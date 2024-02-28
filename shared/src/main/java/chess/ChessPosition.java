package chess;

import java.util.Objects;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {
    int row;
    int Col;
    public ChessPosition(int roww, int coll) {
        row = roww;
        Col = coll;
    }

    /**
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {
        return row;
        //throw new RuntimeException("Not implemented");
    }

    /**
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn() {
        return Col;
        //throw new RuntimeException("Not implemented");
    }

    public int updateRow(int roww){
        row = roww;
        return row;
    }

    public int updateCol(int col){
        Col = col;
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPosition that=(ChessPosition) o;
        return row == that.row && Col == that.Col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, Col);
    }

    @Override
    public String toString() {
        return "ChessPosition{" +
                "row=" + row +
                ", Col=" + Col +
                '}';
    }
}
