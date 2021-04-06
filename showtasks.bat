call runcrud.bat
if "%ERRORLEVEL%" == "0" goto open
echo runcrud.bat has errors – breaking work
goto fail

:open
start chrome http://localhost:8080/crud/v1/task/getTasks

:fail
echo There were errors.