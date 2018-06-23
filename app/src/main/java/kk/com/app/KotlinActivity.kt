package kk.com.app

import android.app.Activity
import android.os.Bundle
import android.util.Log
import io.reactivex.disposables.Disposable
import kk.com.datashare.DataManager
import kk.com.datashare.IListener
import kk.com.rxjava.R

class KotlinActivity : Activity() {


    private lateinit var disposable: Disposable

    var createKey: Int = 0

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        createKey = DataManager.createKey()
        DataManager.request(createKey, NetShareRequester<Test.A>(GoodsDetailParamCreator("新建")))
        DataManager.request(createKey, NetShareRequester<Test.A>(GoodsDetailParamCreator("刷新")))

        DataManager.request(createKey, NetShareRequester<Test.A>(GoodsDetailParamCreator("过来")))

        DataManager.listen(createKey, object : IListener<String> {

            override fun result(data: String, cause: String) {
                Log.e("=====", "哈哈哈哈$data$cause")
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