package com.example.linguahelper.presentation.screens.home

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayoutStates
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.example.linguahelper.R
import com.example.linguahelper.databinding.NavDrawerBinding

class HomeScreen : Fragment(R.layout.screen_home) {
    private var binidng: NavDrawerBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //binidng!!.inner.animBack.playAnimation()

      //requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = Color.parseColor("#4F6F52")
        requireActivity().getWindow().navigationBarColor= Color.parseColor("#4F6F52")

        binidng!!.inner.play.setOnClickListener {
            findNavController().navigate(R.id.homeScreen_to_mainView)  }

    //    binidng!!.inner.animDrawer.playAnimation()
        binidng!!.inner.menuwka.setOnClickListener {
            binidng!!.drawerlayout.openDrawer(GravityCompat.START)
        }

        binidng!!.navigationView.setNavigationItemSelectedListener {
           when (it.itemId) {
                R.id.rate -> {
                    Toast.makeText(requireActivity(), "The app has not been uploaded to the Play Market", Toast.LENGTH_SHORT).show()
                }

                R.id.share -> {
                shareApp()
                }
                R.id.contact->{
                    contactUs()
                }
                R.id.exit->{
                    finishDialog()
                }
            }
            return@setNavigationItemSelectedListener true
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binidng = NavDrawerBinding.inflate(layoutInflater, container, false)
        return binidng!!.root
    }

    private fun shareApp() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Check out this cool app: https://yourappurl.com")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, "Share via"))
    }

    private fun contactUs() {
        try {
            val telegramUri = Uri.parse("https://t.me/mgalibovna")
            val telegramIntent = Intent(Intent.ACTION_VIEW, telegramUri)
            startActivity(telegramIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(requireContext(), "Telegram app not installed", Toast.LENGTH_SHORT).show()
        }
    }

    fun finishDialog() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Exit Game")
            .setMessage("Are you sure you want to leave the Lingua Helper?")
            .setPositiveButton(
                "Yes"
            ) { dialog, id ->requireActivity().finish() }
            .setNegativeButton(
                "No"
            ) { dialog, id -> dialog.dismiss() }
        builder.setCancelable(false)
        val dialog = builder.create()
        dialog.show()
    }

}