#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export BASE_CP=base.dashboard/target/base.dashboard-1.4.0-SNAPSHOT.jar:base.dashboard/target/dependency/*;

#REM call the java VM, e.g,
java -cp $BASE_CP web.WebDashboard $1