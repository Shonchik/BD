package com.example.numiz.ui.model.sv

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SVCoinCountModel(
    val name: String,
    val year: String,
    val count: Int,
): Parcelable