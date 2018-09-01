package kk.com.app

internal class NetWorker {
    companion object {
        fun <T> doRequest(param: String, successFunction: (T?) -> Unit, failFunction: (String?) -> Unit) {
            if (true) {
                successFunction("成功" as T)
            } else {
                failFunction("失败")
            }
        }
    }
}

