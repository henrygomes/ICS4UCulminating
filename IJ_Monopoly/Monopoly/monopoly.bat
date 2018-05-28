REM Set to path of JDK install. Can download from Oracle
SET JDK=.\Java_sdk
REM SET JDK=.\jre

%JDK%\bin\javac -Xlint:unchecked -d .\build .\src\Monopoly\*.java
COPY .\src\Monopoly\sample.fxml .\build\Monopoly
COPY .\src\Monopoly\monoplyboard.jpg .\build\Monopoly


SET CLASSPATH=%JDK%\jre\lib\charsets.jar;%JDK%\jre\lib\ext\access-bridge-64.jar;%JDK%\jre\lib\ext\cldrdata.jar;%JDK%\jre\lib\ext\dnsns.jar;%JDK%\jre\lib\ext\jaccess.jar;%JDK%\jre\lib\ext\jfxrt.jar;%JDK%\jre\lib\ext\localedata.jar;%JDK%\jre\lib\ext\nashorn.jar;%JDK%\jre\lib\ext\sunec.jar;%JDK%\jre\lib\ext\sunjce_provider.jar;%JDK%\jre\lib\ext\sunmscapi.jar;%JDK%\jre\lib\ext\sunpkcs11.jar;%JDK%\jre\lib\ext\zipfs.jar;%JDK%\jre\lib\jce.jar;%JDK%\jre\lib\jfxswt.jar;%JDK%\jre\lib\jsse.jar;%JDK%\jre\lib\management-agent.jar;%JDK%\jre\lib\resources.jar;%JDK%\jre\lib\rt.jar
SET CODE_BUILD_DIR=.\build

%JDK%\bin\java.exe -Dfile.encoding=UTF-8 -classpath %CLASSPATH%;%CODE_BUILD_DIR% Monopoly.Main
