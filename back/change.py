import re

name="xiaohuangji"

with open(name+'.yml',mode='w',encoding='utf-8') as f1:   #转换tsv文本为yml可学习文本
    f1.write("categories:"+'\n'+"- "+name+'\n'+"conversations:"+'\n'+"- - ")

with open(name+'.tsv', encoding='utf-8') as f:   

    data = f.read().replace('\t', '#')
    data=data.replace('\n','*')
    data=data.replace('#','\n  - ')
    data=data.replace('*','\n- - ')
    data=data.replace(',',' ')
    data=data.replace('@',' ')
    data=data.replace(':',' ')
    data=data.replace('%',' ')
    data=data.replace('<',' ')


with open(name+'.yml','a',encoding='utf-8') as f1:
     f1.write(data)
  