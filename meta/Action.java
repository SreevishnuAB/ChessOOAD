package meta;

import java.util.List;

public interface Action {
  List<Position> getValidMoves();
  List<Position> getValidAttacks();
}
