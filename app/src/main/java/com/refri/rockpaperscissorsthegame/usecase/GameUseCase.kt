package com.refri.rockpaperscissorsthegame.usecase

import com.refri.rockpaperscissorsthegame.enum.MaterialSelection

interface GameUseCase {
    fun outcomeWinner(playerChoice : MaterialSelection?,computerChoice : MaterialSelection?) : Int
}