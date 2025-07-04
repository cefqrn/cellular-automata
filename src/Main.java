import automaton.Cell;
import automaton.ElementaryCellularAutomaton;

public class Main {
  public static final int ITERATIONS = 100;

  public static void main(String[] args) {
    var rule110 = ElementaryCellularAutomaton.stepFrom(110);
    var rule124 = ElementaryCellularAutomaton.stepFrom(124);

    var automaton = new ElementaryCellularAutomaton(rule110, ITERATIONS);
    automaton.cells[ITERATIONS-1] = Cell.ALIVE;

    System.out.println(Cell.toString(automaton.cells));
    for (var i=1; i < ITERATIONS; ++i) {
      automaton.step();
      System.out.println(Cell.toString(automaton.cells));
    }

    System.out.println();

    automaton = new ElementaryCellularAutomaton(rule124, ITERATIONS);
    automaton.cells[0] = Cell.ALIVE;

    System.out.println(Cell.toString(automaton.cells));
    for (var i=1; i < ITERATIONS; ++i) {
      automaton.step();
      System.out.println(Cell.toString(automaton.cells));
    }
  }
}
