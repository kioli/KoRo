package kioli.koro.ui.activity.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import kioli.koro.ui.activity.di.module.TestActivityBindingModule
import kioli.koro.ui.activity.di.module.TestApplicationModule
import kioli.koro.ui.di.component.LMComponent
import kioli.koro.ui.di.scope.PerApplication

@PerApplication
@Component(modules = [(TestActivityBindingModule::class), (TestApplicationModule::class), (AndroidSupportInjectionModule::class)])
interface TestApplicationComponent: LMComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestApplicationComponent
    }
}
