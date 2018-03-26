package com.github.ksughosh.adapter

import android.support.annotation.CallSuper
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
    private var itemOnClick: ((U, Int) -> Unit)? = null

    @CallSuper open fun onBindViewHolder(holder: RecyclerView.ViewHolder?, items: List<ListItem>?, position: Int, payload: MutableList<Any>? = null) {
        val item = items?.get(position)
        val holderItem = holder?.itemView as? ListViewType<T> ?: return
        holderItem.model = item as T
        itemOnClick?.apply {  holderItem.setOnItemClick { this(it as U, position) } }
    }



    /**
     * @param func is the on item delegate click
     * Note call on [onDestroy] on activity/fragment destruction
     * this will remove the initialization references made in the lambda
     */
    open fun setOnDelegateClick(func : (U, Int) -> Unit) {
        itemOnClick = func
    }

    @CallSuper open fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        Log.d("BINDER", "CreateViewHolder")
        return BaseViewHolder(itemView(parent))
    }

    open fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder?) {}

    open fun forItemViewType(item: Any?): Boolean =  forType(item)

    open fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder?) {}

    open fun onFailedToRecycleView(holder: RecyclerView.ViewHolder?): Boolean = false

    fun onDestroy() {
        // clear references
        itemOnClick = null
    }
}