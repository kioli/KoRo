package kioli.koro.ui.di.module

import dagger.Module
import dagger.Provides
import kioli.koro.presentation.viewmodel.IntroViewModel
import kioli.koro.presentation.viewmodel.LoginViewModel
import kioli.koro.presentation.viewmodel.ViewModelFactory

/**
 * Module used to provide dependencies to the [LoginActivity]
 */
@Module
open class LoginActivityModule {

    @Provides
    fun provideLoginViewModelFactory(introViewModel: IntroViewModel, loginViewModel: LoginViewModel) =
            ViewModelFactory(introViewModel, loginViewModel)
}
