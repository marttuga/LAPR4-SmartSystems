#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export BASE_CP=base.dashboard/target/base.dashboard-1.3.0-SNAPSHOT.jar:base.dashboard/target/dependency/*;

#REM call the java VM, e.g,
java -cp $BASE_CP base.dashboard/src/main/java/web/WebDashboard.java $1