package kioli.koro.domain.interactor

import io.reactivex.Flowable
import kioli.koro.domain.executor.PostExecutionThread
import kioli.koro.domain.executor.ThreadExecutor
import kioli.koro.domain.interactor.base.FlowableUseCase
import kioli.koro.domain.model.QuoteModelDomain
import kioli.koro.domain.repository.QuoteRepository
import javax.inject.Inject

/**
 * Use case used for loading a [QuoteModelDomain]
 */
open class LoadQuoteUseCase @Inject constructor(val repository: QuoteRepository,
                                                threadExecutor: ThreadExecutor,
                                                postExecutionThread: PostExecutionThread) :
        FlowableUseCase<QuoteModelDomain, Void>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Flowable<QuoteModelDomain> {
        return repository.loadQuote()
    }
}