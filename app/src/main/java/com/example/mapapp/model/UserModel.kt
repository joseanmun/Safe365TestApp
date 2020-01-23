/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 11:21 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/22/20 10:28 PM
 *
 */

package com.example.mapapp.model

import android.os.Parcel
import android.os.Parcelable

data class UserModel(
    val name: String,
    val avatar: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long,
    val motion: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readLong(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(avatar)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeLong(timestamp)
        parcel.writeString(motion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}