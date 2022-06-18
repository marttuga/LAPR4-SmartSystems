REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=base.customerApp\target\base.customerApp-1.4.0-SNAPSHOT.jar;base.customerApp\target\dependency\*;

REM call the java VM, e.g,
java -cp %BASE_CP% eapli.presentation.CustomerApp