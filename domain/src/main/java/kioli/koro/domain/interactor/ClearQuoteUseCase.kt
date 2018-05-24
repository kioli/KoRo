package kioli.koro.domain.interactor

import io.reactivex.Completable
import kioli.koro.domain.executor.PostExecutionThread
import kioli.koro.domain.executor.ThreadExecutor
import kioli.koro.domain.interactor.base.CompletableUseCase
import kioli.koro.domain.model.QuoteModelDomain
import kioli.koro.domain.repository.QuoteRepository

/**
 * Use case used for clearing all stored [QuoteModelDomain]
 */
open class ClearQuoteUseCase(val repository: QuoteRepository,
                             threadExecutor: ThreadExecutor,
                             postExecutionThread: PostExecutionThread) :
        CompletableUseCase<String>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: String): Completable {
        return repository.clearQuotes()
    }
}