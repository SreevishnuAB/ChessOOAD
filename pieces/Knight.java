package pieces;

import java.util.ArrayList;
import java.util.List;

import meta.Position;

public class Knight extends Piece{
  public Knight(String color, Position position){
    super("Knight", color, position);
  }

  private void addValidKnightMoveFromIdx(int row, int col, List<Position> validMoves){
    char newRow = Character.forDigit(row, 10);
    char newCol = Position.convertIndexToColumn(col);
    if(Position.isValidPosition(newRow, newCol))
      validMoves.add(new Position(newRow, newCol));
  }

  private List<Position> getValidKnightMoves(){
    char row = position.getRow();
    char col = position.getColumn();
    int colInt = Position.convertColumnToIndex(col);
    int rowInt =Character.getNumericValue(row);
    ArrayList<Position> validMoves = new ArrayList<Position>();
    for(int i = 1; i < 3; i++){
      addValidKnightMoveFromIdx(rowInt + i, colInt + (3 - i), validMoves);
      addValidKnightMoveFromIdx(rowInt - i, colInt - (3 - i), validMoves);
    }
    return validMoves;
  }

  @Override
  public List<Position> getValidAttacks() {
    
    return getValidKnightMoves();
  }

  @Override
  public List<Position> getValidMoves() {
    return getValidKnightMoves();
  }
}
