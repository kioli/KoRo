package kioli.koro.ui

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import kioli.koro.domain.executor.PostExecutionThread

/**
 * MainThread (UI Thread) implementation based on a [Scheduler]
 * which will execute actions on the Android UI thread
 */
internal class UiThread : PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()

}