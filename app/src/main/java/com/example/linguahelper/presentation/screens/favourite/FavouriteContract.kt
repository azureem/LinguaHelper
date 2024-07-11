package com.example.linguahelper.presentation.screens.favourite

import android.database.Cursor
import com.example.linguahelper.data.source.entity.Favourites

interface FavouriteContract {

    interface Model{
        fun getAllFavourites():Cursor
        fun insertFavourite(fav: Favourites)
        fun deleteFavourite(fav: Favourites)
    }


    interface View{
        fun showFavourites(cursor: Cursor)
        fun  doesEmpty(bool: Boolean)
    }


    interface Presenter{
    fun loadFavourites()

    fun deleteFavourites(favourites: Favourites)

    }
}