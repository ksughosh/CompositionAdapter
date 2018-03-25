package com.github.ksughosh.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup

/**
* Copyright (c) Apache License 2.0
* Created by SughoshKumar on 10/12/17.
*/
open class ViewDelegate<T: ListItem, out U: ListViewType<T>>(private val itemView: (ViewGroup?) -> U,
                                                             private val forType: (Any?) -> Boolean) {
    var viewType: Int = Int.MAX_VALUE - 1
    fun onBindViewHolder(holder: RecyclerView.ViewHolder?, items: List<ListItem>?, position: Int, payload: MutableList<Any>? = null) {
        val item = items?.get(position)
        (holder?.itemView as? ListViewType<T>)?.mData = item as T
    }

    fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        Log.d("BINDER", "CreateViewHolder")
        return BaseViewHolder(itemView(parent))
    }

    open fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder?) {}

    open fun forItemViewType(item: Any?): Boolean =  forType(item)

    open fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder?) {}

    fun onFailedToRecycleView(holder: RecyclerView.ViewHolder?): Boolean = false
}