package pieces;

import java.util.ArrayList;
import java.util.List;

import meta.Position;

public class King extends Piece{
  public King(String color, Position position){
    super("King", color, position);
  }

  private void addValidKingMoveFromIdx(int row, int col, List<Position> validMoves){
    char newRow = Character.forDigit(row, 10);
    char newCol = Position.convertIndexToColumn(col);
    if(Position.isValidPosition(newRow, newCol))
      validMoves.add(new Position(newRow, newCol));
  }

  private List<Position> getValidKingMoves(){
    char row = position.getRow();
    char col = position.getColumn();
    int colInt = Position.convertColumnToIndex(col);
    int rowInt =Character.getNumericValue(row);
    ArrayList<Position> validMoves = new ArrayList<Position>();
    addValidKingMoveFromIdx(rowInt + 1, colInt, validMoves);
    addValidKingMoveFromIdx(rowInt - 1, colInt, validMoves);
    addValidKingMoveFromIdx(rowInt, colInt - 1, validMoves);
    addValidKingMoveFromIdx(rowInt, colInt + 1, validMoves);
    addValidKingMoveFromIdx(rowInt + 1, colInt + 1, validMoves);
    addValidKingMoveFromIdx(rowInt - 1, colInt + 1, validMoves);
    addValidKingMoveFromIdx(rowInt + 1, colInt - 1, validMoves);
    addValidKingMoveFromIdx(rowInt - 1, colInt - 1, validMoves);
    return validMoves;
  }

  @Override
  public List<Position> getValidAttacks() {
    return getValidKingMoves();
  }

  @Override
  public List<Position> getValidMoves() {
    return getValidKingMoves();
  }
}
