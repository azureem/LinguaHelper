package com.example.linguahelper.domain

import android.database.Cursor
import androidx.room.Query
import com.example.linguahelper.data.source.entity.Favourites

interface AppRepository {
    fun getAllWords(): Cursor
    fun getAllWordsByQuery(query: String): Cursor

    fun getAllFavourite(): Cursor
    fun insertFavourite(fav: Favourites)
    fun deleteFavourite(fav:Favourites)

    fun getImage1(name: String): Favourites
    fun getImageWord():List<Favourites>

//    fun getAllImageWord():List<ImageFavik>
//    fun deleteImageWord(imageFavik: ImageFavik)
//    fun insertImageWord(imageFavik: ImageFavik)
}