package kioli.koro.remote

import io.reactivex.Flowable
import kioli.koro.data.model.QuoteModelData
import kioli.koro.data.repository.QuoteRemote
import kioli.koro.remote.mapper.QuoteRemoteMapper
import javax.inject.Inject

/**
 * Remote implementation for retrieving User instances. This class implements the
 * [QuoteRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class QuoteRemoteImpl @Inject constructor(private val service: QuoteService,
                                          private val mapper: QuoteRemoteMapper) :
        QuoteRemote {

    /**
     * Retrieve a list of [QuoteModelData] instances from the [QuoteService].
     */
    override fun getQuotes(): Flowable<QuoteModelData> = service.loadQuote().map { mapper.mapFromRemote(it) }

}