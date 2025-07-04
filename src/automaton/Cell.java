package automaton;

import java.util.Arrays;
import java.util.stream.Collectors;

public sealed interface Cell {
  public static Alive ALIVE = new Alive();
  public static Dead  DEAD  = new Dead();

  Cell flip();

  public static String toString(Cell[] cells) {
    return Arrays.stream(cells)
      .map(Cell::toString)
      .collect(Collectors.joining());
  }

  record Alive() implements Cell {
    @Override public Dead  flip() { return DEAD;  }
    @Override public String toString() { return "#"; }
  }

  record Dead()  implements Cell {
    @Override public Alive flip() { return ALIVE; }
    @Override public String toString() { return "."; }
  }
}
