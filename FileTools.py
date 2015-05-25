##!/usr/bin/env python
#coding:utf-8
import sys
import os
class FileTools:
    def __init__(self):
        pass
    def filesize(self, filepath):
        ''' get file size of a file , or the total size of a directory
        
        '''
        # filepath devote a file, return the size of the file

        if os.path.isfile(filepath):
            return os.path.getsize(filepath)
        elif os.path.isdir(filepath):
            size = 0
            for f in os.listdir(filepath):
                size += self.filesize(os.path.join(filepath, f))
        return size
    
             
    def pathw2l(self, path):
        ''' windows path 2 linux from root path

        linux format: /c/home/usr/env python
        '''
        pathlist = path.split('\\')
        pathlist[0] =  pathlist[0][0]
        return "/"+"/".join(pathlist)
    def pathl2w(self, path):
        '''linux path 2 windows from root path
        '''
        lenpath = len(path)
        
        pathlist = []
        if lenpath>=2:
            pathlist = path.split("/")
            if pathlist[0]=="":
                pathlist.remove("")
            return pathlist[0]+":\\"+"\\".join(pathlist[1:])

    def dir(self, path):
        '''显示path下所有的子子文件
        '''
        files = []
        dirs = []
        for r, ds, fs in os.walk(path):
            #print "r:=%s\nds=%s\nfs=%s\n"%(r, ds, fs)
            for f in fs:
                ff = os.path.join(r, f)
                files.append(ff)
        return files
ft = FileTools()
funcdict = {"d":ft.dir, "fs":ft.filesize, "pl":ft.pathw2l, "pw":ft.pathl2w}
if __name__=="__main__":

    para = ""
    arg = ""
    if len(sys.argv)<2:
        path = sys.argv[1]
        para = raw_input("输入操作参数:")
        arg = raw_input("输入路径：")
        pass
    else:
        para = sys.argv[1] 
        arg = sys.argv[2]
        print para, arg
    print funcdict.get(para[1:])(arg)

