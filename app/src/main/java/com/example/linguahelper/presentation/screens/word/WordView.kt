package com.example.linguahelper.presentation.screens.word

import android.graphics.Color
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.linguahelper.R
import com.example.linguahelper.databinding.ScreenWordBinding
import com.example.linguahelper.presentation.adapters.WordAdapter
import java.util.Locale


class WordView : Fragment(R.layout.screen_word) {
    private lateinit var adapter: WordAdapter
    private lateinit var tts: TextToSpeech
    private val binding by viewBinding (ScreenWordBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = Color.parseColor("#4F6F52")
        requireActivity().getWindow().navigationBarColor= Color.parseColor("#4F6F52")
        adapter = WordAdapter()
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_wordView_to_mainView)
        }

        tts = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            tts.setLanguage(Locale.US)
        })


        val word = arguments?.getString("word", "")
        val type = arguments?.getString("type", "")
        val definition = arguments?.getString("definition", "")


        view.findViewById<TextView>(R.id.actual_word).text = word
        view.findViewById<TextView>(R.id.word_type).text = type
        view.findViewById<TextView>(R.id.word_definition).text = definition

        view.findViewById<ImageView>(R.id.speaker).setOnClickListener {
         speakOut(word.toString())
        }
    }


    private fun speakOut(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

}

