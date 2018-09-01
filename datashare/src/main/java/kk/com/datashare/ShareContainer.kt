package kk.com.datashare

internal class ShareContainer {

    private val mDataHolderMap = HashMap<String, DataHolder<*>>()

    fun <T> getDataHolder(resultKey: String): DataHolder<T> {
        var iContainer = mDataHolderMap[resultKey]
        if (iContainer == null) {
            iContainer = DataHolder<T>()
            mDataHolderMap[resultKey] = iContainer
        }
        return iContainer as DataHolder<T>

    }


    @Synchronized
    fun <T> listen(resultKey: String): IListener<T> {
        val listener = Listener(getDataHolder<T>(resultKey))
        listener.listen()
        return listener
    }


    @Synchronized
    fun <T> listenOnce(resultKey: String): IListener<T> {
        val listener = Listener(getDataHolder<T>(resultKey))
        listener.listenOnce()
        return listener
    }

    fun destroy() {
        mDataHolderMap.clear()
    }


}