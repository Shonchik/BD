package com.example.numiz.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.numiz.db.model.*

@Database(
    entities = [
        CountryDbModel::class,
        MintDbModel::class,
        CoinDescrDbModel::class,
        CoinDbModel::class,
        GlobalThemeDbModel::class,
        ThemeDbModel::class,
        ThemeDescrDbModel::class,
        CollectionDbModel::class,
        CollectionDescrDbModel::class
    ],
    version = 22
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun dao(): MyDao

    companion object {

        fun getInstance(context: Context): MyDatabase {
            return Room.databaseBuilder(context, MyDatabase::class.java, "db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}