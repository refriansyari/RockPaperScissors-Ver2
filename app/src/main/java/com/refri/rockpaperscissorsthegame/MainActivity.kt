package com.refri.rockpaperscissorsthegame

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.refri.rockpaperscissorsthegame.databinding.ActivityMainBinding
import com.refri.rockpaperscissorsthegame.enum.MaterialSelection
import com.refri.rockpaperscissorsthegame.gamelistener.ComputerActionListener
import com.refri.rockpaperscissorsthegame.gamelistener.PrintBattleResult
import com.refri.rockpaperscissorsthegame.usecase.GameUseCase
import com.refri.rockpaperscissorsthegame.usecase.GameUseCaseImpl


class MainActivity : AppCompatActivity() {

    //Variable Declaration
    private val TAG = MainActivity::class.simpleName
    private var playerChoice: MaterialSelection? = null             //extends and declare enum class element as null
    private var computerChoice: MaterialSelection? = null           //extends and declare enum class element as null
    private var battleResult: Int = 0

    private lateinit var binding: ActivityMainBinding
    private lateinit var computerActionListener: ComputerActionListener
    private lateinit var printBattleResult: PrintBattleResult
    private lateinit var gameUseCase: GameUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViews()
        setClickListenerRefresh()
        startGame(binding,this)
    }

    private fun bindViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

    private fun startGame(binding: ActivityMainBinding, context: Context) {

        //Set action after clicked (player side)
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
            playerChoice = MaterialSelection.ROCK               //Set player choice value
            generateResult(binding, context)


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
            generateResult(binding, context)
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
            generateResult(binding, context)

        }
    }

    private fun generateResult(binding: ActivityMainBinding, context: Context) {
        //defining object
        computerActionListener = ComputerActionListener()
        gameUseCase = GameUseCaseImpl()
        printBattleResult = PrintBattleResult()

        computerActionListener.setRandomChoice(binding)                         //Set Random Number
        computerChoice = computerActionListener.computerChoice                  //Set computerChoice as Variable
        battleResult = gameUseCase.outcomeWinner(playerChoice, computerChoice)  //Deciding winner process
        printBattleResult.showBattleResult(binding, battleResult, context)      //Print result

    }

    private fun setClickListenerRefresh() {
        binding.ivRefresh.setOnClickListener {
            //Set Refresh Activity
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
            Log.d(TAG, "New Game")
        }
    }
}



