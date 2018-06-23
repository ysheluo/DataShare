package kk.com.datashare

/**
 * 数据存储
 */
interface IDataHolder<T> {

    fun setData(data: T)

    fun key(): String

    fun registerListener(listener: IListener<T>)

    fun doRequest()


    fun unRegisterListener(listener: IListener<T>)

}