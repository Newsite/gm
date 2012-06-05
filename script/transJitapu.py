#coding=utf-8
'''
Author: wangfei
Date: 12-6-4
Time: 下午7:31

用来把wget抓取的jitapu网页中的吉他谱提取出来
'''
import os
from MySQLdb import *


conn = Connection(user='root',passwd='87654312',charset='utf8')
conn.select_db('guitarme')
cursor = conn.cursor()

def insertData(dataList):
    sql = "insert into jitapu select null,%s"
    cursor.executemany(sql,dataList)
    cursor.execute('commit')

    
def readDataFromFile(filePath):
    f = open(filePath,'r')
    data = f.read()

    start = data.find('<pre>')+len('<pre>')
    end = data.find('</pre>')
    return  data[start:end].decode('gbk')

jitapuPath = '/Users/wangfei/jitapu/www.jitapu.com'
dirs = os.listdir(jitapuPath)

dataList = []

for dir in dirs:
    if dir.startswith('showtab.aspx?tabid'):
            try:
                path = os.path.join(jitapuPath,dir)
                data = readDataFromFile(path)
                dataList.append(data)
            except UnicodeDecodeError,error:
                print "'%s'有不能被转码的部分:[%s]"%(path,error)


insertData(dataList)
