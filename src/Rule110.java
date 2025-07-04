import automaton.Cell;
import automaton.ElementaryCellularAutomaton;

public class Rule110 extends ElementaryCellularAutomaton {
  public Rule110(int cellCount) {
    super(cellCount);
  }

  @Override
  public Cell step(Window window) {
    return switch (window) {
      case Window(Cell.Alive a, Cell.Alive b, Cell.Alive c) -> Cell.DEAD;
      case Window(Cell.Alive a, Cell.Alive b, Cell.Dead  c) -> Cell.ALIVE;
      case Window(Cell.Alive a, Cell.Dead  b, Cell.Alive c) -> Cell.ALIVE;
      case Window(Cell.Alive a, Cell.Dead  b, Cell.Dead  c) -> Cell.DEAD;
      case Window(Cell.Dead  a, Cell.Alive b, Cell.Alive c) -> Cell.ALIVE;
      case Window(Cell.Dead  a, Cell.Alive b, Cell.Dead  c) -> Cell.ALIVE;
      case Window(Cell.Dead  a, Cell.Dead  b, Cell.Alive c) -> Cell.ALIVE;
      case Window(Cell.Dead  a, Cell.Dead  b, Cell.Dead  c) -> Cell.DEAD;
    };
  }
}
