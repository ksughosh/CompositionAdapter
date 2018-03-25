package com.github.ksughosh.compositionadapter

import android.os.Parcel
import android.os.Parcelable
import com.github.ksughosh.adapter.ListItem

/**
* Copyright (c) Apache License 2.0
* Created by SughoshKumar on 10/12/17.
*/
data class DataOne(val isShown: Boolean,
                   var target: Boolean = false) : ListItem, Parcelable {
    constructor(source: Parcel) : this(
            isShown = 1 == source.readInt(),
            target = 1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt((if (isShown) 1 else 0))
        writeInt((if (target) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DataOne> = object : Parcelable.Creator<DataOne> {
            override fun createFromParcel(source: Parcel): DataOne = DataOne(source)
            override fun newArray(size: Int): Array<DataOne?> = arrayOfNulls(size)
        }
    }
}