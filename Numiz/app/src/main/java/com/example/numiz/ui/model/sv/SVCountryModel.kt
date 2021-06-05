package com.example.numiz.ui.model.sv

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SVCountryModel(
    val id: Int,
    val name: String,
): Parcelable