package com.example.linguahelper.presentation.screens.main

import com.example.linguahelper.data.source.entity.Favourites
import java.util.concurrent.Executors

class MainPresenter(private var view: MainContract.View) : MainContract.Presenter {
    private val model: MainContract.Model = MainModel()
 // private val executors = Executors.newSingleThreadExecutor()

    override fun loadWords() {
     // executors.execute{
          val cursor = model.getAllWords()
          view.showWords(cursor)
       // }

    }

    override fun    loadWordsByQuery(query: String) {
  //executors.execute {
      val cursor = model.getAllWordsByQuery(query)
      view.showWords(cursor)
  //}
    }

    override fun insertToFavourite(fav: Favourites) {
      //executors.execute {
            model.insertToFavourite(fav)
    //  }
    }

//    fun check(fav: Favourites){
//        val kk = model.getAllFavourite()
//        for (i in 0  until kk.count){
//            if
//        }
//    }
}