package pieces;

import java.util.ArrayList;
import java.util.List;

import meta.Position;

public class Pawn extends Piece{
  public Pawn(String color, Position position){
    super("Pawn", color, position);
  }


  @Override
  public List<Position> getValidAttacks() {
    int offset = 1;
    if(this.color.equalsIgnoreCase("black")){
      offset = -1;
    }
    ArrayList<Position> validAttacks = new ArrayList<Position>();
    validAttacks.add(new Position((char)(position.getRow() + offset), (char)(position.getColumn() + 1)));
    validAttacks.add(new Position((char)(position.getRow() + offset), (char)(position.getColumn() - 1)));
    for(int i = 1; i <= 2; i++){
      char row = (char)(position.getRow() + offset);
      char col = (char)(position.getColumn() + (1 * -1));
      if(Position.isValidPosition(row, col))
        validAttacks.add(new Position(row, col));
    }
    return validAttacks;
  }

  @Override
  public List<Position> getValidMoves() {
    int curRow = (int)position.getRow();
    ArrayList<Position> validMoves = new ArrayList<Position>();
    int offset = 1;
    if(this.color.equalsIgnoreCase("black")){
      offset = -1;
    }
    int limit = 1;
    if(curRow == 1 || curRow == 6)
      limit = 2;
    for(int i = 1; i <= limit; i++){
      char row = (char)(position.getRow() + (offset * i));
      char col = position.getColumn();
      if(Position.isValidPosition(row, col))
        validMoves.add(new Position(row, col));
    }
    return validMoves;
  }
}