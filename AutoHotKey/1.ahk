baiduyunPath  = C:\Users\GLIP\AppData\Roaming\baidu\BaiduYunGuanjia\BaiduYunGuanjia.exe

bashgitPath = C:\Program Files (x86)\Git\bin\sh.exe
chromePath = C:\Program Files (x86)\Google\Chrome\Application\chrome.exe
evernotePath = C:\Program Files (x86)\Evernote\Evernote\Evernote.exe 
goagentPath = C:\Program Files (x86)\goagent-3.0\local\goagent.exe
myeclipsePath =C:\Users\GLIP\AppData\Local\MyEclipse Professional 2014\myeclipse.exe 
notepadPath = C:\Program Files (x86)\Notepad++\notepad++.exe


outlookPath=C:\Program Files\Microsoft Office\Office15\OUTLOOK.EXE
pythonPath =C:\Python27\python.exe 
qqPath = C:\Program Files (x86)\Tencent\QQ\Bin\QQ.exe 

;Not By The Default
totalCMDPath =D:\tools\Total_Commander_v8.5\TOTALCMD.EXE
vimPath = D:\software\gVimPortable_7.4\vim74\gvim.exe 
xmindPath = D:\Program Files (x86)\XMind\XMind.exe 

youdaoPath = C:\Users\GLIP\AppData\Local\Youdao\Dict\Application\YodaoDict.exe
cmd = C:\Windows\system32\cmd.exe
#1:: 
run %cmd% /c start gvim D:\tools\AutoHotKey\1.ahk
winactivate
return
;#a:: run "%BaiduYunPath% "
#b:: 
run C:\Program Files (x86)\Git\bin\sh.exe --login -i
Sleep 10Vim


send cd ~{Enter}
return 


start(path, wclass)
{
    IfWinExist ahk_class %wclass%
    {
        winactivate
    }
    else
    {
        run %path%
        winactivate
    }
}
paste(t)
{
    Sleep %t%
    send %clipboard%{Enter}{Enter}
}
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;chrome;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#c:: 
IfWinExist ahk_class Chrome_WidgetWin_1
{
    winactivate
}
else
{
run http://www.google.com
;msgbox "winwait"
winwait ahk_class Chrome_WidgetWin_1
winactivate
sleep 3000
send %clipboard%{Enter}{Enter}
}
return 

#d::
send ^c
IfWinExist ahk_class Chrome_WidgetWin_1
{
    run http://www.google.com
    winactivate
    paste(3000)
}
else
{
run http://www.google.com
;msgbox "winwait"
winwait ahk_class Chrome_WidgetWin_1
winactivate
paste(3000)
}

return
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;----chrome-----;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


#e:: start(evernotePath, "ENMainFrame")
;#g:: run %goagentPath%

#n:: run %notepadPath%
#o:: run %outlookPath%
#q:: run %qqPath%
#t:: run %totalCMDPath%
#v:: start(vimPath, "Vim")
;#x:: run %xmindPath%
#w:: start(myeclipsePath,"SWT_Window0")
#y:: run %youdaoPath%
#z:: 
start(chromePath, "Chrome_WidgetWin_1")
;;3char
;#gt::;
