import java.util.List;
import java.util.Scanner;

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
  Piece[][] pieces;  // Pieces array here exhibits polymorphism
  private ChessBoard() throws Exception{
    // if instance already exists, prevent creation of another
    if(instance != null)
      throw new Exception("Instance already created for singleton object");
    instance = this;
    pieces = new Piece[8][8];
  }

  // method to handle instance creation of this class, which is singleton by definition
  public static ChessBoard getInstance() throws Exception{
    if(instance == null)
      return new ChessBoard();
    return instance;
  }

  // factory method to create chess pieces
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

  private void movePiece(Position current, Position target)throws Exception{

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

  private void attackPiece(Position current, Position target)throws Exception{

    Piece piece = getPieceByPosition(current), targetPiece;
    try{
      targetPiece = getPieceByPosition(target);
    }
    catch(Exception e){
      System.out.println(e);
      throw new Exception("No piece occupies the target. Do you mean to move, instead of attack?");
    }
    if(piece.getColor().equalsIgnoreCase(targetPiece.getColor()))
      throw new Exception("Cannot retire piece of the same color");
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

  private void takeAction(String action, Position origin, Position target) throws ExitGameException, Exception{
    switch (action.toLowerCase()) {
      case "attack":
        attackPiece(origin, target);
        break;
      case "move":
        movePiece(origin, target);
        break;
      case "exit":
        throw new ExitGameException();
      default:
        throw new Exception("Invalid action");
    }
  }

  void takeTurn(String player) throws ExitGameException{
    Scanner sc = new Scanner(System.in);
    while(true){
      try{
        System.out.println("Player "+ player +": Enter action: Move/Attack/Exit");
        String action = sc.next();
        System.out.println("Player "+ player +": Enter starting position");
        String moveOrigin = sc.next();
        System.out.println("Player "+ player +": Enter target position");
        String moveTarget = sc.next();
        Position origin = new Position(moveOrigin.charAt(1), moveOrigin.charAt(0));
        Position target = new Position(moveTarget.charAt(1), moveTarget.charAt(0));
        takeAction(action, origin, target);
      }
      catch(ExitGameException exit){
        throw exit;
      }
      catch(Exception e){
        System.out.println("Player "+ player +": " + e +"Enter a valid action/move/attack");
        continue;
      }
      finally{
        sc.close();
      }
      break;
    }
  }

  public static void main(String[] args) {
    try{
      ChessBoard chessBoard = ChessBoard.getInstance();
      chessBoard.arrangePieces();
      while(true){
        chessBoard.takeTurn("1");
        chessBoard.takeTurn("2");
      }
    }
    catch(Exception e){
      System.out.println(e);
    }
  }
}
