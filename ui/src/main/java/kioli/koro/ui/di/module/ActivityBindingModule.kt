package kioli.koro.ui.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kioli.koro.ui.activity.IntroActivity
import kioli.koro.ui.activity.LoginActivity
import kioli.koro.ui.di.scope.PerActivity

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [(IntroActivityModule::class)])
    abstract fun bindIntroActivity(): IntroActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [(LoginActivityModule::class)])
    abstract fun bindLoginActivity(): LoginActivity

}