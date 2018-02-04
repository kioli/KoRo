package kioli.koro.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import kioli.koro.data.model.QuoteModelData

/**
 * Interface defining methods for the data operations related to Quotes.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface QuoteDataStore {

    fun clearQuotes(): Completable

    fun saveQuote(quote: QuoteModelData): Completable

    fun getQuotes(): Flowable<List<QuoteModelData>>

    fun isCached(): Single<Boolean>

}