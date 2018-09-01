package kk.com.datashare

abstract class AbsRequester<T> : IRequester {


    final override fun doRequest(key: Int, cause: String) {
        val mDataHolder = DataShareManager.getContainerByKey(key).getDataHolder<T>(tag())
        mDataHolder.start()
        onDoRequest({
            mDataHolder.onSetSuccessData(it, cause)
        }, {
            mDataHolder.onFail(it, cause)
        })
    }

    abstract fun onDoRequest(successFunction: (T?) -> Unit, failFunction: (failData: String?) -> Unit)
}