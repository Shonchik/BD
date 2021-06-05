package com.example.numiz.db

import androidx.room.*
import com.example.numiz.db.model.*
import com.example.numiz.db.model.supmodel.CoinModel
import com.example.numiz.db.model.supmodel.CollectionModel
import com.example.numiz.db.model.supmodel.MintModel
import com.example.numiz.db.model.supmodel.ThemeModel
import com.example.numiz.ui.model.sv.SVCoinCountModel

@Dao
interface MyDao {

    // COUNTRY
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(item: CountryDbModel)

    @Update
    suspend fun updateCountry(item: CountryDbModel)

    @Query("SELECT * FROM country WHERE name == :item")
    suspend fun getCountry(item: String): CountryDbModel

    @Query("SELECT COUNT(id) FROM country")
    suspend fun countCountry(): Int

    @Query("SELECT * FROM country")
    suspend fun getAllCountry(): List<CountryDbModel>

    @Query("SELECT * FROM country ORDER BY name ASC")
    suspend fun getAllCountrySort(): List<CountryDbModel>

    @Query("DELETE FROM country WHERE id == :item")
    suspend fun delCountry(item: Int)

    //Mint
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMint(item: MintDbModel)

    @Update
    suspend fun updateMint(item: MintDbModel)

    @Query("SELECT * FROM mint WHERE name == :item")
    suspend fun getMint(item: String): MintDbModel

    @Query("SELECT COUNT(id) FROM mint")
    suspend fun countMint(): Int

    @Query("SELECT * FROM mint")
    suspend fun getAllMint(): List<MintDbModel>

    @Query("DELETE FROM mint WHERE id == :item")
    suspend fun delMint(item: Int)

    //Coin
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(item: CoinDbModel)

    @Update
    suspend fun updateCoin(item: CoinDbModel)

    @Query("SELECT * FROM coin WHERE name == :item")
    suspend fun getCoin(item: String): CoinDbModel

    @Query("SELECT COUNT(id) FROM coin")
    suspend fun countCoin(): Int

    @Transaction
    @Query("SELECT * FROM coin WHERE year > 1999 ORDER BY year ASC")
    suspend fun getAllCoinSort(): List<CoinModel>

    @Query("SELECT * FROM coin")
    suspend fun getAllCoin(): List<CoinDbModel>

    @Query("DELETE FROM coin WHERE id == :item")
    suspend fun delCoin(item: Int)

    //CoinDescription
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinDescr(item: CoinDescrDbModel)

    @Update
    suspend fun updateCoinDescr(item: CoinDescrDbModel)

    @Query("SELECT * FROM coin_description WHERE id == :item")
    suspend fun getCoinDescr(item: Int): CoinDescrDbModel

    @Query("SELECT COUNT(id) FROM coin_description")
    suspend fun countCoinDescr(): Int

    @Query("SELECT * FROM coin_description")
    suspend fun getAllCoinDescr(): List<CoinDescrDbModel>

    @Query("DELETE FROM coin_description WHERE id == :item")
    suspend fun delCoinDescr(item: Int)

    //GlobalTheme
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGlobalTheme(item: GlobalThemeDbModel)

    @Update
    suspend fun updateGlobalTheme(item: GlobalThemeDbModel)

    @Query("SELECT * FROM global_theme WHERE id == :item")
    suspend fun getGlobalTheme(item: Int): GlobalThemeDbModel

    @Query("SELECT COUNT(id) FROM global_theme")
    suspend fun countGlobalTheme(): Int

    @Query("SELECT * FROM global_theme")
    suspend fun getAllGlobalTheme(): List<GlobalThemeDbModel>

    @Query("DELETE FROM global_theme WHERE id == :item")
    suspend fun delGlobalTheme(item: Int)

    //ThemeDescription
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThemeDescr(item: ThemeDescrDbModel)

    @Update
    suspend fun updateThemeDescr(item: ThemeDescrDbModel)

    @Query("SELECT * FROM theme_description WHERE id == :item")
    suspend fun getThemeDescr(item: Int): ThemeDescrDbModel

    @Query("SELECT COUNT(id) FROM theme_description")
    suspend fun countThemeDescr(): Int

    @Query("SELECT * FROM theme_description")
    suspend fun getAllThemeDescr(): List<ThemeDescrDbModel>

    @Query("DELETE FROM theme_description WHERE id == :item")
    suspend fun delThemeDescr(item: Int)

    //Theme
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTheme(item: ThemeDbModel)

    @Update
    suspend fun updateTheme(item: ThemeDbModel)

    @Query("SELECT * FROM theme WHERE id == :item")
    suspend fun getTheme(item: Int): ThemeDbModel

    @Query("SELECT COUNT(id) FROM theme")
    suspend fun countTheme(): Int

    @Query("SELECT * FROM theme")
    suspend fun getAllTheme(): List<ThemeDbModel>

    @Query("DELETE FROM theme WHERE id == :item")
    suspend fun delTheme(item: Int)

    //CollectionDescription
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollectionDescr(item: CollectionDescrDbModel)

    @Update
    suspend fun updateCollectionDescription(item: CollectionDescrDbModel)

    @Query("SELECT * FROM collection_description WHERE id == :item")
    suspend fun getCollectionDescr(item: Int): CollectionDescrDbModel

    @Query("SELECT COUNT(id) FROM collection_description")
    suspend fun countCollectionDescr(): Int

    @Query("SELECT * FROM collection_description")
    suspend fun getAllCollectionDescr(): List<CollectionDescrDbModel>

    @Query("DELETE FROM collection_description WHERE id == :item")
    suspend fun delCollectionDescr(item: Int)

    //Collection
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollection(item: CollectionDbModel)

    @Update
    suspend fun updateCollection(item: CollectionDbModel)

    @Query("SELECT * FROM collection WHERE id == :item")
    suspend fun getCollection(item: Int): CollectionDbModel

    @Query("SELECT COUNT(id) FROM collection")
    suspend fun countCollection(): Int

    @Query("SELECT * FROM collection")
    suspend fun getAllCollection(): List<CollectionDbModel>

    @Query("DELETE FROM collection WHERE id == :item")
    suspend fun delCollection(item: Int)

    @Transaction
    @Query("SELECT * from mint")
    suspend fun getMintModel(): List<MintModel>

    @Transaction
    @Query("SELECT * from coin")
    suspend fun getCoinModel(): List<CoinModel>

    @Transaction
    @Query("SELECT * from theme")
    suspend fun getThemeModel(): List<ThemeModel>

    @Transaction
    @Query("SELECT * from collection")
    suspend fun getCollectionModel(): List<CollectionModel>

    @Query("SELECT name, year, COUNT(*) AS count FROM coin GROUP BY year HAVING count >= 2")
    suspend fun getCoinGroup(): List<SVCoinCountModel>

}