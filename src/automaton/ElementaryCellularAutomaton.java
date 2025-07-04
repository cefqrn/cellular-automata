package automaton;

import java.util.Arrays;

public abstract class ElementaryCellularAutomaton {
  public Cell[] cells;

  protected ElementaryCellularAutomaton(int cellCount) {
    cells = new Cell[cellCount];
    Arrays.fill(cells, defaultState());
  }

  public abstract Cell step(Cell left, Cell middle, Cell right);

  public Cell defaultState() {
    return Cell.DEAD;
  }

  public final ElementaryCellularAutomaton step() {
    var previous = defaultState();

    for (var i=0; i < cells.length-1; ++i)
      cells[i] = step(previous, previous=cells[i], cells[i+1]);

    if (cells.length > 0)
      cells[cells.length-1] = step(previous, cells[cells.length-1], defaultState());

    return this;
  }
}
