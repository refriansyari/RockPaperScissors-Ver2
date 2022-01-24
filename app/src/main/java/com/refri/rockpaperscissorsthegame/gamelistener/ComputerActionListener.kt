package com.refri.rockpaperscissorsthegame.gamelistener

import com.refri.rockpaperscissorsthegame.R
import com.refri.rockpaperscissorsthegame.databinding.ActivityMainBinding
import com.refri.rockpaperscissorsthegame.enum.MaterialSelection
import kotlin.random.Random

class ComputerActionListener {
    var computerChoice: MaterialSelection? = null

    fun setRandomChoice(binding:ActivityMainBinding) {

        var randomnumber = Random.nextInt(0, 3)
        when (randomnumber) {
            0 -> {
                binding.flChoice4.setBackgroundResource(R.drawable.ic_kapow)

                binding.ivChoice4.animate().apply {
                    duration = 50
                    scaleXBy(0.5f)
                    scaleYBy(0.5f)
                }.withEndAction {
                    binding.ivChoice4.animate().apply {
                        duration = 50
                        scaleXBy(-0.5f)
                        scaleYBy(-0.5f)
                    }
                }.start()
                binding.flChoice5.setBackgroundResource(0)
                binding.flChoice6.setBackgroundResource(0)
                computerChoice = MaterialSelection.ROCK


            }
            1 -> {
                binding.flChoice4.setBackgroundResource(0)
                binding.flChoice5.setBackgroundResource(R.drawable.ic_explosion)
                binding.ivChoice5.animate().apply {
                    duration = 50
                    scaleXBy(0.5f)
                    scaleYBy(0.5f)
                }.withEndAction {
                    binding.ivChoice5.animate().apply {
                        duration = 50
                        scaleXBy(-0.5f)
                        scaleYBy(-0.5f)
                    }
                }.start()
                binding.flChoice6.setBackgroundResource(0)
                computerChoice = MaterialSelection.PAPER
            }
            2 -> {
                binding.flChoice4.setBackgroundResource(0)
                binding.flChoice5.setBackgroundResource(0)
                binding.flChoice6.setBackgroundResource(R.drawable.ic_thunder_explosion)
                binding.ivChoice6.animate().apply {
                    duration = 50
                    scaleXBy(0.5f)
                    scaleYBy(0.5f)
                }.withEndAction {
                    binding.ivChoice6.animate().apply {
                        duration = 50
                        scaleXBy(-0.5f)
                        scaleYBy(-0.5f)
                    }
                }.start()
                computerChoice = MaterialSelection.SCISSORS
            }
        }
    }

}