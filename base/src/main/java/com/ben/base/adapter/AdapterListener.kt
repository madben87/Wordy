package com.ben.base.adapter

interface AdapterListener<T> {

    fun itemClick(item: T, position: Int)
}