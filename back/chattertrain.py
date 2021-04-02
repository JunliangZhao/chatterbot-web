#进行库训练  
#下列是几种常用的训练方法
from chatterbot import ChatBot
from chatterbot.trainers import ChatterBotCorpusTrainer

def myTrain():  #利用chatterbot-corpus培训
    chatbot = ChatBot("Ellie",trainer='chatterbot.trainers.ChatterBotCorpusTrainer')

    chatbot.train("chatterbot.corpus.mychat")

def listTrain():#列表式的训练
    chatbot=ChatBot('Ellie', trainer='chatterbot.trainers.ListTrainer')
    chatbot.train([
    '你好',
    '你好',
    '有什么能帮你的？',
    '想买数据科学的课程',
    '具体是数据科学哪块呢？'
    '机器学习',
    '你是谁',
    '我叫Ellie，今年18岁了，生日是5.20哦，不过本宝宝单身，希望可以和你成为朋友哦',
    '我叫，我也希望和你成为朋友',
    '那我们来聊聊天吧',
    '你想聊什么？',
    '你愿意和我一起去看电影吗',
    '技术宅男都这样吗，追妹子只会吃饭看电影吗',
    '天啊，机器人都会嘲讽人了吗',
    '本宝宝可是21世纪，最强机器人呢，不要小看我哦'
    ])


def vTrain():  #利用微软实现的培训
    # -*- coding: utf-8 -*-
    chatbot = ChatBot(
        'Ellie',
        directline_host = Microsoft['directline_host'],
        direct_line_token_or_secret = Microsoft['direct_line_token_or_secret'],
        conversation_id = Microsoft['conversation_id'],
        input_adapter='chatterbot.input.Microsoft',
        output_adapter='chatterbot.output.Microsoft',
        trainer='chatterbot.trainers.ChatterBotCorpusTrainer'
    )
    
    chatbot.train('chatterbot.corpus.english')



def main():
    myTrain()


if __name__ == '__main__':
    main()

