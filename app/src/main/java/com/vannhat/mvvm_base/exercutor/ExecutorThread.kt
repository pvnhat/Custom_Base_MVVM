package com.vannhat.mvvm_base.exercutor

import java.util.concurrent.*
import javax.inject.Inject

/**
 * Specialize Thread to execute the background work
 */
class ExecutorThread @Inject constructor() : Executor {

    private val workQueue = LinkedBlockingDeque<Runnable>()
    private val threadFactory = ExecutorThreadFactory()
    private val threadPoolExecutor = ThreadPoolExecutor(
        CORE_POOL_SIZE,
        MAX_POOL_SIZE,
        KEEP_TIME_ALIVE,
        TimeUnit.SECONDS,
        workQueue,
        threadFactory
    )

    override
    fun execute(command: Runnable?) {
        command?.let {
            threadPoolExecutor.execute(command)
        }
    }

    private class ExecutorThreadFactory : ThreadFactory {

        private var threadCount = 0

        override fun newThread(r: Runnable?): Thread {
            return Thread(r, THREAD_NAME + threadCount++)
        }

        companion object {
            const val THREAD_NAME = "app_thread_"
        }
    }

    companion object {
        private const val KEEP_TIME_ALIVE = 10L
        private const val MAX_POOL_SIZE = 5
        private const val CORE_POOL_SIZE = 3
    }
}
