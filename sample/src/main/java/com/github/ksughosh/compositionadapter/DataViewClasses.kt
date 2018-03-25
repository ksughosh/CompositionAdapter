package com.github.ksughosh.compositionadapter

import android.content.Context
import android.util.Log
import android.view.View
import com.github.ksughosh.adapter.BaseConstraintHolder
import com.github.ksughosh.adapter.BaseLinearHolder
import kotlinx.android.synthetic.main.data_one_view_2.view.*

/**
* Copyright (c) Apache License 2.0
* Created by SughoshKumar on 10/12/17.
*/
class DataOneView(context: Context?): BaseLinearHolder<DataOne>(context) {
    init {
        Log.d("DataOneView", "Init")
        setView(R.layout.data_one_layout)
    }

    override fun onDataReady(data: DataOne) {
        Log.d("DataReady", "DataOne")
    }
}

class DataOneView2(context: Context?) : BaseConstraintHolder<DataOne>(context) {
    init {
        Log.d("DataOneView", "initialized")
        setView(R.layout.data_one_view_2)
    }

    override fun onDataReady(data: DataOne) {
        if (data.target) {
            textButton?.visibility = View.GONE
        } else {
            textButton?.visibility = View.VISIBLE
        }
    }
}

class DataTwoView(context: Context?): BaseLinearHolder<DataTwo>(context) {
    init {
        Log.d("DataTwoView", "Init")
        setView(R.layout.data_two_layout)
    }
    override fun onDataReady(data: DataTwo) {
        Log.d("DataReady", "DataTwo")
    }
}