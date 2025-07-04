import automaton.Cell;
import automaton.ElementaryCellularAutomaton;
import automaton.ElementaryCellularAutomaton.Window;

public class Main {
  public static final int ITERATIONS = 100;

  public static void main(String[] args) {
    var automaton = new ElementaryCellularAutomaton(Main::rule110, ITERATIONS);
    automaton.cells[ITERATIONS-1] = new Cell.Alive();

    System.out.println(Cell.toString(automaton.cells));
    for (var i=1; i < ITERATIONS; ++i) {
      automaton.step();
      System.out.println(Cell.toString(automaton.cells));
    }

    System.out.println();

    automaton = new ElementaryCellularAutomaton(Main::rule124, ITERATIONS);
    automaton.cells[0] = new Cell.Alive();

    System.out.println(Cell.toString(automaton.cells));
    for (var i=1; i < ITERATIONS; ++i) {
      automaton.step();
      System.out.println(Cell.toString(automaton.cells));
    }
  }

  private static Cell rule110(Window window) {
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

  private static Cell rule124(Window window) {
    return switch (window) {
      case Window(Cell.Alive a, Cell.Alive b, Cell.Alive c) -> Cell.DEAD;
      case Window(Cell.Alive a, Cell.Alive b, Cell.Dead  c) -> Cell.ALIVE;
      case Window(Cell.Alive a, Cell.Dead  b, Cell.Alive c) -> Cell.ALIVE;
      case Window(Cell.Alive a, Cell.Dead  b, Cell.Dead  c) -> Cell.ALIVE;
      case Window(Cell.Dead  a, Cell.Alive b, Cell.Alive c) -> Cell.ALIVE;
      case Window(Cell.Dead  a, Cell.Alive b, Cell.Dead  c) -> Cell.ALIVE;
      case Window(Cell.Dead  a, Cell.Dead  b, Cell.Alive c) -> Cell.DEAD;
      case Window(Cell.Dead  a, Cell.Dead  b, Cell.Dead  c) -> Cell.DEAD;
    };
  }
}
