package com.example.carbrowser.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.example.carbrowser.R
import com.example.carbrowser.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // View binding using ViewBinder feature
        var bind = FragmentSplashBinding.inflate(layoutInflater)
        var textWelcome: TextView = bind.root.findViewById(R.id.welcome)

        // Animation and visual effects
        // Fade In
        var fadeInAlphaAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAlphaAnimation.duration = 1200
        fadeInAlphaAnimation.fillAfter = true
        fadeInAlphaAnimation.startOffset = 400

        // Fade Out
        var fadeOutAlphaAnimation = AlphaAnimation(1.0f, 0.0f)
        fadeOutAlphaAnimation.duration = 1000
        fadeOutAlphaAnimation.fillAfter = true
        fadeOutAlphaAnimation.startOffset = 400

        val fadeInAnimation = AnimationSet(false)
        fadeInAnimation.addAnimation(fadeOutAlphaAnimation)
        val fadeOutAnimation = AnimationSet(false)
        fadeOutAnimation.addAnimation(fadeOutAlphaAnimation)

        // Initiate animation
        textWelcome?.requestLayout()
        var fade1 = AnimationUtils.loadAnimation(bind.root.context, R.anim.fadeout)
        textWelcome?.startAnimation(fade1)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}