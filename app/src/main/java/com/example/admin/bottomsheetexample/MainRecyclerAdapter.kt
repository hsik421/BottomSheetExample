package com.example.admin.bottomsheetexample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.item_bottom.view.*

class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {
    interface Click{
        fun onItemClick(text : String)
    }
    var itemClick : Click? = null
    private val dummyDatas = arrayListOf("one","two","three","for","five","six")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bottom,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dummyDatas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView){
            textView.text = dummyDatas[position]
            clicks().subscribe { itemClick?.onItemClick(dummyDatas[position]) }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}