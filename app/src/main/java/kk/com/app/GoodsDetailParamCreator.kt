package kk.com.app

import kk.com.datashare.NetPramCreator
import org.json.JSONObject

class GoodsDetailParamCreator : NetPramCreator {

    companion object {
        const val TAG: String = "GoodsDetailParamCreator"
    }

    override fun key(): String {
        return TAG
    }

    override fun provideParam(): String {
        val jsonObject = JSONObject()
        jsonObject.put("", "")
        return jsonObject.toString()
    }
}