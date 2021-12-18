package pieces;

import java.util.ArrayList;
import java.util.List;

import meta.Action;
import meta.Position;


public class Piece implements Action{
  protected String name;
  protected String color;
  protected Position position; //Posotion forms a component of Piece class

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public Piece(String name, String color, Position position){
    this.name = name;
    this.color = color;
    this.position = position;
  }

  // overloaded constructor for future enhancements,like implementation of Promotion
  public Piece(Piece piece){
    this.name = piece.getName();
    this.color = piece.getColor();
    this.position = piece.getPosition();
  }

  public List<Position> getValidMoves(){
    System.out.println("dummy implementation");
    return new ArrayList<Position>();
  }

  public List<Position> getValidAttacks(){
    System.out.println("dummy implementation");
    return new ArrayList<Position>();
  }

  @Override
  public String toString(){
    return this.name + "(" + this.color + ") @ " + this.position;
  }
}