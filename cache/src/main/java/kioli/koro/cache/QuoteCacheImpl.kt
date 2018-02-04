package kioli.koro.cache

import android.support.annotation.VisibleForTesting
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import kioli.koro.cache.db.Db
import kioli.koro.cache.mapper.QuoteCacheMapper
import kioli.koro.cache.model.QuoteModelCache
import kioli.koro.data.model.QuoteModelData
import kioli.koro.data.repository.QuoteCache
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving Quote instances.
 * This class implements the [QuoteCache] from the Data layer as it is that layers responsibility
 * to define the operations the data store implementation layers can carry out.
 */
class QuoteCacheImpl @Inject constructor(val db: Db, private val entityMapper: QuoteCacheMapper) : QuoteCache {

    /**
     * Retrieve an instance from the database, used for tests.
     */
    @VisibleForTesting
    internal fun getDatabase() = db

    /**
     * Remove all the data from the Quotes table in the database.
     */
    override fun clearQuotes(): Completable {
        return Completable.defer {
            db.quoteDao().clearQuotes()
            Completable.complete()
        }
    }

    /**
     * Save the given [QuoteModelCache] instances to the database.
     */
    override fun saveQuote(quote: QuoteModelData): Completable {
        return Completable.defer {
            db.quoteDao().insertQuote(entityMapper.mapToCached(quote))
            Completable.complete()
        }
    }

    /**
     * Retrieve a list of [QuoteModelData] instances from the database.
     */
    override fun getQuotes(): Flowable<List<QuoteModelData>> {
        return Flowable.defer {
            Flowable.just(db.quoteDao().getQuotes())
        }.map { it.map { entityMapper.mapFromCached(it) } }
    }

    /**
     * Check whether there are instances of [QuoteModelCache] stored in the cache.
     */
    override fun isCached(): Single<Boolean> {
        return Single.defer {
            Single.just(db.quoteDao().getQuotes().isNotEmpty())
        }
    }
}