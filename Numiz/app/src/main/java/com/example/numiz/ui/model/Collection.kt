package com.example.numiz.ui.model

import android.os.Parcelable
import com.example.numiz.db.model.supmodel.CoinModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Collection(
    val name: String,
    val themeId: Int,
    val globalThemeId: Int,
    val descriptionId: Int,
    val coins: String,
): Parcelable