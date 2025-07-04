package automaton;

import java.util.Arrays;
import java.util.Objects;

public final class ElementaryCellularAutomaton {
  public final int wolframCode;
  public final Cell defaultState;
  public final Cell[] cells;

  public ElementaryCellularAutomaton(int wolframCode, int cellCount, Cell defaultState) {
    if (wolframCode < 0 || 255 < wolframCode)
      throw new IllegalArgumentException("Wolfram code must be between 0 and 255 (got " + wolframCode + ")");

    if (cellCount < 0)
      throw new IllegalArgumentException("cell count must be nonnegative (got " + cellCount + ")");

    this.defaultState = Objects.requireNonNull(defaultState, "default state must be non-null");;
    this.wolframCode = wolframCode;

    this.cells = new Cell[cellCount];
    Arrays.fill(this.cells, defaultState);
  }

  public ElementaryCellularAutomaton(int wolframCode, int cellCount) {
    this(wolframCode, cellCount, Cell.DEAD);
  }

  public Cell step(Cell left, Cell middle, Cell right) {
    var bit = (left   instanceof Cell.Alive ? 1 << 2 : 0)
            | (middle instanceof Cell.Alive ? 1 << 1 : 0)
            | (right  instanceof Cell.Alive ? 1 << 0 : 0);

    return (wolframCode & (1 << bit)) != 0 ? Cell.ALIVE : Cell.DEAD;
  }

  public ElementaryCellularAutomaton step() {
    var previous = defaultState;

    for (var i=0; i < cells.length-1; ++i)
      cells[i] = step(previous, previous=cells[i], cells[i+1]);

    if (cells.length > 0)
      cells[cells.length-1] = step(previous, cells[cells.length-1], defaultState);

    return this;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof ElementaryCellularAutomaton o
        && o.wolframCode == wolframCode
        && o.defaultState == defaultState
        && Arrays.equals(o.cells, cells);
  }
}
