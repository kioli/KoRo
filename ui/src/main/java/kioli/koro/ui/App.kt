package kioli.koro.ui

import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import kioli.koro.ui.activity.QuoteActivity

class App : Application() {

    interface Creator {
        fun quoteActivity(): QuoteActivity.Dependencies
    }

    val creator = KodeinCreator()

    override fun onCreate() {
        super.onCreate()
        AppCenter.start(this,
                "fb55b256-4b5f-42fd-80ae-ed0aaf91017f",
                Analytics::class.java,
                Crashes::class.java)
    }
}