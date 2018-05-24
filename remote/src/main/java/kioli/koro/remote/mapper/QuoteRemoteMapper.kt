package kioli.koro.remote.mapper

import kioli.koro.data.model.QuoteModelData
import kioli.koro.remote.model.QuoteModelRemote
import javax.inject.Inject

/**
 * Map [QuoteModelRemote] to and from a [QuoteModelData] when data moves between this and the DATA layer
 */
open class QuoteRemoteMapper constructor() : RemoteMapper<QuoteModelRemote, QuoteModelData> {

    /**
     * Map a [QuoteModelRemote] to a [QuoteModelData] instance
     * @param type quote model coming from the REMOTE layer
     * @return quote model proper of the DATA layer
     */
    override fun mapFromRemote(type: QuoteModelRemote): QuoteModelData {
        return QuoteModelData(type.text)
    }

}