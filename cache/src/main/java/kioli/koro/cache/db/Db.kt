package kioli.koro.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import kioli.koro.cache.dao.QuoteDao
import kioli.koro.cache.model.QuoteModelCache
import javax.inject.Inject

@Database(entities = [(QuoteModelCache::class)], version = 1, exportSchema = false)
abstract class Db @Inject constructor() : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}