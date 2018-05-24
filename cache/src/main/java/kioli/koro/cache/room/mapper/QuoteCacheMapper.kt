package kioli.koro.cache.room.mapper

import kioli.koro.cache.room.model.QuoteModelCache
import kioli.koro.data.model.QuoteModelData

/**
 * Map [QuoteModelCache] to and from a [QuoteModelData] when data moves between this and the DATA layer
 */
open class QuoteCacheMapper : CacheMapper<QuoteModelCache, QuoteModelData> {

    /**
     * Map a [QuoteModelData] instance to a [QuoteModelCache] instance
     * @param type quote model coming form the DATA layer
     * @return quote model proper of the CACHE layer
     */
    override fun mapToCached(type: QuoteModelData): QuoteModelCache {
        return QuoteModelCache(0, type.text)
    }

    /**
     * Map a [QuoteModelCache] instance to a [QuoteModelData] instance
     * @param type quote model coming from the CACHE layer
     * @return quote model proper of the DATA layer
     */
    override fun mapFromCached(type: QuoteModelCache): QuoteModelData {
        return QuoteModelData(type.text)
    }

}