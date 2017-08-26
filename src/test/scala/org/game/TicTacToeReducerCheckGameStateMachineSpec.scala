package org.game

import org.game.GameObjects._
import org.scalatest.FlatSpec
/**
  * Created by zaiyangli on 15/08/2017.
  */
class TicTacToeReducerCheckGameStateMachineSpec extends FlatSpec {
  "checkGameStateMachine input [X, X, X]" should "end X3" in {
    val game = TicTacToeReducer
      .checkGameStateMachine(Array(Square.X, Square.X, Square.X))

    assert(game == CheckGame.X3)
  }

  "checkGameStateMachine input [O, O, O]" should "end O3" in {
    val row = Array.fill[Square.Value](3)(Square.O)
    val game = TicTacToeReducer.checkGameStateMachine(row)
    assert(game == CheckGame.O3)
  }

  "checkGameStateMachine input [EMPTY, EMPTY EMPTY]" should "end ERROR" in {
    val row = Array.fill[Square.Value](3)(Square.EMPTY)
    val game = TicTacToeReducer.checkGameStateMachine(row)
    assert(game == CheckGame.ERROR)
  }

  "checkGameStateMachine input [O, O, X]" should "end ERROR" in {
    val row = Array(Square.O, Square.O, Square.X)
    val game = TicTacToeReducer.checkGameStateMachine(row)
    assert(game == CheckGame.ERROR)
  }

  "checkGameStateMachine input [EMPTY, EMPTY, X]" should "end ERROR" in {
    val row = Array(Square.EMPTY, Square.EMPTY, Square.X)
    val game = TicTacToeReducer.checkGameStateMachine(row)
    assert(game == CheckGame.ERROR)
  }
}
