package kk.com.datashare

import android.text.TextUtils

internal class DataHolder<DATA> {

    companion object {
        const val NOT_BEGIN = 0X001
        const val START = 0X002
        const val LOADING = 0X003
        const val FAIL = 0X004
        const val SUCCESS = 0X005
    }

    var mData: DATA? = null
    private var mCause: String = ""
    private var mFailResult: String? = null
    private var mStatus = NOT_BEGIN


    private val mListeners = ArrayList<Listener<DATA>>()


    fun register(iListener: Listener<DATA>) {
        val isContains = mListeners.contains(iListener)
        if (!isContains) {
            mListeners.add(iListener)
        }
        giveResult(iListener)
    }

    private fun giveResult(iListener: Listener<DATA>) {
        if (mStatus == SUCCESS && mData != null) {
            successResult(iListener)
        } else if (mStatus == FAIL && TextUtils.isEmpty(mFailResult)) {
            iListener.onFail(mFailResult, mCause)
        }
        if (iListener.isListenOnce) {
            unRegister(iListener)
        }
    }

    private fun successResult(iListener: Listener<DATA>) {
        iListener.onSuccess(mCause)
    }

    fun onSetSuccessData(data: DATA?, cause: String) {
        this.mCause = cause
        this.mData = data
        mStatus = SUCCESS
        mListeners.forEach {
            giveResult(it)
        }
    }

    fun onFail(data: String?, cause: String) {
        mFailResult = data
        mCause = cause
        mStatus = FAIL
        mListeners.forEach {
            giveResult(it)
        }
    }

    fun unRegister(listener: Listener<DATA>) {
        mListeners.remove(listener)
    }

    fun start() {
        mStatus = START
    }
}