REM set the class path,
REM assumes the build was executed with maven copy-dependencies SET BASE_CP=base.orderServer\target\base.orderServer-1.4.0-SNAPSHOT.jar;base.orderServer\target\dependency\*;

REM call the java VM, e.g, java -cp %BASE_CP% eapli.presentation.OrderServer

REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=base.app.customerApplication\target\base.app.customerApplication-1.4.0-SNAPSHOT.jar;base.app.customerApplication\target\dependency\*;

REM call the java VM, e.g,
java -cp %BASE_CP% eapli.presentation.CustomerAppCli