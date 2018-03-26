package com.github.ksughosh.adapter

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
* Copyright (c) Apache License 2.0
* Created by SughoshKumar on 10/12/17.
*/
interface ListViewType<T: ListItem> : View.OnClickListener{
    var model: T?
    fun setView(@LayoutRes resource: Int) {
        inflate(resource, true)
    }

    fun onDataReady(data: T)

    fun ListViewType<*>.inflate(@LayoutRes res: Int, attachToParent: Boolean = false) {
        if (this is ViewGroup) {
            LayoutInflater.from(context).inflate(res, this, attachToParent)
            setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {}

    fun setOnItemClick(func : (ListViewType<T>) -> Unit) {
        (this as? View)?.setOnClickListener { func(this) }
    }

    fun onDestroy() {
        (this as? View)?.setOnClickListener(null)
    }
}