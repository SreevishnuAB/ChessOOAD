import meta.Position;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class ChessBoard {
  
  static ChessBoard instance = null;
  Piece[][] pieces;
  private ChessBoard() throws Exception{
    if(instance != null)
      throw new Exception("Instance already created for singleton object");
    instance = this;
    pieces = new Piece[8][8];
  }

  public static ChessBoard getInstance() throws Exception{
    if(instance == null)
      return new ChessBoard();
    return instance;
  }

  private Piece getPieces(int col, char row, String color){
    Position position = new Position(row, Position.convertIndexToColumn(col));
    switch (col) {
      case 0:
      case 7:
        return new Rook(color, position);
      case 1:
      case 6:
        return new Knight(color, position);
      case 2:
      case 5:
        return new Bishop(color, position);
      case 3:
        return new Queen(color, position);
      case 4:
        return new King(color, position);
      default:
        return new Pawn(color, position);
    }
  }

  public void arrangePieces(){
    for(int i = 0; i < 8;i++){
      pieces[0][i] = getPieces(i, '1', "white");
      pieces[1][i] = getPieces(i+7, '2', "white");
      pieces[7][i] = getPieces(i, '8', "black");
      pieces[6][i] = getPieces(i+7, '7', "black");
    }
  }

  public static void main(String[] args) {
    try{
      ChessBoard chessBoard = ChessBoard.getInstance();
      chessBoard.arrangePieces(); 
    }
    catch(Exception e){
      System.out.println(e);
    }
  }
}
