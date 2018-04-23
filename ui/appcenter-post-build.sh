#!/usr/bin/env bash

# Example: launch unit tests
# if [ "$APPCENTER_BRANCH" == "master" ];
# then
#     cd .. && ./gradlew test
# fi

# echo "LORENZO"
# echo $APPCENTER_SOURCE_DIRECTORY
# cd build/outputs/apk
# pwd
appcenter test run espresso --app "kioli85/Koro" --devices "kioli85/simple" --app-path $APPCENTER_OUTPUT_DIRECTORY/ui-debug-androidTest.apk  --test-series "master" --locale "en_US" --build-dir $APPCENTER_SOURCE_DIRECTORY/ui/build/outputs/apk/debug --token b380e71528dd49a10fb990c74f55b50d85291757 --debug

# appcenter test run espresso --app "kioli85/Koro" --devices "kioli85/simple" --app-path $APPCENTER_OUTPUT_DIRECTORY/*.apk  --test-series "master" --locale "en_US" --build-dir $APPCENTER_SOURCE_DIRECTORY/androidTest/debug --token b380e71528dd49a10fb990c74f55b50d85291757 --debug