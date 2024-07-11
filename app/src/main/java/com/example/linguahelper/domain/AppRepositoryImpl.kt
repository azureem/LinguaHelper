package com.example.linguahelper.domain

import android.database.Cursor
import android.database.sqlite.SQLiteCursor
import com.example.linguahelper.data.source.AppDatabase
import com.example.linguahelper.data.source.entity.Favourites
import kotlin.js.ExperimentalJsReflectionCreateInstance

class AppRepositoryImpl : AppRepository {
    companion object {
        private lateinit var instance: AppRepository

        fun init() {
            if (!(::instance.isInitialized)) {
                instance = AppRepositoryImpl()
            }
        }

        fun getAppRepository(): AppRepository = instance
    }

    private val wordDao = AppDatabase.getInstance().getWordDao()

    override fun getAllWords(): Cursor = wordDao.getAllWords()

    override fun getAllWordsByQuery(query: String): Cursor = wordDao.getAllWordsByQuery(query)



    override fun getAllFavourite(): Cursor = wordDao.getAllFavourites()

    override fun insertFavourite(fav: Favourites) = wordDao.insertFavourite(fav)

    override fun deleteFavourite(fav: Favourites) = wordDao.deleteFavourite(fav)
    override fun getImage1(name: String): Favourites =wordDao.getImage1(name)
    override fun getImageWord(): List<Favourites> = wordDao.getImageWord()


//    override fun getAllImageWord(): List<ImageFavik> =wordDao.getAllImageWords()
//
//    override fun deleteImageWord(imageFavik: ImageFavik) = wordDao.deleteImageView(imageFavik)
//
//    override fun insertImageWord(imageFavik: ImageFavik) = wordDao.insertImageWord(imageFavik)
}