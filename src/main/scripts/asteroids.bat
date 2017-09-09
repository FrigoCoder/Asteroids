@echo off
setlocal
cd %~dp0

set JAVA=java.exe
if defined JAVA_HOME set JAVA="%JAVA_HOME%\bin\java.exe"

set JVM_OPTIONS=-XX:+UseG1GC -XX:MaxGCPauseMillis=10 -Dsun.java2d.opengl=true -Dsun.java2d.d3d=false

set CLASSPATH=lib/*

set MAIN_CLASS=frigo.asteroids.Game

%JAVA% %JVM_OPTIONS% -cp %CLASSPATH% %MAIN_CLASS%

endlocal
