call runcrud
if "%ERRORLEVEL%" == "0" goto chrome
echo.
echo RUNCRUD has errors – breaking work
goto fail

:fail
echo.
echo There were errors

:chrome
start CHROME http://localhost:8080/crud/v1/task/getTasks

:end
echo.
echo Work is finished.