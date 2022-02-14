package com.refri.rockpaperscissorsthegame.ui.appintropage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroCustomLayoutFragment
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType
import com.refri.rockpaperscissorsthegame.game.GameActivity
import com.refri.rockpaperscissorsthegame.R
import com.refri.rockpaperscissorsthegame.ui.appintropage.nameinput.InputNameFragment

class IntroAppActivity : AppIntro2() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        isWizardMode = true
        addSlide(
            AppIntroCustomLayoutFragment.newInstance(R.layout.activity_intro_landing_page_1)
        )

        addSlide(
            AppIntroCustomLayoutFragment.newInstance(R.layout.activity_intro_landing_page_2)
            )

        addSlide(InputNameFragment())
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        if(currentFragment is InputNameFragment){
            currentFragment.navigateToMenuGame()
        }
    }


}