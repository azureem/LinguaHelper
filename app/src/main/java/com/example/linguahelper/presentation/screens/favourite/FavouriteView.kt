package com.example.linguahelper.presentation.screens.favourite

import android.database.Cursor
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.linguahelper.R
import com.example.linguahelper.data.source.entity.Favourites
import com.example.linguahelper.databinding.ScreenFavouritesBinding
import com.example.linguahelper.presentation.adapters.FavouriteAdapter
import com.example.linguahelper.presentation.screens.word.WordView


class FavouriteView : Fragment(R.layout.screen_favourites), FavouriteContract.View {

    lateinit var adapter: FavouriteAdapter
    private val presenter by lazy { FavouritePresenter(this) }
    private val binding by viewBinding(ScreenFavouritesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = Color.parseColor("#4F6F52")
        requireActivity().getWindow().navigationBarColor= Color.parseColor("#4F6F52")
        adapter = FavouriteAdapter()
        binding.rvFavi.adapter = adapter
        binding.rvFavi.layoutManager = LinearLayoutManager(requireActivity())
        presenter.loadFavourites()
        binding.menuwka.setOnClickListener {
            findNavController().navigate(R.id.action_favikScreen_to_mainView)
        }
        adapter.saveOnClickedFav { itFav->
           openDetailed(itFav)
        }
        adapter.setOnSavedFun {
            presenter.deleteFavourites(it)
            presenter.loadFavourites()
        }

    }

    override fun showFavourites(cursor: Cursor) {
        if (isAdded) {
            requireActivity().runOnUiThread {
                adapter.setCursor(cursor)
            }
        }
    }

    override fun doesEmpty(bool: Boolean) {
        binding.emptyAnim.playAnimation()
        binding.emptyAnim.visibility = if (bool) View.VISIBLE else View.GONE
    }

    fun openDetailed(favourites: Favourites){
        val myBundle =  Bundle().apply {
            putString("word", favourites.word)
            putString("type", favourites.type)
            putString("definition", favourites.defn)
        }

        val word = WordView()
        word.arguments = myBundle

        findNavController().navigate(R.id.favikScreen_to_wordView, myBundle)
    }
}