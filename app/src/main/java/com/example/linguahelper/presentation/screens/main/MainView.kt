package com.example.linguahelper.presentation.screens.main

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.linguahelper.R
import com.example.linguahelper.data.source.entity.WordEntity
import com.example.linguahelper.databinding.ScreenMainBinding
import com.example.linguahelper.presentation.adapters.FavouriteAdapter
import com.example.linguahelper.presentation.adapters.WordAdapter
import com.example.linguahelper.presentation.screens.word.WordView

class MainView : Fragment(R.layout.screen_main), MainContract.View {
    private val binding: ScreenMainBinding by viewBinding(ScreenMainBinding::bind)
    private lateinit var adapter: WordAdapter
    private val presenter by lazy { MainPresenter(this) }
    private var currentQuery: String? = null


    private val REQ_CODE_SPEECH_INPUT = 100

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      //  requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = Color.parseColor("#4F6F52")
        requireActivity().getWindow().navigationBarColor= Color.parseColor("#4F6F52")
        adapter = WordAdapter()
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())


        binding.microphone.setOnClickListener {
            promptSpeechInput()
        }

        binding.menuwka.setOnClickListener {
            findNavController().navigate(R.id.action_mainView_to_homeScreen)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                currentQuery = newText
                if (currentQuery == null) presenter.loadWords()
                else presenter.loadWordsByQuery(currentQuery!!)
                return true
            }
        })
        adapter.saveOnClickedFav {
            presenter.insertToFavourite(it)
        }

        binding.favs.setOnClickListener {
            findNavController().navigate(R.id.action_mainView_to_favikScreen)
        }
        adapter.setOnWordClickListener { wordEntity ->
            openWordDetailedScreen(wordEntity)
        }

        val closeButton =
            binding.searchView.findViewById(androidx.appcompat.R.id.search_close_btn) as ImageView
        closeButton.setOnClickListener {
            binding.searchView.setQuery(null, false)
            binding.searchView.clearFocus()
        }
        presenter.loadWords()
    }

    override fun showWords(cursor: Cursor) {
        if (isAdded) {
            requireActivity().runOnUiThread {
                adapter.setCursor(cursor, currentQuery)
            }
        }
    }

    private fun openWordDetailedScreen(wordEntity: WordEntity) {
        val myBundle = Bundle().apply {
            putString("word", wordEntity.word)
            putString("type", wordEntity.type)
            putString("definition", wordEntity.defn)
        }

        val wordView = WordView()
        wordView.arguments = myBundle
        findNavController().navigate(R.id.action_mainView_to_wordView, myBundle)
    }


    fun promptSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ru-RU")
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_string))

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                requireActivity(), "Sorry! Your device doesn\\'t support speech input",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_CODE_SPEECH_INPUT) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                val message = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                currentQuery = message?.get(0)
                presenter.loadWordsByQuery(currentQuery!!)
            }
        }
    }

    public fun updateResults(s: String) {
        Toast.makeText(requireActivity(), s, Toast.LENGTH_LONG).show()
        // text.text = s
    }
}

