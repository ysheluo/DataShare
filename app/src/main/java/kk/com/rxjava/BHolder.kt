package kk.com.rxjava

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class BHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    private val textView: TextView = itemView!!.findViewById(R.id.tv)


    fun onBind(data: String) {
        textView.text = data
    }


}