package kk.com.datashare

import android.util.SparseArray
import java.util.concurrent.atomic.AtomicInteger

object DataShareManager {

    private val mArrays = SparseArray<ShareContainer>()

    @Synchronized
    internal fun getContainerByKey(key: Int): ShareContainer {
        var shareContainer = mArrays[key]
        if (shareContainer == null) {
            shareContainer = ShareContainer()
            mArrays.put(key, shareContainer)
        }
        return shareContainer
    }


    fun destroy(key: Int) {
        val shareContainer = mArrays[key]
        shareContainer?.let {
            it.destroy()
            mArrays.remove(key)
        }
    }

    fun createKey(): Int {
        return AtomicInteger().incrementAndGet()
    }

    fun <T> doRequest(requester: AbsRequester<T>, key: Int, cause: String) {
        requester.doRequest(key, cause)
    }
}