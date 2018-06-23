package kk.com.datashare

abstract class IRequesterImpl<T> : IRequester<T> {


    private var dataHolder: IDataHolder<T>? = null

    final override fun bindDataHolder(dataHolder: IDataHolder<T>) {
        this.dataHolder = dataHolder
    }




    override fun doRequest() {
        dataHolder?.doRequest()
        onRequest()
    }

    abstract fun onRequest()


    final override fun setResult(data: T) {
        dataHolder?.setData(data)
    }

}