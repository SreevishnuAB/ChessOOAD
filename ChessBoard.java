import java.util.List;

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

  private Piece getPieceByPosition(Position position)throws Exception{
    int row = Character.getNumericValue(position.getRow());
    int column = Position.convertColumnToIndex(position.getColumn());
    Piece piece = pieces[row][column];
    if(piece == null)
      throw new Exception("Chosen position is unoccupied");
    return piece;
  }

  public void movePiece(Position current, Position target)throws Exception{

    Piece piece = getPieceByPosition(current);
    List<Position> moves = piece.getValidMoves();
    int counter = 0;
    for(Position p: moves){
      if(p.getColumn() == target.getColumn() && p.getRow() == target.getRow())
        break;
      counter++;
    }
    if(counter == moves.size())
      throw new Exception("Invalid move");
    
    int row = Character.getNumericValue(target.getRow());
    int column = Character.getNumericValue(target.getColumn());
    pieces[row][column] = piece;

  }

  public void attackPiece(Position current, Position target)throws Exception{

    Piece piece = getPieceByPosition(current);
    try{
     getPieceByPosition(target);
    }
    catch(Exception e){
      System.out.println(e);
      throw new Exception("No piece occupies the target. Do you mean to move, instead of attack?");
    }
    List<Position> moves = piece.getValidMoves();
    int counter = 0;
    for(Position p: moves){
      if(p.getColumn() == target.getColumn() && p.getRow() == target.getRow())
        break;
      counter++;
    }
    if(counter == moves.size())
      throw new Exception("Invalid move");
    
    int row = Character.getNumericValue(target.getRow());
    int column = Character.getNumericValue(target.getColumn());
    pieces[row][column] = piece;

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
