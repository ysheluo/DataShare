package kk.com.datashare

interface IListener<T> {

    fun result(resultKey: String): T?

    fun success(result: (T?, cause: String) -> Unit): IListener<T>

    fun fail(result: (failData: String?, cause: String?) -> Unit): IListener<T>

    fun listenOnce()

}