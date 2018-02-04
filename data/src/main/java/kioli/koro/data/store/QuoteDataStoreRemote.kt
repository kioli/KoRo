package kioli.koro.data.store

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import kioli.koro.data.model.QuoteModelData
import kioli.koro.data.repository.QuoteDataStore
import kioli.koro.data.repository.QuoteRemote
import javax.inject.Inject

/**
 * Implementation of the [QuoteDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class QuoteDataStoreRemote @Inject constructor(private val quoteRemote: QuoteRemote) : QuoteDataStore {

    override fun clearQuotes(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveQuote(quote: QuoteModelData): Completable {
        throw UnsupportedOperationException()
    }

    override fun getQuotes(): Flowable<List<QuoteModelData>> {
        return quoteRemote.getQuotes().toList().toFlowable()
    }

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException()
    }
}