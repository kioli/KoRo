package kioli.koro.ui

import android.app.Activity
import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kioli.koro.ui.di.component.DaggerApplicationComponent
import javax.inject.Inject


class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)

        AppCenter.start(this,
                "fb55b256-4b5f-42fd-80ae-ed0aaf91017f",
                Analytics::class.java,
                Crashes::class.java)
    }

    override fun activityInjector() = activityDispatchingAndroidInjector
}