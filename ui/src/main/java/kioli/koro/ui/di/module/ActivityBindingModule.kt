package kioli.koro.ui.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kioli.koro.ui.activity.QuoteActivity
import kioli.koro.ui.di.scope.PerActivity

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [(QuoteActivityModule::class)])
    abstract fun bindQuoteActivity(): QuoteActivity

}