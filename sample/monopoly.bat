SET JDK=C:\Users\Ian\.IdeaIC2018.1\config\jdks\jbsdk8u112b287.2_windows_x64
SET CLASSPATH=%JDK%\jre\lib\charsets.jar;%JDK%\jre\lib\ext\access-bridge-64.jar;%JDK%\jre\lib\ext\cldrdata.jar;%JDK%\jre\lib\ext\dnsns.jar;%JDK%\jre\lib\ext\jaccess.jar;%JDK%\jre\lib\ext\jfxrt.jar;%JDK%\jre\lib\ext\localedata.jar;%JDK%\jre\lib\ext\nashorn.jar;%JDK%\jre\lib\ext\sunec.jar;%JDK%\jre\lib\ext\sunjce_provider.jar;%JDK%\jre\lib\ext\sunmscapi.jar;%JDK%\jre\lib\ext\sunpkcs11.jar;%JDK%\jre\lib\ext\zipfs.jar;%JDK%\jre\lib\jce.jar;%JDK%\jre\lib\jfxswt.jar;%JDK%\jre\lib\jsse.jar;%JDK%\jre\lib\management-agent.jar;%JDK%\jre\lib\resources.jar;%JDK%\jre\lib\rt.jar
SET CODE_BUILD_DIR=C:\Users\Ian\Documents\GitHub\Monopoly\sample\build

%JDK\bin\javac ..\Main.class
%JDK%\bin\java.exe -Dfile.encoding=UTF-8 -classpath %CLASSPATH%;%CODE_BUILD_DIR% sample.Main
