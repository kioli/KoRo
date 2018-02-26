package kioli.koro.ui.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kioli.koro.ui.di.scope.PerActivity
import kioli.koro.ui.activity.IntroActivity

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [(IntroActivityModule::class)])
    abstract fun bindIntroActivity(): IntroActivity

}