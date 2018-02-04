package kioli.koro.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import kioli.koro.data.model.QuoteModelData

/**
 * Interface defining methods for the caching of Quotes.
 * This is to be implemented by the cache layer, using this interface as a way of communicating.
 */
interface QuoteCache {

    /**
     * Clear all quotes from the cache.
     */
    fun clearQuotes(): Completable

    /**
     * Save a quote to the cache.
     */
    fun saveQuote(quote: QuoteModelData): Completable

    /**
     * Retrieve a list of quotes from the cache.
     */
    fun getQuotes(): Flowable<List<QuoteModelData>>

    /**
     * Check whether there is a quote stored in the cache.
     *
     * @return true if the quote is cached, otherwise false
     */
    fun isCached(): Single<Boolean>
}