
#set this to the directory of your project

PROJECT_HOME=${HOME}/eclipse-workspace/Final_Project/


JAVAFX_LIB=/c/usr/java/javafx-sdk-19/lib
JAVAFX="--module-path $JAVAFX_LIB --add-modules javafx.controls,java.sql"

rm -rf ${PROJECT_HOME}/turnin/*
cp -r -t ${PROJECT_HOME}/turnin ${PROJECT_HOME}/src/main/resources/*
find ${PROJECT_HOME}/src/main/java -type f -name "*.java" -exec cp {} ${PROJECT_HOME}/turnin \;

javac $JAVAFX -Xlint -d . *.java

java $JAVAFX application.MainV3
