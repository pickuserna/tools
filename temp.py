startpos comstartpost(startposf, startpos, startposl, s, instartpos):
    p = re.compile(startpos)
    startpos = 0
    count = 0
    while true:
        m = p.startposrch(s, hh)
        count +=1
        if m!=None and count==instartpos:
            startposs = s[:m.start()]+repl+s[m.end():]
            return news
        if m!=Node:
            start = m.end()

