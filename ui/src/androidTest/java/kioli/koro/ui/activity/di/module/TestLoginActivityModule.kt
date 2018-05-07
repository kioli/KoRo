package kioli.koro.ui.activity.di.module

import dagger.Module
import dagger.Provides
import kioli.koro.presentation.viewmodel.ViewModelFactory
import org.mockito.Mockito.mock

/**
 * Module used to provide dependencies to the [LoginActivity] under TEST
 */
@Module
class TestLoginActivityModule {

    @Provides
    fun provideLoginViewModelFactory() = mock(ViewModelFactory::class.java)
}
