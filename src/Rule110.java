import automaton.Cell;
import automaton.ElementaryCellularAutomaton;

public class Rule110 extends ElementaryCellularAutomaton {
  public Rule110(int cellCount) {
    super(cellCount);
  }

  @Override
  public Cell step(Cell a, Cell b, Cell c) {
    return switch (b) {
      case Cell.Alive y -> switch (a) {
        case Cell.Alive x -> c.flip();
        case Cell.Dead  x -> b;
      };
      case Cell.Dead  y -> c;
    };
  }
}
