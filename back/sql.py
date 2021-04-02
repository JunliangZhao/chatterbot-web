import pymysql
import sys,codecs
import pandas as pd
import numpy as np
from sqlalchemy import create_engine
def changetocsv():
    engine = create_engine('mysql+pymysql://root:zjl123...@localhost:3306/bot?charset=utf8')#需修改成自己的密码
    sql = ''' select userAccount,record from t_record where messageType=1; '''
    df1 = pd.read_sql_query(sql, engine)
    engine.dispose()
    df = df1.groupby(by='userAccount')['record'].sum()
    all_ID=[]
    all_kind=[]
    all_value=[]
    for i in range(df.count()):
        all_ID.append(i)
        all_kind.append(df.index[i])
        all_value.append(df[i])
    df2=pd.DataFrame({'id':all_ID,'userAccount':all_kind,'userword':all_value})
    return df2


def savetosql(data):
    sql='''delete  from T_message'''

    db = pymysql.connect("localhost","root","zjl123...","bot")
    cursor = db.cursor()
    cursor.execute(sql)
        # 提交到数据库执行
    db.commit()
    print("删除数据成功")

    cursor.close()
    db.close()


    engine = create_engine('mysql+pymysql://root:zjl123...@localhost:3306/bot?charset=utf8')#需修改成自己的密码
    
    pd.io.sql.to_sql(data,'T_message',engine,'bot',index=False,if_exists='append')
    engine.dispose()
    