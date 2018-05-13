package kioli.koro.ui

import android.app.Activity
import android.app.Application
import android.support.annotation.VisibleForTesting
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kioli.koro.ui.di.component.ApplicationComponent
import kioli.koro.ui.di.component.DaggerApplicationComponent
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @set:VisibleForTesting
    internal var component = createComponent()

    private fun createComponent(): ApplicationComponent = DaggerApplicationComponent
            .builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        AppCenter.start(this,
                "fb55b256-4b5f-42fd-80ae-ed0aaf91017f",
                Analytics::class.java,
                Crashes::class.java)
    }

    override fun activityInjector() = activityDispatchingAndroidInjector
}