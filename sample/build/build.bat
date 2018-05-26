SET JDK=C:\Users\Ian\.IdeaIC2018.1\config\jdks\jbsdk8u112b287.2_windows_x64
SET CODE_BUILD_DIR=C:\Users\Ian\Documents\GitHub\Monopoly\sample\out

%JDK%\bin\javac -Xlint:unchecked -d . ..\*.java
COPY ..\sample.fxml .\sample
