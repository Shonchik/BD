package com.example.numiz.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinDescr(
    val description: String
): Parcelable
