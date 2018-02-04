package kioli.koro.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.subscribers.DisposableSubscriber
import kioli.koro.domain.interactor.ClearQuoteUseCase
import kioli.koro.domain.interactor.LoadQuoteUseCase
import kioli.koro.domain.interactor.SaveQuoteUseCase
import kioli.koro.domain.model.QuoteModelDomain
import kioli.koro.presentation.data.Resource
import kioli.koro.presentation.data.ResourceState
import kioli.koro.presentation.mapper.QuotePresentationMapper
import kioli.koro.presentation.model.QuoteModelPresentation
import javax.inject.Inject

open class IntroViewModel @Inject internal constructor(
        private val loadQuoteUseCase: LoadQuoteUseCase,
        private val saveQuoteUseCase: SaveQuoteUseCase,
        private val clearQuoteUseCase: ClearQuoteUseCase,
        private val mapper: QuotePresentationMapper) : ViewModel() {

    private val quoteLiveData: MutableLiveData<Resource<QuoteModelPresentation>> = MutableLiveData()

    override fun onCleared() {
        loadQuoteUseCase.dispose()
        saveQuoteUseCase.unsubscribe()
        clearQuoteUseCase.unsubscribe()
        super.onCleared()
    }

    fun getLiveData(): LiveData<Resource<QuoteModelPresentation>> {
        return quoteLiveData
    }

    fun loadQuote() {
        quoteLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        loadQuoteUseCase.execute(LoadQuoteSubscriber())
    }

    inner class LoadQuoteSubscriber : DisposableSubscriber<QuoteModelDomain>() {

        override fun onComplete() {}

        override fun onNext(quote: QuoteModelDomain?) {
            quote?.let {
                quoteLiveData.postValue(Resource(ResourceState.SUCCESS, mapper.mapToPresentation(it), null))
            }
        }

        override fun onError(exception: Throwable) {
            quoteLiveData.postValue(Resource(ResourceState.ERROR, null, exception.localizedMessage))
        }
    }

    fun saveQuote(text: String) {
        val quoteModel = QuoteModelPresentation(text)
        saveQuoteUseCase.execute(SaveQuoteSubscriber(), mapper.mapFromPresentation(quoteModel))
    }

    inner class SaveQuoteSubscriber : DisposableCompletableObserver() {

        override fun onComplete() {
        }

        override fun onError(exception: Throwable) {
            quoteLiveData.postValue(Resource(ResourceState.ERROR, null, exception.localizedMessage))
        }
    }

    fun clearQuotes() {
        clearQuoteUseCase.execute(ClearQuoteSubscriber(), "")
    }

    inner class ClearQuoteSubscriber : DisposableCompletableObserver() {

        override fun onComplete() {
            quoteLiveData.postValue(Resource(ResourceState.SUCCESS, null, null))
        }

        override fun onError(exception: Throwable) {
            quoteLiveData.postValue(Resource(ResourceState.ERROR, null, exception.localizedMessage))
        }
    }
}