package meta;

public class Position {
  char row, column;

  public char getRow() {
    return row;
  }
  public void setRow(char row) {
    this.row = row;
  }
  public char getColumn() {
    return column;
  }
  public void setColumn(char column) {
    this.column = column;
  }

  public static boolean isValidPosition(char row, char col){
    if((col <= 'A' || col > 'H') || ((int)row < 0 || (int)row > 7 ))
      return false;
    return true;
  }

  public Position(char row, char column){
    this.row = row;
    this.column = column;
  }

  public static int convertColumnToIndex(char col){
    col = Character.toUpperCase(col);
    return col - 65;
  }

  public static char convertIndexToColumn(int col){
    return (char)(col + 65);
  }

  @Override
  public String toString(){
    return this.row + "" + this.getColumn();
  }
}
