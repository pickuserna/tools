;SetTitleMatchMode 2
;DetectHiddenWindows, on
;ifwinactive,chrome.exe
;{
;    winminimize
;}
;    ifwinexist,Chrome.exe
;{
;	msgbox "activate"
;	winactivate
;}
;else
;{
;msgbox "new chrome"
;run %chromePath%
;Loop
;{
;    WinGet, win_pc,ID,A
;    WinWaitNotActive,ahk_id %win_pc%
;    WinGet, win_pp,ProcessName,A
;    MsgBox, %win_pp%£¬ %processName%, %A%
;	ControlClick È·¶¨
;}
;}
;return
wininfo()
{
   mouseGetPos ,,, id, control
   ;wingetTitle, title, ahk_id %id%
   ;wingetClass, class,  ahk_id %id%
   ;wingetText , text , ahk_id %id%
   msgbox "title: %title% ,, class: %class%,, ,, et: %ExcludeTitle%,, etext:%ExcludeText%"
}
#R:: 
{
    wininfo()    
}
return 
SetTitleMatchMode 2
#f::
ifwinexist Google - Google Chrome
{
    msgbox "exist *chrome"
}
else {
    msgBox "not *chrome"
}
IfWinExist ahk_class Vim
{
    msgbox "ahk_class"
    winactivate
}
else
{
    msgbox "not ahk_class"
}    
return
