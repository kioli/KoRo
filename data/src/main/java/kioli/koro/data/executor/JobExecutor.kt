package kioli.koro.data.executor

import kioli.koro.domain.executor.ThreadExecutor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Decorated [ThreadPoolExecutor]
 */
open class JobExecutor @Inject constructor() : ThreadExecutor {

    private val initialPoolSize = 3
    private val maxPoolSize = 5
    private val keepAlieThreadTime = 10
    private val keepAliveTimeUnit = TimeUnit.SECONDS

    private val workQueue: LinkedBlockingQueue<Runnable> = LinkedBlockingQueue()
    private val threadPoolExecutor: ThreadPoolExecutor
    private val threadFactory: ThreadFactory

    init {
        threadFactory = JobThreadFactory()
        threadPoolExecutor = ThreadPoolExecutor(initialPoolSize, maxPoolSize,
                keepAlieThreadTime.toLong(), keepAliveTimeUnit, this.workQueue, this.threadFactory)
    }

    override fun execute(runnable: Runnable?) {
        if (runnable == null) {
            throw IllegalArgumentException("Runnable to execute cannot be null")
        }
        threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {
        private val threadName = "android_"
        private var counter = 0

        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, threadName + counter++)
        }
    }
}