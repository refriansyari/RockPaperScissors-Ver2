package com.refri.rockpaperscissorsthegame.game

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.refri.rockpaperscissorsthegame.R
import com.refri.rockpaperscissorsthegame.databinding.ActivityGameBinding
import com.refri.rockpaperscissorsthegame.enum.MaterialSelection
import com.refri.rockpaperscissorsthegame.enum.PlaySide
import com.refri.rockpaperscissorsthegame.game.dialogutlis.DialogUtils
import com.refri.rockpaperscissorsthegame.gamelistener.ComputerActionListener
import com.refri.rockpaperscissorsthegame.menu.MenuGameActivity
import com.refri.rockpaperscissorsthegame.preference.UserPreference
import com.refri.rockpaperscissorsthegame.usecase.GameUseCase
import com.refri.rockpaperscissorsthegame.usecase.GameUseCaseImpl


class GameActivity : AppCompatActivity() {

    //Variable Declaration
    private val TAG = GameActivity::class.simpleName
    private var playerChoice: MaterialSelection? = null             //extends and declare enum class element as null
    private var computerChoice: MaterialSelection? = null           //extends and declare enum class element as null
    private var battleResult: Int = 0
    private val EXTRAS_VERSUS_MODE = "EXTRAS_VERSUS_MODE"
    private var versusMode: Int = MenuGameActivity.COMPUTER_VERSUS_MODE
    private var playTurn: PlaySide = PlaySide.PLAYER
    //User Preference
    private val userPreference: UserPreference? by lazy {
        this.let { UserPreference(it) }
    }

