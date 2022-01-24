package com.refri.rockpaperscissorsthegame.usecase
import com.refri.rockpaperscissorsthegame.enum.MaterialSelection

class GameUseCaseImpl : GameUseCase {

    companion object{
        const val DRAW = 0
        const val PLAYER_WIN = 1
        const val COMPUTER_WIN = 2
    }

    override fun outcomeWinner(playerChoice: MaterialSelection?, computerChoice: MaterialSelection?): Int {
        return if (playerChoice == computerChoice) {
            DRAW
        } else if (
            computerChoice == MaterialSelection.ROCK && playerChoice == MaterialSelection.SCISSORS ||
            computerChoice == MaterialSelection.PAPER && playerChoice == MaterialSelection.ROCK ||
            computerChoice == MaterialSelection.SCISSORS && playerChoice == MaterialSelection.PAPER
        ) {
            COMPUTER_WIN
        } else {
            PLAYER_WIN
        }
    }
}
