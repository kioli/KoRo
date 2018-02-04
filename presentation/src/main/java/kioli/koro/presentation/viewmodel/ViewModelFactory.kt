package kioli.koro.presentation.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import kioli.koro.domain.interactor.ClearQuoteUseCase
import kioli.koro.domain.interactor.LoadQuoteUseCase
import kioli.koro.domain.interactor.SaveQuoteUseCase
import kioli.koro.presentation.mapper.QuotePresentationMapper

open class ViewModelFactory(
        private val loadQuoteUseCase: LoadQuoteUseCase,
        private val saveQuoteUseCase: SaveQuoteUseCase,
        private val clearQuoteUseCase: ClearQuoteUseCase,
        private val mapper: QuotePresentationMapper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IntroViewModel::class.java)) {
            return IntroViewModel(loadQuoteUseCase, saveQuoteUseCase, clearQuoteUseCase, mapper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}