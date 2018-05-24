package kioli.koro.data.store

import kioli.koro.data.repository.QuoteStore

/**
 * Create an instance of a [QuoteStore]
 */
class QuoteStoreFactory(private val quoteCacheDataStore: QuoteStoreCache,
                        private val quoteRemoteDataStore: QuoteStoreRemote) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache
     */
    fun retrieveDataStore(isCached: Boolean): QuoteStore {
        if (isCached) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    fun retrieveCacheDataStore(): QuoteStore = quoteCacheDataStore

    /**
     * Return an instance of the Remote Data Store
     */
    fun retrieveRemoteDataStore(): QuoteStore = quoteRemoteDataStore

}