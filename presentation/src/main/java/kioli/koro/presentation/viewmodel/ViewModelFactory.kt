package kioli.koro.presentation.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

open class ViewModelFactory(
        private val quoteViewModel: QuoteViewModel? = null,
        private val loginViewModel: LoginViewModel? = null) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuoteViewModel::class.java)) {
            return quoteViewModel!! as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return loginViewModel!! as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}