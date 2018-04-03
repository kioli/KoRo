package kioli.koro.ui.di.module

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import kioli.koro.presentation.viewmodel.LoginViewModel
import kioli.koro.presentation.viewmodel.ViewModelFactory

/**
 * Module used to provide dependencies to the [LoginActivity]
 */
@Module
open class LoginActivityModule {

    @Provides
    fun provideLoginViewModelFactory(loginViewModel: LoginViewModel) =
            ViewModelFactory(loginViewModel = loginViewModel)

    @Provides
    fun provideFirebaseAuthentication(): FirebaseAuth = FirebaseAuth.getInstance()
}
