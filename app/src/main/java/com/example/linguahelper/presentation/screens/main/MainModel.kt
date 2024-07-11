package com.example.linguahelper.presentation.screens.main

import android.database.Cursor
import com.example.linguahelper.data.source.entity.Favourites
import com.example.linguahelper.domain.AppRepository
import com.example.linguahelper.domain.AppRepositoryImpl

class MainModel : MainContract.Model {

    private val repo: AppRepository = AppRepositoryImpl.getAppRepository()
    override fun getAllWords(): Cursor {
        return repo.getAllWords()
    }

    override fun getAllWordsByQuery(query: String): Cursor {
        return repo.getAllWordsByQuery(query)
    }

    override fun insertToFavourite(fav: Favourites) {
//        val o = repo.getAllFavourite().count
//      for (i in 0 until o){
//          if (o)
//      }
      repo.insertFavourite(fav)
    }

    override fun getAllFavourite(): Cursor {
        return repo.getAllFavourite()
    }
}