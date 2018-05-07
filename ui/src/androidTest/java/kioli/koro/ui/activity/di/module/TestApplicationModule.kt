package kioli.koro.ui.activity.di.module

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import kioli.koro.ui.di.scope.PerApplication
import org.mockito.Mockito.mock

@Module
class TestApplicationModule {

    @Provides
    @PerApplication
    internal fun provideFirebaseAuthenticator() = mock(FirebaseAuth::class.java)
}