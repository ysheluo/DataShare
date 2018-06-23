package kk.com.rxjava

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class RecyclerAdapter(list: List<String>) : RecyclerView.Adapter<BHolder>() {

    private var list: List<String> = list
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BHolder {
        var item = LayoutInflater.from(parent!!.context).inflate(R.layout.item_layout, parent, false)

        return BHolder(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BHolder?, position: Int) {
        holder?.onBind(list[position])
    }
}


