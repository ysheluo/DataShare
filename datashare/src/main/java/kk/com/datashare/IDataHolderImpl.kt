package kk.com.datashare


class IDataHolderImpl<T>(var key: String) : IDataHolder<T> {


    companion object {
        val NOT_STRT = 1
        val REQUESTING: Int = 2
        val END: Int = 3
    }


    private val listeners: MutableList<IListener<T>> = ArrayList()

    private var state: Int = NOT_STRT

    override fun doRequest() {
        state = REQUESTING
    }


    override fun registerListener(listener: IListener<T>) {
        if (data != null && state == END) {
            listener.result(this.data as T)
        }
        listeners.add(listener)
    }


    override fun unRegisterListener(listener: IListener<T>) {
        listeners.remove(listener)
    }


    override fun key(): String {
        return key
    }

    private var data: T? = null

    override fun setData(data: T) {
        this.data = data
        listeners.forEach {
            it.result(data)
        }
    }
}