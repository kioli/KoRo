package kioli.architecturecomponents.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import kioli.architecturecomponents.db.dao.UserRoomDao
import kioli.architecturecomponents.db.entry.User

@Database(entities = [(User::class)], version = 1, exportSchema = false)
internal abstract class Db : RoomDatabase() {
    abstract fun userDao(): UserRoomDao

//    companion object {
//        val migration1_2: Migration = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                try {
//                    database.execSQL("CREATE TABLE IF NOT EXISTS new (username TEXT NOT NULL, password TEXT NOT NULL, PRIMARY KEY(username, password))")
//                    database.execSQL("INSERT INTO new SELECT username, password FROM users")
//                    database.execSQL("DROP TABLE users")
//                    database.execSQL("ALTER TABLE new RENAME TO users")
//                    database.execSQL("CREATE UNIQUE INDEX index_users_email ON users(password)")
//                } catch (e: Exception) {
//                    Log.e("DB", "Db migration from v1 to v2 failed: ${e.localizedMessage}")
//                }
//            }
//        }
//    }
}