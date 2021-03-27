package com.ben.base.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseHolder<*>>(), AdapterListener<T> {

    private var data: ArrayList<T> = ArrayList()
    var onItemClick: (item: T, position: Int) -> Unit = { _, _ -> }

    override fun onBindViewHolder(holder: BaseHolder<*>, position: Int) {
        holder.bindTo(data[position])
        holder.listener = this
    }

    override fun getItemCount(): Int {
        return data.size
    }

    open fun setData(data: ArrayList<T>) {
        this.data = data
    }

    open fun getData(): List<T> = data

    override fun itemClick(item: T, position: Int) {
        onItemClick.invoke(item, position)
    }
}