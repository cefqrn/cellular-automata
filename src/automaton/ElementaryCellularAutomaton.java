package automaton;

import java.util.Arrays;
import java.util.function.Function;

public class ElementaryCellularAutomaton {
  public Function<Window, Cell> step;
  public Cell defaultState;
  public Cell[] cells;

  public ElementaryCellularAutomaton(Function<Window, Cell> step, int cellCount, Cell defaultState) {
    this.defaultState = defaultState;
    this.step = step;

    this.cells = new Cell[cellCount];
    Arrays.fill(this.cells, defaultState);
  }

  public ElementaryCellularAutomaton(Function<Window, Cell> step, int cellCount) {
    this(step, cellCount, Cell.DEAD);
  }

  public final Cell step(Cell left, Cell middle, Cell right) {
    return step.apply(new Window(left, middle, right));
  }

  public final ElementaryCellularAutomaton step() {
    var previous = defaultState;

    for (var i=0; i < cells.length-1; ++i)
      cells[i] = step(previous, previous=cells[i], cells[i+1]);

    if (cells.length > 0)
      cells[cells.length-1] = step(previous, cells[cells.length-1], defaultState);

    return this;
  }

  public record Window(Cell left, Cell middle, Cell right) {}
}
