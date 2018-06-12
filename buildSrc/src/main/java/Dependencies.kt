object Versions {
    //Android
    const val androidCompileSdk = 27
    const val androidMinSdk = 16
    const val androidTargetSdk = 27
    const val androidVersionCode = 1
    const val androidVersionName = "1.0"
    const val gradleBuildTool = "3.1.3"
    const val dataBindingCompiler = gradleBuildTool
    const val gradleDexCountPlugin = "0.8.2"
    const val lintForKotlin = "0.15.0"

    //Libraries
    const val appCompat = "27.1.1"
    const val androidArchitecture = "1.1.0"
    const val dagger = "2.14.1"
    const val gson = "2.8.2"
    const val javaxInject = "1"
    const val kotlin = "1.2.41"
    const val okHttp = "3.9.1"
    const val retrofit = "2.3.0"
    const val room = "1.0.0"
    const val rxAndroid = "2.0.1"
    const val rxKotlin = "2.2.0"

    //Testing
    const val espressoRunner = "1.0.0"
    const val espresso = "3.0.1"
    const val jUnit = "5.1.1"
    const val mockito = "2.18.3"

    //App Center
    const val appCenterSdk = "1.3.0"
    const val appCenterEspresso = "1.2"
}

object Deps {
    const val archCompiler = "android.arch.lifecycle:compiler:${Versions.androidArchitecture}"
    const val archExtensions = "android.arch.lifecycle:extensions:${Versions.androidArchitecture}"
    const val archRuntime = "android.arch.lifecycle:runtime:${Versions.androidArchitecture}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val javaxInject = "javax.inject:javax.inject:${Versions.javaxInject}"
    const val dataBindingCompiler = "com.android.databinding:compiler:${Versions.dataBindingCompiler}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val lintForKotlin = "com.github.shyiko:ktlint:${Versions.lintForKotlin}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val room = "android.arch.persistence.room:runtime:${Versions.room}"
    const val roomCompiler = "android.arch.persistence.room:compiler:${Versions.room}"
    const val roomRxJava = "android.arch.persistence.room:rxjava2:${Versions.room}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val supportLib = "com.android.support:appcompat-v7:${Versions.appCompat}"
    const val supportDesignLib = "com.android.support:design:${Versions.appCompat}"

    const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    const val espressoRunner = "com.android.support.test:runner:${Versions.espressoRunner}"
    const val junit = "org.junit.jupiter:junit-jupiter-api:${Versions.jUnit}"
    const val mockitoAndroidTest = "org.mockito:mockito-android:${Versions.mockito}"
    const val mockitoUnitTest = "org.mockito:mockito-core:${Versions.mockito}"
    const val architectureTesting = "android.arch.core:core-testing:${Versions.androidArchitecture}"

    const val testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"

    const val appCenterAnalytics = "com.microsoft.appcenter:appcenter-analytics:${Versions.appCenterSdk}"
    const val appCenterCrashes = "com.microsoft.appcenter:appcenter-crashes:${Versions.appCenterSdk}"
    const val appCenterEspresso = "com.microsoft.appcenter:espresso-test-extension:${Versions.appCenterEspresso}"
}