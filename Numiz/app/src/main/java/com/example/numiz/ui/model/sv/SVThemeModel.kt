package com.example.numiz.ui.model.sv

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SVThemeModel(
    val id: Int,
    val name: String,
    val globalThemeId: Int,
    val globalTheme: String,
    val description: String,
): Parcelable