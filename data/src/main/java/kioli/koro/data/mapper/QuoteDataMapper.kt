package kioli.koro.data.mapper

import kioli.koro.data.model.QuoteModelData
import kioli.koro.domain.model.QuoteModelDomain
import javax.inject.Inject

/**
 * Map [QuoteModelData] to and from a [QuoteModelDomain] when data moves between this and the DOMAIN layer
 */
open class QuoteDataMapper @Inject constructor() : DataMapper<QuoteModelData, QuoteModelDomain> {

    /**
     * Map a [QuoteModelData] instance to a [QuoteModelDomain] instance
     * @param type quote model coming form the DATA layer
     * @return quote model proper of the DOMAIN layer
     */
    override fun mapFromData(type: QuoteModelData): QuoteModelDomain {
        return QuoteModelDomain(type.text)
    }

    /**
     * Map a [QuoteModelDomain] instance to a [QuoteModelData] instance
     * @param type quote model coming from the DOMAIN layer
     * @return quote model proper of the DATA layer
     */
    override fun mapToData(type: QuoteModelDomain): QuoteModelData {
        return QuoteModelData(type.text)
    }


}