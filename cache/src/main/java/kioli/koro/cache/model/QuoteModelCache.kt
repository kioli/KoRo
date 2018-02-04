package kioli.koro.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

const val quotesTableName = "quotes"

/**
 * Representation for a quote instance for the CACHE layer
 */
@Entity(tableName = quotesTableName)
data class QuoteModelCache(@PrimaryKey var id: Long, val text: String)