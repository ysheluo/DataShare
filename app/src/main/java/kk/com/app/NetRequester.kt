package kk.com.app

import kk.com.datashare.AbsRequester

abstract class NetRequester<T> : AbsRequester<T>() {

    override fun onDoRequest(successFunction: (T?) -> Unit, failFunction: (failData: String?) -> Unit) {
        NetWorker.doRequest<T>(createParams(), {
            successFunction(it)
        }, {
            failFunction(it)
        })
    }

    abstract fun createParams(): String

}