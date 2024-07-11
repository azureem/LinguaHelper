package com.example.linguahelper.presentation.screens.main

import android.database.Cursor
import com.example.linguahelper.data.source.entity.Favourites

interface MainContract {

    interface Model {
        fun getAllWords(): Cursor
        fun getAllWordsByQuery(query: String): Cursor
        fun insertToFavourite(fav: Favourites)
        fun getAllFavourite():Cursor
    }


    interface View {
        fun showWords(cursor: Cursor)
    }

    interface Presenter {
        fun loadWords()
        fun loadWordsByQuery(query: String)

        fun insertToFavourite(fav: Favourites)
    }
}