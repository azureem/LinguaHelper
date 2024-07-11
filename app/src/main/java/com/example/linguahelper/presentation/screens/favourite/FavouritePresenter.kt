package com.example.linguahelper.presentation.screens.favourite

import android.database.Cursor
import com.example.linguahelper.data.source.entity.Favourites
import java.util.concurrent.Executors

class FavouritePresenter(val view: FavouriteView) : FavouriteContract.Presenter {
    val model: FavouriteContract.Model = FavouriteModel()
  //  private val executors = Executors.newSingleThreadExecutor()

    override fun loadFavourites() {
     //   executors.execute {
            val curs = model.getAllFavourites()
            val isEmpty = curs == null || curs.count == 0
            view.showFavourites(curs)
            view.doesEmpty(isEmpty)
        //}
    }

    override fun deleteFavourites(favourites: Favourites) {
      //  executors.execute {
            model.deleteFavourite(favourites)
     //   }

    }
}