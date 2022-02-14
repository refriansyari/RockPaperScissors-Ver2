package com.refri.rockpaperscissorsthegame.menu

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.refri.rockpaperscissorsthegame.R
import com.refri.rockpaperscissorsthegame.databinding.ActivityMenuGameBinding
import com.refri.rockpaperscissorsthegame.enum.PlaySide
import com.refri.rockpaperscissorsthegame.game.GameActivity
import com.refri.rockpaperscissorsthegame.preference.UserPreference

class MenuGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuGameBinding
    private var versusMode: Int = COMPUTER_VERSUS_MODE
    private var playTurn: PlaySide = PlaySide.PLAYER

    companion object {
        private const val EXTRAS_VERSUS_MODE = "EXTRAS_VERSUS_MODE"
        const val COMPUTER_VERSUS_MODE = 0
        const val PLAYER_VERSUS_MODE = 1

        @JvmStatic
        fun startActivity(context: Context, versusMode: Int) {
            val intent = Intent(context, GameActivity::class.java)
            intent.putExtra(EXTRAS_VERSUS_MODE, versusMode)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setModeClickListeners()
        setNameOnTitle()

    }

    private fun setModeClickListeners() {
        //passing versusMode value to Game Activity
        binding.ivMenuComputer.setOnClickListener {
            startActivity(this, COMPUTER_VERSUS_MODE)
        }
        binding.ivMenuPlayer.setOnClickListener {
            startActivity(this, PLAYER_VERSUS_MODE)
        }
    }

    //passing userPreference data to title
    private fun setNameOnTitle() {
        binding.tvMenuComputer.text =
            getString(
                R.string.text_placeholder_versus_computer,
                UserPreference(this).name
            ).uppercase()

        binding.tvMenuPlayer.text =
            getString(
                R.string.text_placeholder_versus_player,
                UserPreference(this).name
            ).uppercase()
    }
}