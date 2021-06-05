package com.example.numiz.ui.model.sv

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SVMintModel(
    val id: Int,
    val name: String,
    val countryId: Int,
    val country: String
): Parcelable