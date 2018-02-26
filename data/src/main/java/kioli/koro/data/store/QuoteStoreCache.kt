package kioli.koro.data.store

import kioli.koro.data.model.QuoteModelData
import kioli.koro.data.repository.QuoteCache
import kioli.koro.data.repository.QuoteStore
import javax.inject.Inject

/**
 * Implementation of the [QuoteStore] interface to provide a means of communicating
 * with the local data source
 */
open class QuoteStoreCache @Inject constructor(private val quoteCache: QuoteCache) : QuoteStore {

    /**
     * Clear all Quotes from the cache
     */
    override fun clearQuotes() = quoteCache.clearQuotes()

    /**
     * Save a given [QuoteModelData] instance to the cache
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