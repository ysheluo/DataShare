package kk.com.datashare

/**
 * 数据监听回调
 */
interface IListener<T> {

    fun key(): String

    fun result(data: T)

}