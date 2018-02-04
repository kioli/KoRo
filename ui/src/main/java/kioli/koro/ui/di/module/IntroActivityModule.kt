package kioli.koro.ui.di.module

import dagger.Module
import dagger.Provides
import kioli.koro.domain.interactor.ClearQuoteUseCase
import kioli.koro.domain.interactor.LoadQuoteUseCase
import kioli.koro.domain.interactor.SaveQuoteUseCase
import kioli.koro.presentation.mapper.QuotePresentationMapper
import kioli.koro.presentation.viewmodel.ViewModelFactory

/**
 * Module used to provide dependencies to the [IntroActivity]
 */
@Module
open class IntroActivityModule {

    @Provides
    fun provideIntroViewModelFactory(loadQuoteUseCase: LoadQuoteUseCase,
                                     saveQuoteUseCase: SaveQuoteUseCase,
                                     clearQuoteUseCase: ClearQuoteUseCase,
                                     mapper: QuotePresentationMapper) = ViewModelFactory(loadQuoteUseCase,
            saveQuoteUseCase,
            clearQuoteUseCase,
            mapper)

}
