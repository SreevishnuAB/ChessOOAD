package pieces;

import java.util.ArrayList;
import java.util.List;

import meta.Position;

public class Bishop extends Piece{
  public Bishop(String color, Position position){
    super("Bishop", color, position);
  }

  private void addValidBishopMoveFromIdx(int row, int col, List<Position> validMoves){
    char newRow = Character.forDigit(row, 10);
    char newCol = Position.convertIndexToColumn(col);
    if(Position.isValidPosition(newRow, newCol))
      validMoves.add(new Position(newRow, newCol));
  }

  private List<Position> getValidBishopMoves(){
    char row = position.getRow();
    char col = position.getColumn();
    int colInt = Position.convertColumnToIndex(col);
    int rowInt =Character.getNumericValue(row);
    ArrayList<Position> validMoves = new ArrayList<Position>();
    for(int i = 1; i< 8; i++){
      addValidBishopMoveFromIdx(rowInt + i, colInt + i, validMoves);
      addValidBishopMoveFromIdx(rowInt - i, colInt + i, validMoves);
      addValidBishopMoveFromIdx(rowInt + i, colInt - i, validMoves);
      addValidBishopMoveFromIdx(rowInt - i, colInt - i, validMoves);
    }
    return validMoves;
  }

  @Override
  public List<Position> getValidAttacks() {
    return getValidBishopMoves();
  }

  @Override
  public List<Position> getValidMoves() {
    return getValidBishopMoves();
  }
}
