package com.ben.base.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BaseHolder<V : ViewBinding>(private val binding: V) : RecyclerView.ViewHolder(binding.root) {

    var listener: AdapterListener<*>? = null

    open fun bindTo(o: Any?) {}

    open fun unbindTo() {

    }
}