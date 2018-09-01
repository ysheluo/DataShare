package kk.com.app

class DemoRequester : NetRequester<String>() {

    companion object {
        const val TAG = "DemoRequester"
    }

    override fun createParams(): String {
        return ""
    }

    override fun tag(): String {
        return TAG
    }
}