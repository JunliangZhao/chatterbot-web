from flask import Flask,jsonify,request
import requests
from bs4 import BeautifulSoup
from chatbotapp.returnChat import returnChatBot
app=Flask("myapp",static_url_path="/")


@app.route("/biz/mesdata.json", methods=["POST"])
def mes_data_service():
    if request.method == 'POST':
        
        if "usermes" in request.form:
            
            usermes = request.form["usermes"]
            botmes = request.form["botmes"]
            print(usermes,":",botmes)
        else:
            usermes ="未知"
        
        # 后端处理语句
        if (usermes[0]+usermes[1])=="搜索":
            print("爬虫")

            reply ="为您找到一下内容："+'\n'
            
            print(usermes[2:])

            key_words = usermes[2:]
            #页面深度
            depth = 1
            #伪装浏览器头部
            kv = {"User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36"}
            #获得每页搜索结果
            for i in range(depth):
                url = 'https://www.baidu.com/s?wd=' + key_words + '&pn=' + str(i * 10)
                print(url)
                try:
                    r = requests.get(url, headers = kv)
                    r.raise_for_status()
                    r.encoding = r.apparent_encoding
                    html = r.text
                except:
                    print("Error1")
                
                soup = BeautifulSoup(html, 'html.parser')
                h3 = soup.find_all('h3')
                for i in h3:
                    a = i.a
                    try:
                        href = a.attrs['href']
                        #获取a标签中的文字
                        reply = reply + a.text + '\n' + href +'\n' 
                        print(reply)
                    except:
                        print('Error2')

            json = {
                'reply':reply
            }
            return jsonify(json)


        else:
            
            mychat= returnChatBot('Ellie',botmes)

            reply = (mychat.answer(usermes))

            # 返回reply
            
            print(reply)
            json = {
                'reply':str(reply)
            }
            
            return jsonify(json)
        
        

    else:
        json = {
            'reply':"ERROR"
        }
        return jsonify(json)