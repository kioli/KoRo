package kioli.koro.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import kioli.koro.data.mapper.QuoteDataMapper
import kioli.koro.data.store.QuoteStoreFactory
import kioli.koro.domain.model.QuoteModelDomain
import kioli.koro.domain.repository.QuoteRepository
import javax.inject.Inject

/**
 * Provides an implementation of the [QuoteRepository] interface for communicating to and from
 * data sources
 */
class QuoteDataRepository @Inject constructor(private val factory: QuoteStoreFactory,
                                              private val mapper: QuoteDataMapper) :
        QuoteRepository {

    override fun clearQuotes(): Completable =
            factory.retrieveCacheDataStore().clearQuotes()

    override fun saveQuote(quote: QuoteModelDomain): Completable =
            factory.retrieveCacheDataStore().saveQuote(mapper.mapToData(quote))

    override fun loadQuote(): Flowable<QuoteModelDomain> {
        return factory.retrieveCacheDataStore().isCached()
                .flatMapPublisher {
                    factory.retrieveDataStore(it).getQuotes()
                }
                .flatMap {
                    Flowable.just(it.map { mapper.mapFromData(it) }.first())
                }
    }
}