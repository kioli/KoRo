#!/usr/bin/env bash

# Launch unit tests
if [ "$APPCENTER_BRANCH" == "master" ];
then
    cd .. && ./gradlew test
fi

# Launch UI tests
./gradlew assembleAndroidTest
appcenter test run espresso --app "kioli85/Koro" --devices "kioli85/simple" --app-path $APPCENTER_OUTPUT_DIRECTORY/ui-debug.apk  --test-series "master" --locale "en_US" --build-dir $APPCENTER_SOURCE_DIRECTORY/ui/build/outputs/apk/androidTest/debug --token b380e71528dd49a10fb990c74f55b50d85291757 --debug