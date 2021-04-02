#!/usr/bin/python
# coding=utf-8
# 采用TF-IDF方法提取文本关键词
import sys,codecs
import pandas as pd
import numpy as np
import jieba.posseg
import jieba.analyse
from sklearn import feature_extraction
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.feature_extraction.text import CountVectorizer
from pickMajor.sql import changetocsv 
from pickMajor.sql import savetosql

# 数据预处理操作：分词，去停用词，词性筛选
def dataPrepos(text, stopkey):
    l = []
    pos = ['n', 'nz', 'v', 'vd', 'vn', 'l', 'a', 'd']  # 定义选取的词性
    seg = jieba.posseg.cut(text)  # 分词
    for i in seg:
        if i.word not in stopkey and i.flag in pos:  # 去停用词 + 词性筛选
            l.append(i.word)
    return l

# tf-idf获取文本top10关键词
def getKeywords_tfidf(data,stopkey,topK):
    idList, titleList, abstractList = data['id'], data['userAccount'], data['userword']
    corpus = [] # 将所有文档输出到一个list中，一行就是一个文档
    for index in range(len(idList)):
        text = '%s。%s' % (titleList[index], abstractList[index]) # 拼接标题和摘要
        text = dataPrepos(text,stopkey) # 文本预处理
        text = " ".join(text) # 连接成字符串，空格分隔
        corpus.append(text)

    # 1、构建词频矩阵，将文本中的词语转换成词频矩阵
    vectorizer = CountVectorizer()
    X = vectorizer.fit_transform(corpus) # 词频矩阵,a[i][j]:表示j词在第i个文本中的词频
    # 2、统计每个词的tf-idf权值
    transformer = TfidfTransformer()
    tfidf = transformer.fit_transform(X)
    # 3、获取词袋模型中的关键词
    word = vectorizer.get_feature_names()
    # 4、获取tf-idf矩阵，a[i][j]表示j词在i篇文本中的tf-idf权重
    weight = tfidf.toarray()
    # 5、打印词语权重
    ids, titles, keys = [], [], []



    all_keyweight=[]  #保存每篇文章权重
    all_keyword=[]   #保存每篇文章关键词

    for i in range(len(weight)):
        #print (u"-------这里输出第", i+1 , u"篇文本的词语tf-idf------")
        ids.append(idList[i])
        titles.append(titleList[i])
        df_word,df_weight = [],[] # 当前文章的所有词汇列表、词汇对应权重列表
        for j in range(len(word)):
            #print (word[j],weight[i][j])
            df_word.append(word[j])
            df_weight.append(weight[i][j])
        df_word = pd.DataFrame(df_word,columns=['word'])
        df_weight = pd.DataFrame(df_weight,columns=['weight'])
        word_weight = pd.concat([df_word, df_weight], axis=1) # 拼接词汇列表和权重列表
        word_weight = word_weight.sort_values(by="weight",ascending = False) # 按照权重值降序排列
        keyweight=np.array(word_weight['weight'])

        #print(keyweight)#输出权重列表

        keyword = np.array(word_weight['word']) # 选择词汇列并转成数组格式



        #临时保存一个用户聊天的关键词信息
        tempall=[]
        tempallkeyword=[]
        #遍历一个用户
        for l in range(topK):
            tempall.append(int(keyweight[l]*1000))
            tempallkeyword.append(keyword[l])
        #添加到全部用户的关键词列表
        all_keyword.append(tempallkeyword)
        all_keyweight.append(tempall)



        #在向数据库输出的同时还建了一个dataframe
        word_split = [keyword[x] for x in range(0,topK)] # 抽取前topK个词汇作为关键词
        word_split = " ".join(word_split)
        keys.append(word_split)  

    result = pd.DataFrame({"id": ids, "userAccount": titles, "userword": keys},columns=['id','userAccount','userword'])
    return result,titles,all_keyweight,all_keyword

def anay():
    # 读取数据集
    print("分析数据开始")

    data = changetocsv()
    print(data)
    # 停用词表
    stopkey = [w.strip() for w in codecs.open('datasets/stopWord.txt', 'r',encoding='utf-8').readlines()]
    # tf-idf关键词抽取
    result,user,weight,word= getKeywords_tfidf(data,stopkey,5)
    all_id=[]
    all_user=[]
    all_word=[]
    all_weight=[]
    l=1
    for i in range(len(weight)):
        tempall_id=[]
        tempall_user=[]
        tempall_word=[]
        tempall_weight=[]
        for j in range(len(weight[i])):
            all_id.append(l)
            l=l+1
            all_user.append(user[i])
            all_word.append(word[i][j])
            all_weight.append(weight[i][j])

    print(all_id)
    print(all_user)
    print(all_word)
    print(all_weight)

    print(result)
   

    myresult=pd.DataFrame({"userID": all_id, "userAccount": all_user, "userword": all_word,"fwords":all_weight},columns=['userID','userAccount','userword','fwords'])
    

    print(myresult)

    savetosql(myresult)
    
    print("分析结束")

