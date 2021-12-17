package pieces;

import java.util.ArrayList;
import java.util.List;

import meta.Position;

public class Queen extends Piece{
  
  public Queen(String color, Position position){
    super("Queen", color, position);
  }

  private void addValidQueenMoveFromIdx(int row, int col, List<Position> validMoves){
    char newRow = Character.forDigit(row, 10);
    char newCol = Position.convertIndexToColumn(col);
    if(Position.isValidPosition(newRow, newCol))
      validMoves.add(new Position(newRow, newCol));
  }

  private List<Position> getValidQueenMoves(){
    char row = position.getRow();
    char col = position.getColumn();
    int colInt = Position.convertColumnToIndex(col);
    int rowInt =Character.getNumericValue(row);
    ArrayList<Position> validMoves = new ArrayList<Position>();
    for(int i = 1; i< 8; i++){
      addValidQueenMoveFromIdx(rowInt + i, colInt, validMoves);
      addValidQueenMoveFromIdx(rowInt - i, colInt, validMoves);
      addValidQueenMoveFromIdx(rowInt, colInt - i, validMoves);
      addValidQueenMoveFromIdx(rowInt, colInt + i, validMoves);
      addValidQueenMoveFromIdx(rowInt + i, colInt + i, validMoves);
      addValidQueenMoveFromIdx(rowInt - i, colInt + i, validMoves);
      addValidQueenMoveFromIdx(rowInt + i, colInt - i, validMoves);
      addValidQueenMoveFromIdx(rowInt - i, colInt - i, validMoves);
    }
    return validMoves;
  }

  @Override
  public List<Position> getValidAttacks() {
    return getValidQueenMoves();
  }

  @Override
  public List<Position> getValidMoves() {
    return getValidQueenMoves();
  }
}
