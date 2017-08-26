package org.game

/**
  * Created by zaiyangli on 15/08/2017.
  */


object GameObjects{
  object CheckGame extends Enumeration {
    val START, O1, O2, O3, X1, X2, X3, ERROR = Value
  }

  object Square extends Enumeration {
    type Square = Value
    val EMPTY = Value(" ")
    val X = Value("X")
    val O = Value("O")
  }

  object GameState extends Enumeration {
    val XWIN, OWIN, DRAW, RUNNING = Value
  }

  object Player extends Enumeration {
    val X = Value("X")
    val O = Value("O")
  }

  val rowsColumnsDiagonalIndices : Array[Array[Int]] = Array(
    Array(0, 3, 6),
    Array(1, 4, 7),
    Array(2, 5, 8),
    Array(0, 1, 2),
    Array(3, 4, 5),
    Array(6, 7, 8),
    Array(0, 4, 8),
    Array(2, 4, 6)
  )

  case class Move(player: Player.Value, position: Int)
  case class TicTacToe(board: Array[Square.Value],
                       nextPlayerTurn: Player.Value,
                       state: GameState.Value,
                       moves: List[Move])
  val InitialTicTacToe = new TicTacToe(
    Array.fill[Square.Value](9)(Square.EMPTY),
    Player.X,
    GameState.RUNNING,
    List()
  )
}