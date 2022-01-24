package com.refri.rockpaperscissorsthegame.gamelistener
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.refri.rockpaperscissorsthegame.R
import com.refri.rockpaperscissorsthegame.databinding.ActivityMainBinding
import com.refri.rockpaperscissorsthegame.usecase.GameUseCaseImpl


class PrintBattleResult {

    fun showBattleResult(
        binding: ActivityMainBinding ,
        battleResult: Int,
        context: Context
    ) {
        when (battleResult) {
            GameUseCaseImpl.PLAYER_WIN -> {
                Log.d(TAG, "Player Win")
                binding.tvGameStatus.text = context.getString(R.string.text_player_win)

            }
            GameUseCaseImpl.COMPUTER_WIN -> {
                Log.d(TAG, "Computer Win")
                binding.tvGameStatus.text = context.getString(R.string.text_computer_win)

            }
            GameUseCaseImpl.DRAW -> {
                Log.d(TAG, "Draw")
                binding.tvGameStatus.text = context.getString(R.string.text_draw)

            }
        }
    }
}