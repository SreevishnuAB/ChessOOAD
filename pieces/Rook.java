package pieces;

import java.util.ArrayList;
import java.util.List;

import meta.Position;

public class Rook extends Piece{
  public Rook(String color, Position position){
    super("Rook", color, position);
  }

  private void addValidRookMoveFromIdx(int row, int col, List<Position> validMoves){
    char newRow = Character.forDigit(row, 10);
    char newCol = Position.convertIndexToColumn(col);
    if(Position.isValidPosition(newRow, newCol))
      validMoves.add(new Position(newRow, newCol));
  }

  private List<Position> getValidRookMoves(){
    char row = position.getRow();
    char col = position.getColumn();
    int colInt = Position.convertColumnToIndex(col);
    int rowInt =Character.getNumericValue(row);
    ArrayList<Position> validMoves = new ArrayList<Position>();
    for(int i = 1; i< 8; i++){
      addValidRookMoveFromIdx(rowInt + i, colInt, validMoves);
      addValidRookMoveFromIdx(rowInt - i, colInt, validMoves);
      addValidRookMoveFromIdx(rowInt, colInt - i, validMoves);
      addValidRookMoveFromIdx(rowInt, colInt + i, validMoves);
    }
    return validMoves;
  }

  @Override
  public List<Position> getValidAttacks() {
    return getValidRookMoves();
  }

  @Override
  public List<Position> getValidMoves() {
    return getValidRookMoves();
  }
}
