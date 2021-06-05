package com.example.numiz.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mint(
    val name: String,
    val countryId: Int
): Parcelable