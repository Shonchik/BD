package com.example.numiz.ui.model.sv

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SVCoinModel(
    val id: Int,
    val name: String,
    val amount: Int,
    val year: String,
    val mintId: Int,
    val mint: String,
    val description: String,
): Parcelable