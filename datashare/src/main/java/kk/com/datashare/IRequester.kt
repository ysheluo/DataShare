package kk.com.datashare

interface IRequester {

    fun doRequest(key: Int, cause: String)

    fun tag(): String
}