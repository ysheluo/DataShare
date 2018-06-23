package kk.com.datashare

interface IRequester<T> {

    fun bindDataHolder(dataHolder: IDataHolder<T>)


    fun doRequest()


    fun setResult(data: T)

    fun key(): String

    fun requestCause(): String

}