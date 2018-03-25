package com.github.ksughosh.compositionadapter

import android.os.Parcel
import android.os.Parcelable
import com.github.ksughosh.adapter.ListItem

/**
* Copyright (c) Apache License 2.0
* Created by SughoshKumar on 10/12/17.
*/
data class DataTwo(val target: Int, val name: String) : ListItem, Parcelable {
    constructor(source: Parcel) : this(
            target = source.readInt(),
            name = source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(target)
        writeString(name)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DataTwo> = object : Parcelable.Creator<DataTwo> {
            override fun createFromParcel(source: Parcel): DataTwo = DataTwo(source)
            override fun newArray(size: Int): Array<DataTwo?> = arrayOfNulls(size)
        }
    }
}