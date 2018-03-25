package com.github.ksughosh.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

/**
* Copyright (c) Apache License 2.0
* Created by SughoshKumar on 10/12/17.
*/
class BaseViewHolder<T: ListItem>(view: ListViewType<T>): RecyclerView.ViewHolder(view as View)