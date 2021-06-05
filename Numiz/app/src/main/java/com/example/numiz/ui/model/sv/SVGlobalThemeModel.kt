package com.example.numiz.ui.model.sv

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SVGlobalThemeModel(
    val id: Int,
    val name: String,
): Parcelable