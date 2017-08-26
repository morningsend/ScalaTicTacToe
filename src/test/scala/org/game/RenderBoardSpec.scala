package org.game

import org.game.GameObjects.Square
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by zaiyangli on 19/08/2017.
  */
class RenderBoardSpec extends FlatSpec with Matchers {

  "renderBoard" should "make string" in {
    val board = Array.fill[Square.Value](9)(Square.EMPTY)
    val render = TicTacToeReducer.renderBoard(board)
    println(render)
  }

  "renderBoard" should "make string with all O board" in {
    val board = Array.fill(9)(Square.O)
    println(TicTacToeReducer.renderBoard(board))
  }
}
