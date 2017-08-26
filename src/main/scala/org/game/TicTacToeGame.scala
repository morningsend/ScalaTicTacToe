package org.game

import GameObjects._
/**
  * Created by zaiyangli on 26/08/2017.
  */
class TicTacToeGame(initialState: TicTacToe = InitialTicTacToe) {
  var currentState = initialState
  def gameLoop() : Unit = {
    while(currentState.state == GameState.RUNNING){
      println(printBoard(currentState.board))
      val move = input(currentState)
      currentState = TicTacToeReducer
        .calculateNextGameStep(
          currentState,
          move
        )
    }
    endGame(currentState)
  }
  def input(state: TicTacToe) : Move = {
    print(s"Player ${state.nextPlayerTurn}'s turn [1-9]: ")
    var n = scala.io.StdIn.readInt() - 1
    while(!TicTacToeReducer.isMoveValid(state.board, new Move(state.nextPlayerTurn, n))) {
      print("Invalid move, try again: ")
      n = scala.io.StdIn.readInt() - 1
    }
    new Move(state.nextPlayerTurn, n)
  }

  def printBoard(board: Array[Square.Value]) : String = {
    TicTacToeReducer.renderBoard(board)
  }
  def endGame(ticTacToe: TicTacToe): Unit = {
    println(printBoard(ticTacToe.board))
    ticTacToe.state match {
      case GameState.OWIN => println("Game end with O wins")
      case GameState.XWIN => println("Game ended with X wins")
      case GameState.DRAW => println("Game ended in a draw")
      case GameState.RUNNING => println("Game is still going on.")
    }
  }
}
