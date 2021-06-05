package com.example.numiz.mapper

import com.example.numiz.db.model.*
import com.example.numiz.db.model.supmodel.CoinModel
import com.example.numiz.db.model.supmodel.CollectionModel
import com.example.numiz.db.model.supmodel.MintModel
import com.example.numiz.db.model.supmodel.ThemeModel
import com.example.numiz.ui.model.*
import com.example.numiz.ui.model.Collection
import com.example.numiz.ui.model.sv.*

object Mapper {

    fun dbToUiCountryModel(dbModel: CountryDbModel) = Country(
        name = dbModel.name,
    )
    fun uiToDbCountryModel(uiModel: Country) = CountryDbModel(
        id = 0,
        name = uiModel.name,
    )

    fun dbToUiMintModel(dbModel: MintDbModel) = Mint(
        name = dbModel.name,
        countryId = dbModel.countryId,
    )
    fun uiToDbMintModel(uiModel: Mint) = MintDbModel(
        id = 0,
        name = uiModel.name,
        countryId = uiModel.countryId,
    )

    fun dbToUiCoinModel(dbModel: CoinDbModel) = Coin(
        name = dbModel.name,
        amount = dbModel.amount,
        year = dbModel.year,
        mintId = dbModel.mintId,
        descriptionId = dbModel.descriptionId,
    )
    fun uiToDbCoinModel(uiModel: Coin) = CoinDbModel(
        id = 0,
        name = uiModel.name,
        amount = uiModel.amount,
        year = uiModel.year,
        mintId = uiModel.mintId,
        descriptionId = uiModel.descriptionId,
    )

    fun dbToUiCoinDescrModel(dbModel: CoinDescrDbModel) = CoinDescr(
        description = dbModel.description,
    )
    fun uiToDbCoinDescrModel(uiModel: CoinDescr) = CoinDescrDbModel(
        id = 0,
        description = uiModel.description,
    )

    fun dbToUiGlobalThemeModel(dbModel: GlobalThemeDbModel) = GlobalTheme(
        name = dbModel.name,
    )
    fun uiToDbGlobalThemeModel(uiModel: GlobalTheme) = GlobalThemeDbModel(
        id = 0,
        name = uiModel.name,
    )

    fun dbToUiCollectionDescrModel(dbModel: CollectionDescrDbModel) = CollectionDescr(
        description = dbModel.description,
    )
        fun uiToDbCollectionDescrModel(uiModel: CollectionDescr) = CollectionDescrDbModel(
        id = 0,
        description = uiModel.description,
    )

    fun dbToUiThemeDescrModel(dbModel: ThemeDescrDbModel) = ThemeDescr(
        description = dbModel.description,
    )
    fun uiToDbThemeDescrModel(uiModel: ThemeDescr) = ThemeDescrDbModel(
        id = 0,
        description = uiModel.description,
    )

    fun dbToUiThemeModel(dbModel: ThemeDbModel) = Theme(
        name = dbModel.name,
        globalThemeId = dbModel.globalThemeId,
        descriptionId = dbModel.descriptionId,
    )
    fun uiToDbThemeModel(uiModel: Theme) = ThemeDbModel(
        id = 0,
        name = uiModel.name,
        globalThemeId = uiModel.globalThemeId,
        descriptionId = uiModel.descriptionId,
    )

    fun dbToUiCollectionModel(dbModel: CollectionDbModel) = Collection(
        name = dbModel.name,
        themeId = dbModel.themeId,
        globalThemeId = dbModel.globalThemeId,
        descriptionId = dbModel.descriptionId,
        coins = dbModel.coins
    )
    fun uiToDbCollectionModel(uiModel: Collection) = CollectionDbModel(
        id = 0,
        name = uiModel.name,
        themeId = uiModel.themeId,
        globalThemeId = uiModel.globalThemeId,
        descriptionId = uiModel.descriptionId,
        coins = uiModel.coins
    )





    fun dbToUiSVMintModel(dbModel: MintModel) = SVMintModel(
        id = dbModel.mint.id,
        name = dbModel.mint.name,
        countryId = dbModel.country.id,
        country = dbModel.country.name,
    )

    fun dbToUiSVCountryModel(dbModel: CountryDbModel) = SVCountryModel(
        id = dbModel.id,
        name = dbModel.name,
    )

    fun dbToUiSVCoinModel(dbModel: CoinModel) = SVCoinModel(
        id = dbModel.coin.id,
        name = dbModel.coin.name,
        amount = dbModel.coin.amount,
        year = dbModel.coin.year,
        mintId = dbModel.mintModel.id,
        mint = dbModel.mintModel.name,
        description = dbModel.description.description,
    )

    fun dbToUiSVGlobalThemeModel(dbModel: GlobalThemeDbModel) = SVGlobalThemeModel(
        id = dbModel.id,
        name = dbModel.name,
    )

    fun dbToUiSVThemeModel(dbModel: ThemeModel) = SVThemeModel(
        id = dbModel.theme.id,
        name = dbModel.theme.name,
        globalThemeId = dbModel.globalTheme.id,
        globalTheme = dbModel.globalTheme.name,
        description = dbModel.description.description,
    )

    fun dbToUiSVCollectionModel(dbModel: CollectionModel) = SVCollectionModel(
        id = dbModel.collection.id,
        name = dbModel.collection.name,
        themeId = dbModel.theme.id,
        theme = dbModel.theme.name,
        globalThemeId = dbModel.globalTheme.id,
        globalTheme = dbModel.globalTheme.name,
        description = dbModel.description.description,
        coins = dbModel.collection.coins
    )

}