    private lateinit var binding: ActivityGameBinding
    private lateinit var computerActionListener: ComputerActionListener
    private lateinit var gameUseCase: GameUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViews()
        setClickListenerRefresh()
        getIntentData()
        setInitialState(binding)

    }

    //get intent data from menu game activity
    private fun getIntentData() {
        versusMode = intent.extras?.getInt(EXTRAS_VERSUS_MODE, 0) ?: 0
    }

    private fun bindViews() {
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

    //Set initial state for player vs player mode
    private fun setInitialState(binding: ActivityGameBinding) {
        if (versusMode == MenuGameActivity.PLAYER_VERSUS_MODE) {
            Toast.makeText(this, getString(R.string.text_player_1_turn), Toast.LENGTH_SHORT)
                .show()
            //hide player 2 side
            showPlayerSide(PlaySide.PLAYER, true)
            showPlayerSide(PlaySide.COMPUTER, false)
            //remove vs sign
            binding.ivVsSign.setImageDrawable(null)
            startGame()

        } else {
            //player vs computer mode
            startGame()
        }
    }

    //visibility flags for Player and Computer
    private fun showPlayerSide(playSide: PlaySide, isVisible: Boolean) {
        if (playSide == PlaySide.PLAYER) {
            binding.llPlayer.isVisible = isVisible
        } else {
            binding.llComputer.isVisible = isVisible
        }
    }

    //Set action after clicked (player side)
    //it's a 1 flow methode
    private fun startGame() {
        //passing name using userPreference to Game Activity
        binding.tvPlayerName.text = userPreference?.name
        binding.flChoice1.setOnClickListener {
            binding.flChoice1.setBackgroundResource(R.drawable.ic_kapow)
            binding.ivChoice1.animate().apply {
                duration = 50
                scaleXBy(0.5f)
                scaleYBy(0.5f)

            }.withEndAction {
                binding.ivChoice1.animate().apply {
                    duration = 50
                    scaleXBy(-0.5f)
                    scaleYBy(-0.5f)
                }
            }.start()
            binding.flChoice2.setBackgroundResource(0)
            binding.flChoice3.setBackgroundResource(0)
            //Set value for playerChoice
            playerChoice = MaterialSelection.ROCK

            if (versusMode == MenuGameActivity.PLAYER_VERSUS_MODE) {
                //Player 2 choice
                player2Selection()
            } else {
                //Proceed to vs computer
                generateResult(binding)
            }

        }
        binding.flChoice2.setOnClickListener {
            binding.flChoice1.setBackgroundResource(0)
            binding.flChoice2.setBackgroundResource(R.drawable.ic_explosion)
            binding.ivChoice2.animate().apply {
                duration = 50
                scaleXBy(0.5f)
                scaleYBy(0.5f)
            }.withEndAction {
                binding.ivChoice2.animate().apply {
                    duration = 50
                    scaleXBy(-0.5f)
                    scaleYBy(-0.5f)
                }
            }.start()
            binding.flChoice3.setBackgroundResource(0)
            playerChoice = MaterialSelection.PAPER
            if (versusMode == MenuGameActivity.PLAYER_VERSUS_MODE) {
                player2Selection()
            } else {
                generateResult(binding)
            }
        }
        binding.flChoice3.setOnClickListener {
            binding.flChoice1.setBackgroundResource(0)
            binding.flChoice2.setBackgroundResource(0)
            binding.flChoice3.setBackgroundResource(R.drawable.ic_thunder_explosion)
            binding.ivChoice3.animate().apply {
                duration = 50
                scaleXBy(0.5f)
                scaleYBy(0.5f)
            }.withEndAction {
                binding.ivChoice3.animate().apply {
                    duration = 50
                    scaleXBy(-0.5f)
                    scaleYBy(-0.5f)
                }
            }.start()
            playerChoice = MaterialSelection.SCISSORS
            if (versusMode == MenuGameActivity.PLAYER_VERSUS_MODE) {
                player2Selection()
            } else {
                generateResult(binding)
            }
        }
    }

    //Player 2 turn
    private fun player2Selection() {
        if (versusMode == MenuGameActivity.PLAYER_VERSUS_MODE) {
            //Set player 2 turn
            if (playTurn == PlaySide.PLAYER) {
                playTurn = PlaySide.COMPUTER
                Toast.makeText(this, getString(R.string.text_player_2_turn), Toast.LENGTH_SHORT)
                    .show()
                showPlayerSide(PlaySide.PLAYER, false)
                showPlayerSide(PlaySide.COMPUTER, true)

                //change opponents name from computer -> player 2
                binding.tvComputerName.text = getString(R.string.text_player_2)
            }
            binding.flChoice4.setOnClickListener {
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
                //Set value for computer Choice on PLAYER VERSUS MODE
                computerChoice = MaterialSelection.ROCK
                //call generateResult function
                generateResult(binding)
            }
            binding.flChoice5.setOnClickListener {
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
                generateResult(binding)
            }
            binding.flChoice6.setOnClickListener {
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
                generateResult(binding)
            }
        }
    }

    private fun generateResult(binding: ActivityGameBinding) {
        //defining object
        computerActionListener = ComputerActionListener()
        gameUseCase = GameUseCaseImpl()

        if (versusMode == MenuGameActivity.PLAYER_VERSUS_MODE) {
            //PLAYER VERSUS MODE
            if (playerChoice != null && computerChoice != null) { //playerChoice and computerChoice value has been set
                battleResult = gameUseCase.outcomeWinner(playerChoice, computerChoice)     // proceed to battle result
                showBattleResult(battleResult)
            }
        } else {
            //COMPUTER VERSUS MODE
            //calling function to set Random Choice
            computerActionListener.setRandomChoice(binding)
            //set computerChoice value for COMPUTER VERSUS MODE
            computerChoice = computerActionListener.computerChoice
            battleResult = gameUseCase.outcomeWinner(playerChoice, computerChoice)
            showBattleResult(battleResult)
        }

    }


    private fun showBattleResult(battleResult: Int) {
        //Showing battle result
        when (battleResult) {
            GameUseCaseImpl.PLAYER_WIN -> {
                //Delay time for dialog after being called
                delay(500) {
                    //call DialogUtils object
                    DialogUtils.showAnnouncerDialogPlayer(this)
                    Log.d(ContentValues.TAG, "Player Win")
                }
            }
            GameUseCaseImpl.COMPUTER_WIN -> {
                Log.d(ContentValues.TAG, "Computer Win")
                if (versusMode == MenuGameActivity.PLAYER_VERSUS_MODE) {
                    delay(500) {
                        DialogUtils.showAnnouncerDialogComputer(
                            this,
                            getString(R.string.text_player_2_win)
                        )
                    }
                } else {
                    delay(500) {
                        DialogUtils.showAnnouncerDialogComputer(
                            this,
                            getString(R.string.text_computer_win)
                        )
                    }
                }

            }
            GameUseCaseImpl.DRAW -> {
                delay(500) {
                    DialogUtils.showAnnouncerDialogComputer(this, getString(R.string.text_draw))
                    Log.d(ContentValues.TAG, "Draw")
                }
            }
        }
        //Showing UI after game is finished
        showPlayerSide(PlaySide.PLAYER, true)
        showPlayerSide(PlaySide.COMPUTER, true)
        binding.ivVsSign.setImageResource(R.drawable.ic_vs_sign)
    }

    //delay method
    private inline fun delay(delay: Long, crossinline completion: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            completion()
        }, delay)
    }


    private fun setClickListenerRefresh() {
        binding.ivRefresh.setOnClickListener {
            //Set Refresh Activity
            finish()
            overridePendingTransition(0, 0)
            startActivity(intent)
            overridePendingTransition(0, 0)
            Log.d(TAG, "New Game")
        }
    }
}




