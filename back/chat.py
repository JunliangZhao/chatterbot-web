from chatbotapp.returnChat import returnChatBot
from chatterbot import ChatBot
from chatterbot.trainers import ChatterBotCorpusTrainer

##几种不同的逻辑适配器所运行的聊天机器人，机器人的效果有所不同


def mychat():    #最佳匹配反馈式学习机器人
    mychat= returnChatBot('Ellie')
    while True:
        ask=input('>')
        res=mychat.answer(ask)
        print((res))


def mathlogicchat():  #时间和数学问题机器人
    mybot = ChatBot(
    "Ellie",
    logic_adapters=[
        "chatterbot.logic.MathematicalEvaluation",
        "chatterbot.logic.TimeLogicAdapter"
    ])
    # 进行数学计算
    question = "What is 4 + 9?"
    print(question)
    response = mybot.get_response(question)
    print(response)

    print("\n")

    # 回答和时间相关的问题
    question = "What time is it?"
    print(question)
    response = mybot.get_response(question)
    print(response)


def bestmatchchat():  #最佳匹配返回
    mybot = ChatBot(
    "Ellie",
    logic_adapters=[
        "chatterbot.logic.BestMatch"
    ])
    while True:
        ask=input('>')
        res=mychat.answer(ask)
        print((res))

        
def main():
    mychat()

if __name__ == '__main__':
    main()
