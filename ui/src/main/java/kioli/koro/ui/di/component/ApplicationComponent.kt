package kioli.koro.ui.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import kioli.koro.ui.App
import kioli.koro.ui.di.module.ActivityBindingModule
import kioli.koro.ui.di.module.ApplicationModule
import kioli.koro.ui.di.scope.PerApplication

@PerApplication
@Component(modules = [(ActivityBindingModule::class), (ApplicationModule::class), (AndroidSupportInjectionModule::class)])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: App)
}
