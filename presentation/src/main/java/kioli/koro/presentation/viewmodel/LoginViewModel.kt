package kioli.koro.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.microsoft.appcenter.analytics.Analytics
import io.reactivex.subscribers.DisposableSubscriber
import kioli.koro.domain.interactor.LoginUseCase
import kioli.koro.domain.model.UserModelDomain
import kioli.koro.presentation.Events
import kioli.koro.presentation.data.Resource
import kioli.koro.presentation.data.ResourceState
import kioli.koro.presentation.eventResult
import kioli.koro.presentation.mapper.UserPresentationMapper
import kioli.koro.presentation.model.UserModelPresentation
import javax.inject.Inject

open class LoginViewModel @Inject internal constructor(
        private val loginUseCase: LoginUseCase,
        private val mapper: UserPresentationMapper) : ViewModel() {

    private val userLiveData: MutableLiveData<Resource<UserModelPresentation>> = MutableLiveData()

    override fun onCleared() {
        loginUseCase.dispose()
        super.onCleared()
    }

    fun getLiveData(): LiveData<Resource<UserModelPresentation>> = userLiveData

    fun goToNextScreen() {
        userLiveData.postValue(Resource(ResourceState.SUCCESS, UserModelPresentation("", ""), null))
//        userLiveData.postValue(Resource(ResourceState.LOADING, null, null))
//        loginUseCase.execute(LoginSubscriber())
//        Analytics.trackEvent(Events.LoadQuoteStart.id)
    }

//    inner class LoginSubscriber : DisposableSubscriber<UserModelDomain>() {
//
//        override fun onComplete() {}
//
//        override fun onNext(quote: UserModelDomain?) {
//            Analytics.trackEvent(Events.LoadQuoteResultOk.id, mutableMapOf(Pair(eventResult, quote.toString())))
//            quote?.let {
//                userLiveData.postValue(Resource(ResourceState.SUCCESS, mapper.mapToPresentation(it), null))
//            }
//        }
//
//        override fun onError(exception: Throwable) {
//            Analytics.trackEvent(Events.LoadQuoteResultFail.id, mutableMapOf(Pair(eventResult, exception.localizedMessage)))
//            userLiveData.postValue(Resource(ResourceState.ERROR, null, exception.localizedMessage))
//        }
//    }
}