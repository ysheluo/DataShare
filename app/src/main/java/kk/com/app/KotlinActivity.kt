package kk.com.app

import android.app.Activity
import android.os.Bundle
import android.util.Log
import kk.com.datashare.*

class KotlinActivity : Activity() {


    private var createKey: Int = 0

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        createKey = DataShareManager.createKey()

//        listen<String>(createKey, DemoRequester.TAG).success { result, cause ->
//            Log.e("哈哈", result + cause)
//        }

        doRequest(DemoRequester(), createKey, "init")
        listenOnce<String>(createKey,DemoRequester.TAG).success{ result, cause ->
            Log.e("哈哈", result + cause)
        }
    }

    @Override
    override fun onDestroy() {
        super.onDestroy()
        DataShareManager.destroy(createKey)
    }
}