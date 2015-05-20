import sys
import re 
def win2linux(path):
    '''
    windows path "D:\hello" -> "D:/hello"
    '''
    pwin = r"\\"
    return  re.sub(pwin,"/", path)
def linux2win(path):
    pl = "/"
    return re.sub(pl, r"\\",path)
if __name__=="__main__":
    print "-w:2win\n-l:2linux"
    arg = sys.argv[1]

    operator = {'w':linux2win, 'l':win2linux}
    if sys.argv[1].startswith('-'):
        print operator.get(sys.argv[1][1])(sys.argv[2])
    
    
