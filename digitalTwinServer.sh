#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export BASE_CP=base.app.agvDigitalTwin/target/daemon-1.4.0-SNAPSHOT.jar:base.app.agvDigitalTwin/target/dependency/*;

#REM call the java VM, e.g,
java -cp $BASE_CP eapli.base.app.agvDigitalTwin.AgvTwinSrv
