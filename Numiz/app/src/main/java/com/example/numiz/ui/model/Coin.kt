package com.example.numiz.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coin(
    val name: String,
    val amount: Int,
    val year: String,
    val mintId: Int,
    val descriptionId: Int,
): Parcelable