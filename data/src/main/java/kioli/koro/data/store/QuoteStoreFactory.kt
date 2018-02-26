package kioli.koro.data.store

import kioli.koro.data.repository.QuoteStore
import javax.inject.Inject

/**
 * Create an instance of a [QuoteStore]
 */
open class QuoteStoreFactory @Inject constructor(
        private val quoteCacheDataStore: QuoteStoreCache,
        private val quoteRemoteDataStore: QuoteStoreRemote) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache
     */
    open fun retrieveDataStore(isCached: Boolean): QuoteStore {
        if (isCached) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveCacheDataStore(): QuoteStore = quoteCacheDataStore

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveRemoteDataStore(): QuoteStore = quoteRemoteDataStore

}