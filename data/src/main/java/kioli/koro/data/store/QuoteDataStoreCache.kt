package kioli.koro.data.store

import io.reactivex.Completable
import kioli.koro.data.model.QuoteModelData
import kioli.koro.data.repository.QuoteCache
import kioli.koro.data.repository.QuoteDataStore
import javax.inject.Inject

/**
 * Implementation of the [QuoteDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class QuoteDataStoreCache @Inject constructor(private val quoteCache: QuoteCache) : QuoteDataStore {

    /**
     * Clear all Users from the cache
     */
    override fun clearQuotes() = quoteCache.clearQuotes()

    /**
     * Save a given [List] of [QuoteModelData] instances to the cache
     */
    override fun saveQuote(quote: QuoteModelData) = quoteCache.saveQuote(quote)

    /**
     * Retrieve a list of [QuoteModelData] instance from the cache
     */
    override fun getQuotes() = quoteCache.getQuotes()

    /**
     * Check if the local cache is not empty
     */
    override fun isCached() = quoteCache.isCached()
}