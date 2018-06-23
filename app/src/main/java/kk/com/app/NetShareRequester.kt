package kk.com.app

import kk.com.datashare.IRequesterImpl
import kk.com.datashare.NetPramCreator


class NetShareRequester<T>(private val paramCreator: NetPramCreator) : IRequesterImpl<T>() {

    override fun requestCause(): String {
       return paramCreator.createCause()
    }

    override fun key(): String {
        return paramCreator.key()
    }

    override fun onRequest() {
        NetManager.query("", "", object : NetManager.ICallBack<T> {
            override fun onSuccess(data: T) {
                setResult(data)
            }
        })
    }
}