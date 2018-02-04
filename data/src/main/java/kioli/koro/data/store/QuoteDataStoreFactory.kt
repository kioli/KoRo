package kioli.koro.data.store

import kioli.koro.data.repository.QuoteDataStore
import javax.inject.Inject

/**
 * Create an instance of a [QuoteDataStore]
 */
open class QuoteDataStoreFactory @Inject constructor(
        private val quoteCacheDataStore: QuoteDataStoreCache,
        private val quoteRemoteDataStore: QuoteDataStoreRemote) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache
     */
    open fun retrieveDataStore(isCached: Boolean): QuoteDataStore {
        if (isCached) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveCacheDataStore(): QuoteDataStore = quoteCacheDataStore

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveRemoteDataStore(): QuoteDataStore = quoteRemoteDataStore

}