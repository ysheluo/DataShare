package kk.com.rxjava

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import io.reactivex.disposables.Disposable
import kk.com.app.Test
import kk.com.datashare.DataManager
import kk.com.datashare.GoodsDetailParamCreator
import kk.com.datashare.IListener
import kk.com.datashare.NetShareRequester
import java.util.*

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


    class A {

        var tt = "啦啦啦啦"


    }


    private fun list() {
        recyclerView = findViewById(R.id.recycler)
        button = findViewById(R.id.button)

        list = ArrayList()
        for (item: Int in 0..1000) {
            list.add(item.toString() + "aaaaaa")
        }

        recyclerView.adapter = RecyclerAdapter(list)


        button.setOnClickListener {

            //            list.removeAt(90)

            //            recyclerView.adapter.notifyItemRemoved(90)
            list.clear()
            for (item: Int in 0..50) {
                list.add(item.toString() + "aaaaaa")
            }
            recyclerView.adapter.notifyItemRangeChanged(100, 1000)


            //            recyclerView.adapter.notifyDataSetChanged()
        }

        val layoutManager = AA(this, recyclerView.adapter)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter.notifyDataSetChanged()
    }


}