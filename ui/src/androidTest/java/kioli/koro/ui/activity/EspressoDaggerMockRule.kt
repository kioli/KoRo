package kioli.koro.ui.activity

import android.support.test.InstrumentationRegistry
import it.cosenonjaviste.daggermock.DaggerMock
import kioli.koro.ui.App
import kioli.koro.ui.di.component.ApplicationComponent
import kioli.koro.ui.di.module.ApplicationModule
import kioli.koro.ui.di.module.LoginActivityModule

fun espressoDaggerMockRule() = DaggerMock.rule<ApplicationComponent>(ApplicationModule(),
        LoginActivityModule()) {
    set { component -> app.component = component }
}

val app: App get() = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App