package kk.com.app

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import io.reactivex.disposables.Disposable
import kk.com.datashare.DataManager
import kk.com.datashare.IListener
import kk.com.rxjava.R

class KotlinActivity : Activity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var button: Button

    private lateinit var list: MutableList<String>

    private lateinit var disposable: Disposable

    var createKey: Int = 0

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        createKey = DataManager.createKey()
        DataManager.request(createKey, NetShareRequester<Test.A>(GoodsDetailParamCreator()))


        DataManager.listen(createKey, object : IListener<String> {

            override fun result(data: String) {

                Log.e("=====", "哈哈哈哈$data")
            }

            override fun key(): String {
                return GoodsDetailParamCreator.TAG
            }
        })
    }

    @Override
    override fun onDestroy() {
        super.onDestroy()
        DataManager.destroy(createKey)
    }


}