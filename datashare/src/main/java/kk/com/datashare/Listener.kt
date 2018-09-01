package kk.com.datashare

internal class Listener<T>(private val iContainer: DataHolder<T>) : IListener<T> {


    private var mSuccess: ((T?, cause: String) -> Unit?)? = null

    private var mFail: ((failData: String?, cause: String?) -> Unit)? = null

    var isListenOnce = false


    override fun result(resultKey: String): T? {
        return iContainer.mData
    }


    override fun success(result: (T?, cause: String) -> Unit): IListener<T> {
        mSuccess = result
        return this
    }


    override fun fail(result: (failData: String?, cause: String?) -> Unit): IListener<T> {
        this.mFail = result
        return this
    }


    fun listen() {
        iContainer.register(this)
    }

    fun onSuccess(cause: String) {
        mSuccess?.invoke(iContainer.mData, cause)
        if (isListenOnce) {
            iContainer.unRegister(this)
        }
    }


    fun onFail(data: String?, cause: String) {
        mFail?.let {
            it(data, cause)
        }
        if (isListenOnce) {
            iContainer.unRegister(this)
        }
    }

    override fun listenOnce() {
        isListenOnce = true
    }
}