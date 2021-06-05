package com.example.numiz.ui.model.sv

import android.os.Parcelable
import com.example.numiz.ui.model.Coin
import kotlinx.parcelize.Parcelize


@Parcelize
data class SVCollectionModel(
    val id: Int,
    val name: String,
    val themeId: Int,
    val theme: String,
    val globalThemeId: Int,
    val globalTheme: String,
    val description: String,
    val coins: String
): Parcelable