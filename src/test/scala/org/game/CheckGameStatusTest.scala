package org.game

import org.game.GameObjects.{GameState, Square}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by zaiyangli on 15/08/2017.
  */
class CheckGameStatusTest extends FlatSpec with Matchers{

  "A board with row of X" should "return XWIN" in {
    val board = Array[Square.Value](
      Square.X, Square.X, Square.X,
      Square.X, Square.O, Square.EMPTY,
      Square.O, Square.X, Square.O
    )
    val state = TicTacToeReducer.checkGameStatus(board)
    assert(state == GameState.XWIN)
  }

  "A board with row of O" should "return OWIN" in {
    val board = Array[Square.Value](
      Square.X, Square.O, Square.X,
      Square.X, Square.O, Square.EMPTY,
      Square.O, Square.O, Square.X
    )
    val state = TicTacToeReducer.checkGameStatus(board)
    assert(state == GameState.OWIN)
  }

  "A full board with no threes in a row" should "return DRAW" in {
    val board = Array[Square.Value](
      Square.X, Square.O, Square.X,
      Square.X, Square.O, Square.O,
      Square.O, Square.X, Square.X
    )
    val state = TicTacToeReducer.checkGameStatus(board)
    assert(state == GameState.DRAW)
  }

  "A board no threes in a row but not filled" should "return RUNNING" in {
    val board = Array[Square.Value](
      Square.X, Square.O, Square.X,
      Square.X, Square.O, Square.EMPTY,
      Square.O, Square.X, Square.EMPTY
    )
    val state = TicTacToeReducer.checkGameStatus(board)
    assert(state == GameState.RUNNING)
  }
}
