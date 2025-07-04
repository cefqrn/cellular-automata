import automaton.Cell;
import automaton.ElementaryCellularAutomaton;

public class Rule124 extends ElementaryCellularAutomaton {
  public Rule124(int cellCount) {
    super(cellCount);
  }

  @Override
  public Cell step(Cell a, Cell b, Cell c) {
    return switch (a) {
      case Cell.Alive x -> switch (b) {
        case Cell.Alive y -> c.flip();
        case Cell.Dead  y -> a;
      };
      case Cell.Dead  x -> b;
    };
  }
}
