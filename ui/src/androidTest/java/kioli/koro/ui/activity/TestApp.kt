package kioli.koro.ui.activity

import kioli.koro.ui.App
import kioli.koro.ui.activity.di.component.DaggerTestApplicationComponent
import kioli.koro.ui.di.component.LMComponent

open class TestApp : App() {

    override fun createComponent(): LMComponent = DaggerTestApplicationComponent
            .builder()
            .application(this)
            .build()
}