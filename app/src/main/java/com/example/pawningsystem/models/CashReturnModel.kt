package com.example.pawningsystem.models

import android.os.Parcel
import android.os.Parcelable

data class CashReturnModel(val csNIC:String?=null,
                           val fullName:String?=null,
                           val email:String?=null,
                           val phone:String?=null,
                           val cashAmount:String?=null,
                           val dateToCollect:String?=null,
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(csNIC)
        parcel.writeString(fullName)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeString(cashAmount)
        parcel.writeString(dateToCollect)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CashReturnModel> {
        override fun createFromParcel(parcel: Parcel): CashReturnModel {
            return CashReturnModel(parcel)
        }

        override fun newArray(size: Int): Array<CashReturnModel?> {
            return arrayOfNulls(size)
        }
    }
}