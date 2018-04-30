package kioli.koro.ui.di.module

import dagger.Module
import dagger.Provides
import kioli.koro.presentation.viewmodel.QuoteViewModel
import kioli.koro.presentation.viewmodel.ViewModelFactory

/**
 * Module used to provide dependencies to the [QuoteActivity]
 */
@Module
open class QuoteActivityModule {

    @Provides
    fun provideQuoteViewModelFactory(quoteViewModel: QuoteViewModel) =
            ViewModelFactory(quoteViewModel = quoteViewModel)
}
