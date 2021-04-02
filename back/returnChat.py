
from chatterbot import ChatBot
from chatterbot.trainers import ChatterBotCorpusTrainer
import sqlite3

class returnChatBot:
    last=''
    chatbot=None
    def __init__(self,name='Ellie',botmes=''):
        self.last=botmes
        self.chatbot=ChatBot(name, trainer='chatterbot.trainers.ListTrainer',logic_adapters=['chatterbot.logic.BestMatch'])
    def answer(self,ask):
        conn = sqlite3.connect('db.sqlite3')
        print ("Opened database successfully")
        c = conn.cursor()       
        res=self.chatbot.get_response(ask)
        returnmes=res
        res=str(res)
        c.execute("DELETE from response where statement_text like '"+res+"'")
        conn.commit()
        self.chatbot.train([self.last,ask])
        self.chatbot.train([ask,res])
        self.last=res
        return returnmes
