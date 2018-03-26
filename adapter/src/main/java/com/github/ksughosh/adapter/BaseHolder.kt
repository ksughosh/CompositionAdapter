package com.github.ksughosh.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout

/**
* Copyright (c) Apache License 2.0
* Created by SughoshKumar on 10/12/17.
*/
abstract class BaseLinearHolder<T: ListItem> (context: Context?): LinearLayout(context), ListViewType<T> {
    override fun setView(resource: Int) {
        super.setView(resource)
        layoutParams =  RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override var model: T? = null
        set(value) {
            field = value
            if (value != null) {
                onDataReady(value)
            }
        }
}

abstract class BaseFrameHolder<T: ListItem> (context: Context?): FrameLayout(context), ListViewType<T> {
    override var model: T? = null
        set(value) {
            field = value
            if (value != null) {
                onDataReady(value)
            }
        }
}

abstract class BaseRelativeHolder<T: ListItem> (context: Context?): RelativeLayout(context), ListViewType<T>{
    override var model: T? = null
        set(value) {
            field = value
            if (value != null) {
                onDataReady(value)
            }
        }
}

abstract class BaseConstraintHolder<T: ListItem> (context: Context?) : ConstraintLayout(context), ListViewType<T>{
    override fun setView(resource: Int) {
        super.setView(resource)
        layoutParams =  RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override var model: T? = null
        set(value) {
            field = value
            if (value != null) {
                onDataReady(value)
            }
        }
}