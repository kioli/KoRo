package kioli.koro.data.repository

import io.reactivex.Flowable
import kioli.koro.data.model.QuoteModelData

/**
 * Interface defining methods for the caching of Users. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface QuoteRemote {

    /**
     * Retrieve a list of Users from a remote source
     */
    fun getQuotes(): Flowable<QuoteModelData>

}