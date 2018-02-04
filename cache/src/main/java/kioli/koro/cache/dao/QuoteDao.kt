package kioli.koro.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import kioli.koro.cache.model.QuoteModelCache
import kioli.koro.cache.model.quotesTableName

@Dao
abstract class QuoteDao {

    @Query("SELECT * FROM $quotesTableName")
    abstract fun getQuotes(): List<QuoteModelCache>

    @Query("DELETE FROM $quotesTableName")
    abstract fun clearQuotes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertQuote(quote: QuoteModelCache)

}