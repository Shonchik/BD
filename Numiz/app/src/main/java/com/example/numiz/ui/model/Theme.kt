package com.example.numiz.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Theme(
    val name: String,
    val globalThemeId: Int,
    val descriptionId: Int,
): Parcelable