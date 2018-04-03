package kioli.koro.ui.di.module

import dagger.Module
import dagger.Provides
import kioli.koro.presentation.viewmodel.IntroViewModel
import kioli.koro.presentation.viewmodel.ViewModelFactory

/**
 * Module used to provide dependencies to the [IntroActivity]
 */
@Module
open class IntroActivityModule {

    @Provides
    fun provideIntroViewModelFactory(introViewModel: IntroViewModel) =
            ViewModelFactory(introViewModel = introViewModel)
}
