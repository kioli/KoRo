#!/usr/bin/env bash

# Example: launch presentation tests
if [ "$APPCENTER_BRANCH" == "master" ];
then
    cd .. && ./gradlew :presentation:test --stacktrace
fi