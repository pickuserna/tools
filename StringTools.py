#coding : utf-8
import re
class StringTools:
    commentRex = r"/\*([^\*]|[\*](?!/))*\*/"
    def comment(self, rex,repl, s):
        return re.sub( rex, repl ,s)
    def comment(self, rex, repl, s, index):
        p = re.compile(rex)
        startpos= 0
        count = 0
        while True:
            m = p.search(s, startpos)
            count +=1
            if m!=None and count==index:
                news = s[:m.start()]+repl+s[m.end():]
                return news
            if m!=None:
                startpos = m.end()

