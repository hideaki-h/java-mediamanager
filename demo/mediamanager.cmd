@echo off
setlocal enabledelayedexpansion

set LIB_DIR=L:\
set LIB_ESC_DIR=L\:\\lib000\\movies\\@TO
set TMPFILE=%TEMP%\%~n0.list
set column=0

cd /d %~dp0

dir /b /a:d %LIB_DIR% | find /V "lib000" | find /V "public" | find /V "delete_dup_ts.batch.config" > %TMPFILE%

copy /y "%~dpn0.master.properties" "%~dpn0.properties" >NUL

set /p X=libraries=<NUL >>"%~dpn0.properties"

for /f "delims=" %%l IN (%TMPFILE%) do (
	set /a column=!column!+ 1
	if !column! neq 1 set /p X=,<NUL >>"%~dpn0.properties"
	set /p X=%%l/%LIB_ESC_DIR%\\%%l<NUL >>"%~dpn0.properties"
)

del %TMPFILE%

start /b java -jar "%~dpn0.jar"

endlocal
