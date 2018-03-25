package com.github.ksughosh.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup

/**
* Copyright (c) Apache License 2.0
* Created by SughoshKumar on 10/12/17.
*/
class DataAdapter(items: List<ListItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items:List<ListItem> = items
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var listOfDelegates = mutableListOf<ViewDelegate<out ListItem, ListViewType<out ListItem>>>()
    fun addDelegate(vararg delegate: ViewDelegate<out ListItem, ListViewType<out ListItem>>) : DataAdapter {
        delegate.forEachIndexed { index, abstractDelegate ->
            abstractDelegate.viewType = index
        }
        listOfDelegates.addAll(delegate)
        return this
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindViewHolder(holder, position, mutableListOf())
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        val view = listOfDelegates[holder.itemViewType]
        view.onBindViewHolder(holder, items, position, payloads)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = listOfDelegates[viewType]
        return view.onCreateViewHolder(parent)
    }

    override fun getItemViewType(position: Int): Int {
        val view = listOfDelegates.firstOrNull { it.forItemViewType(items[position]) } ?:
                throw IllegalArgumentException("Data is incoherent with delegate")
        Log.d("ADAPTER", "${view.viewType}")
        return view.viewType
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        listOfDelegates[holder.itemViewType].onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        listOfDelegates[holder.itemViewType].onViewDetachedFromWindow(holder)
    }


    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return listOfDelegates[holder.itemViewType].onFailedToRecycleView(holder)
    }
}