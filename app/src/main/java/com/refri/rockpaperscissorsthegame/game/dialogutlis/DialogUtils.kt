package com.refri.rockpaperscissorsthegame.game.dialogutlis

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.security.keystore.UserPresenceUnavailableException
import android.system.Os.remove
import android.text.method.TextKeyListener.clear
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.refri.rockpaperscissorsthegame.R
import com.refri.rockpaperscissorsthegame.databinding.DialogAnnouncerBinding
import com.refri.rockpaperscissorsthegame.menu.MenuGameActivity
import com.refri.rockpaperscissorsthegame.preference.UserPreference

object DialogUtils {


    fun showAnnouncerDialogPlayer (context: Context) {
        var dialog: AlertDialog? = null
        val dialogBinding =
            DialogAnnouncerBinding.inflate((context as AppCompatActivity).layoutInflater).apply {
                tvDialogTitle.text =
                    context.getString(
                        R.string.text_game_announcement,
                        UserPreference(context).name            //call user preference name
                    ).uppercase()
                //Play again button
                btnDialogPlayAgain.setOnClickListener {
                    context.finish()
                    context.overridePendingTransition(0, 0)
                    context.startActivity(context.intent)
                    context.overridePendingTransition(0, 0)
                    Log.d("Hello", "New Game")
                }
                //Navigate to main menu
                btnDialogMainMenu.setOnClickListener {
                    val intent = Intent(context, MenuGameActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(intent)
                }
            }
        dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .create()
        dialog.show()
        //set cancelable
        dialog.setCancelable(false)

    }

    fun showAnnouncerDialogComputer (context: Context, title: String) {

        var dialog: AlertDialog? = null
        val dialogBinding =
            DialogAnnouncerBinding.inflate((context as AppCompatActivity).layoutInflater).apply {
                tvDialogTitle.text = title
                    context.getString(
                        R.string.text_game_announcement
                    ).uppercase()
                //Play agin button
                btnDialogPlayAgain.setOnClickListener {
                    context.finish()
                    context.overridePendingTransition(0, 0)
                    context.startActivity(context.intent)
                    context.overridePendingTransition(0, 0)
                    Log.d("Hello", "New Game")
                }
                btnDialogMainMenu.setOnClickListener {
                    //navigate to Main Menu
                    val intent = Intent(context, MenuGameActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(intent)
                }
            }
        dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .create()
        dialog.show()
        dialog.setCancelable(false)

    }

}