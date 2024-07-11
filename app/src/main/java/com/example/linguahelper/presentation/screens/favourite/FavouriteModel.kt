package com.example.linguahelper.presentation.screens.favourite

import android.database.Cursor
import com.example.linguahelper.data.source.entity.Favourites
import com.example.linguahelper.domain.AppRepository
import com.example.linguahelper.domain.AppRepositoryImpl

class FavouriteModel : FavouriteContract.Model {

    val repo: AppRepository = AppRepositoryImpl.getAppRepository()

    override fun getAllFavourites(): Cursor {
      return repo.getAllFavourite()
    }

    override fun insertFavourite(fav: Favourites) {
       return repo.insertFavourite(fav)
    }

    override fun deleteFavourite(fav: Favourites) {
        return repo.deleteFavourite(fav)
    }
}