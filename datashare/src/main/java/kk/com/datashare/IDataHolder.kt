package kk.com.datashare

/**
 * 数据存储
 */
interface IDataHolder<T> {

    fun setResult(data: T)

    fun key(): String

    fun registerListener(listener: IListener<T>)

    fun doRequest(cause:String)

    fun unRegisterListener(listener: IListener<T>)

    fun getResult(): T?

}