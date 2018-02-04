package kioli.architecturecomponents

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.squareup.leakcanary.LeakCanary
import kioli.architecturecomponents.db.Db

class App : Application() {

    companion object {
        internal lateinit var db: Db
        internal lateinit var pref: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)
        db = Room.databaseBuilder(applicationContext, Db::class.java, "database-example")
//                .addMigrations(Db.migration1_2)
//                .fallbackToDestructiveMigration()
                .build()
        pref = getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)
    }
}