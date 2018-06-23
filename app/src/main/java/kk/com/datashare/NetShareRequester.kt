package kk.com.datashare


class NetShareRequester<T>(private val paramCreator: NetPramCreator) : IRequesterImpl<T>() {

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