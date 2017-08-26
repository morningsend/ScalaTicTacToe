package org.game

import java.util

import GameObjects._
/**
  * Created by zaiyangli on 15/08/2017.
  */
object TicTacToeReducer {

  def calculateNextGameStep(ticTacToe: TicTacToe, move: Move): TicTacToe = {
    if(ticTacToe.state != GameState.RUNNING){
      return ticTacToe
    }
    if(!isMoveValid(ticTacToe.board, move)) {
      throw new RuntimeException("invalid move: " + move)
    }
    val nextPlayer = if (ticTacToe.nextPlayerTurn == Player.X)
      Player.O
    else Player.X
    val newBoard = nextBoard(ticTacToe.board, move)
    val nextState = checkGameStatus(newBoard)

    TicTacToe(newBoard, nextPlayer, nextState, move :: ticTacToe.moves)
  }

  def isMoveValid(board: Array[Square.Value], move: Move) : Boolean = {
    (move.position >= 0 && move.position < 9 ) && (board(move.position) == Square.EMPTY)
  }

  def nextBoard(board: Array[Square.Value], move: Move) : Array[Square.Value] = {
    val newBoard = util.Arrays.copyOf(board, board.length)
    newBoard(move.position) = if(move.player == Player.X) Square.X else Square.O
    newBoard
  }

  def checkGameStatus(board: Array[Square.Value]) : GameState.Value = {
    val rowsCols = rowsColumnsDiagonalIndices.map( (indices: Array[Int]) => {
      indices.map((n: Int) => board(n))
    })
    val checks = rowsCols.map(checkGameStateMachine)
    val finalCheck = checks.reduce(
      (a: CheckGame.Value, b: CheckGame.Value) =>
        (a, b) match {
          case (CheckGame.O3, _) => CheckGame.O3
          case (_, CheckGame.O3) => CheckGame.O3
          case (CheckGame.X3, _) => CheckGame.X3
          case (_, CheckGame.X3) => CheckGame.X3
          case (_, _) => CheckGame.ERROR
        }
    )
    finalCheck match {
      case CheckGame.ERROR =>
        if (board.contains(Square.EMPTY))
          GameState.RUNNING
        else
          GameState.DRAW

      case CheckGame.O3 => GameState.OWIN
      case CheckGame.X3 => GameState.XWIN
    }
  }

  def checkGameStateMachine(rowColOrDiag: Array[Square.Value]) : CheckGame.Value = {
    var state = CheckGame.START
    for( square <- rowColOrDiag) {
      state = square match {
        case Square.EMPTY => CheckGame.ERROR
        case Square.O => state match {
          case CheckGame.START => CheckGame.O1
          case CheckGame.O1 => CheckGame.O2
          case CheckGame.O2 => CheckGame.O3
          case CheckGame.O3 => CheckGame.O3
          case _ => CheckGame.ERROR
        }
        case Square.X => state match {
          case CheckGame.START => CheckGame.X1
          case CheckGame.X1 => CheckGame.X2
          case CheckGame.X2 => CheckGame.X3
          case CheckGame.X3 => CheckGame.X3
          case _ => CheckGame.ERROR
        }
      }
    }
    state
  }

  def renderBoard(board: Array[Square.Value]) : String = {
    val stringBuilder = new StringBuilder
    for(i <- 0 to 2) {
      stringBuilder.append(s"${board(0 + i * 3)}|${board(1 + i * 3)}|${board(2 + i * 3)}\n")
    }
    stringBuilder.toString
  }
}
