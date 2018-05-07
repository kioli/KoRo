package kioli.koro.ui.activity.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kioli.koro.ui.activity.LoginActivity
import kioli.koro.ui.di.scope.PerActivity

@Module
abstract class TestActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [(TestLoginActivityModule::class)])
    abstract fun bindLoginActivity(): LoginActivity

}