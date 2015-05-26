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
totalCMDPath = D:\BaiduYunDownload\Total_Commander_v8.5_汉化破解绿色正式版\TOTALCMD.EXE
vimPath = D:\software\gVimPortable_7.4\vim74\gvim.exe 
xmindPath = D:\Program Files (x86)\XMind\XMind.exe 

youdaoPath = C:\Users\GLIP\AppData\Local\Youdao\Dict\Application\YodaoDict.exe

;#a:: run "%BaiduYunPath% "
#b:: 
run C:\Program Files (x86)\Git\bin\sh.exe --login -i
Sleep 10
send cd ~{Enter}
return 




#c:: 
run %chromePath%
return 






#e:: run %evernotePath%
;#g:: run %goagentPath%

#m:: run %myeclipsePath%
#n:: run %notepadPath%
#o:: run %outlookPath%
#q:: run %qqPath%
#t:: run %totalCMDPath%
#v:: run %vimPath%
;#x:: run %xmindPath%
#y:: run %youdaoPath%
