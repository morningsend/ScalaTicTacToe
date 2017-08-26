package org.game

import org.game.GameObjects._
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by zaiyangli on 19/08/2017.
  */
class calculateNextGameStepSpecs extends FlatSpec with Matchers {

  "a board with XWIN game" should "return same step" in {
    val ticTacToe = TicTacToe(
      InitialTicTacToe.board,
      InitialTicTacToe.nextPlayerTurn,
      GameState.XWIN,
      InitialTicTacToe.moves
    )
    val nextTicTacToe = TicTacToeReducer.calculateNextGameStep(ticTacToe, Move(Player.X, 0))
    assert(ticTacToe == nextTicTacToe)
  }

  "a board with DRAW game" should "return same step" in {
    val ticTacToe = TicTacToe(
      InitialTicTacToe.board,
      InitialTicTacToe.nextPlayerTurn,
      GameState.DRAW,
      InitialTicTacToe.moves
    )
    val nextTicTacToe = TicTacToeReducer.calculateNextGameStep(ticTacToe, Move(Player.X, 0))
    assert(ticTacToe == nextTicTacToe)
  }
  "a board with OWIN game" should "return same step" in {
    val ticTacToe = TicTacToe(
      InitialTicTacToe.board,
      InitialTicTacToe.nextPlayerTurn,
      GameState.OWIN,
      InitialTicTacToe.moves
    )
    val nextTicTacToe = TicTacToeReducer.calculateNextGameStep(ticTacToe, Move(Player.X, 0))
    assert(ticTacToe == nextTicTacToe)
  }
  "make a move on empty board" should "return game with RUNNING state " in {
    val move = Move(Player.X, 0)
    val nextTicTacToe = TicTacToeReducer.calculateNextGameStep(InitialTicTacToe, move)
    assert(nextTicTacToe.state == GameState.RUNNING)
  }

  "move a move on a board" should "return game with different player" in {
    val move = Move(Player.X, 0)
    val nextTicTacToe = TicTacToeReducer.calculateNextGameStep(InitialTicTacToe, move)
    assert(nextTicTacToe.nextPlayerTurn != InitialTicTacToe.nextPlayerTurn)
  }

  "make a move" should "append to move history" in {
    val move = Move(Player.X, 0)
    val nextTicTacToe = TicTacToeReducer.calculateNextGameStep(InitialTicTacToe, move)
    assert(!nextTicTacToe.moves.isEmpty)
    assert(nextTicTacToe.moves.head == move)
  }
}
