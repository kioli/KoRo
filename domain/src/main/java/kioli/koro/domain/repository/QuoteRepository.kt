package kioli.koro.domain.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import kioli.koro.domain.model.QuoteModelDomain

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the operations that need to be implemented
 */
interface QuoteRepository {

    fun clearQuotes(): Completable

    fun saveQuote(quote: QuoteModelDomain): Completable

    fun loadQuote(): Flowable<QuoteModelDomain>
}