package com.example.linguahelper.presentation.screens

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.linguahelper.R

class SplashScreen: Fragment(R.layout.screen_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        android.os.Handler().postDelayed({
            findNavController().navigate(R.id.action_splashScreen_to_homeScreen)
        }, 1500)
      //  requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = Color.parseColor("#4F6F52")
        requireActivity().getWindow().navigationBarColor=Color.parseColor("#4F6F52")

    }
}