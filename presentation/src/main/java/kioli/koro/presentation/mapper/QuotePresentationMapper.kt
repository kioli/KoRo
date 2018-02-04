package kioli.koro.presentation.mapper

import kioli.koro.domain.model.QuoteModelDomain
import kioli.koro.presentation.model.QuoteModelPresentation
import javax.inject.Inject

/**
 * Map [QuoteModelPresentation] to and from a [QuoteModelDomain] when data moves between this and the DOMAIN layer
 */
open class QuotePresentationMapper @Inject constructor() : PresentationMapper<QuoteModelPresentation, QuoteModelDomain> {

    /**
     * Map a [QuoteModelPresentation] instance to a [QuoteModelDomain] instance
     * @param type quote model coming from the PRESENTATION layer
     * @return quote model proper of the DOMAIN layer
     */
    override fun mapFromPresentation(type: QuoteModelPresentation): QuoteModelDomain {
        return QuoteModelDomain(type.text)
    }

    /**
     * Map a [QuoteModelDomain] instance to a [QuoteModelPresentation] instance
     * @param type quote model coming from the DOMAIN layer
     * @return quote model proper of the PRESENTATION layer
     */
    override fun mapToPresentation(type: QuoteModelDomain): QuoteModelPresentation {
        return QuoteModelPresentation(type.text)
    }

}