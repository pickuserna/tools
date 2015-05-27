#!/usr/bin/env python
#coding:utf-8
import os
import time

def fcomment(c):
    if len(c)!=0:
        return c.replace(" ", "_")
    return ""
def backupdir(source, dest, comment):
    day = time.strftime("%Y%m%d")
    hms = time.strftime("%HMS")
    comment = fcomment(comment) 
    fileName = "_".join([os.path.split(source)[1], comment, day, hms,r".jar"])
    cmd = "jar -cvf %s -C %s ." % (os.path.join(dest,fileName), source) 
    print cmd
    os.system(cmd)


if __name__=="__main__":
    sourcedir = raw_input("source dir:")
    destdir = raw_input("dest dir:")
    comment = raw_input("comment:")
    if not destdir.endswith("\\"):
        destdir = destdir+"\\"
    #sourcedir = r"D:/tools"
    #destdir = r"F:/"
    #comment = r"hello"
    backupdir(sourcedir, destdir, comment)
