
#coding:utf-8
import sys
import os
class FileTools:
    def __init__(self):
        pass
    def filesize(filepath):
        return os.path.getsize(filepath)

    def dir(self, path):
        '''显示path下所有的子子文件
        '''
        files = []
        dirs = []
        for r, ds, fs in os.walk(path):
            #print "r:=%s\nds=%s\nfs=%s\n"%(r, ds, fs)
            for f in fs:
                ff = os.path.join(r, f)
                #print ff
                files.append(ff)
        #print files
         return files        
me__=="__main__":
= FileTools()
len(sys.argv)==2:
 path = sys.argv[1]
    else:
        path = raw_input("please input path name:")
    fs = ft.dir(path)
    print fs
