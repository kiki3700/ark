@echo off

:: BatchGotAdmin
:-------------------------------------
REM  --> 권한 확인
    IF "%PROCESSOR_ARCHITECTURE%" EQU "amd64" (
>nul 2>&1 "%SYSTEMROOT%\SysWOW64\cacls.exe" "%SYSTEMROOT%\SysWOW64\config\system"
) ELSE (
>nul 2>&1 "%SYSTEMROOT%\system32\cacls.exe" "%SYSTEMROOT%\system32\config\system"
)

REM --> 관리자가 아닐 경우, 에러발생
if '%errorlevel%' NEQ '0' (
    echo Requesting administrative privileges...
    goto UACPrompt
) else ( goto gotAdmin )

:UACPrompt
    echo Set UAC = CreateObject^("Shell.Application"^) > "%temp%\getadmin.vbs"
    set params= %*
    echo UAC.ShellExecute "cmd.exe", "/c ""%~s0"" %params:"=""%", "", "runas", 1 >> "%temp%\getadmin.vbs"

    "%temp%\getadmin.vbs"
    del "%temp%\getadmin.vbs"
    exit /B

:gotAdmin
    pushd "%CD%"
    CD /D "%~dp0"
:--------------------------------------

:: 아래에서 계정아이디, 통신비밀번호, 공인인증서비밀번호를 사용자의 것으로 변경해 주십시오.
:: 본 스크립트는 월요일부터 금요일까지 아침 8시에 대신증권 HTS (사이보스 5)를 기동하고 로그인을 자동으로 해줍니다.
:: 미국의 썸머타임 적용 기간에는 작업스케줄러에서 시간을 변경해 주십시오. 08:00 => 07:00

SchTasks /Create /SC WEEKLY /D MON,TUE,WED,THU,FRI /TN "CYBOS_AutoLogin" /TR "C:\Daishin\Starter\ncStarter.exe /id:계정아이디 /pwd:통신비밀번호 /pwdcert:공인인증서비밀번호 /autostart" /ST 08:00 /RL HIGHEST