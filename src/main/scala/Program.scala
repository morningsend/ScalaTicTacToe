import org.game.TicTacToeGame

/**
  * Created by zaiyangli on 15/08/2017.
  */
object Program {
  def main(args: Array[String]) : Unit = {
    val game = new TicTacToeGame()
    game.gameLoop()
  }
}
