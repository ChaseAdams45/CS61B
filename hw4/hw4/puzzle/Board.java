/****************************************************************************
 *  Compilation:  javac hw4/puzzle/Board.java
 *  Execution:    none
 *  Dependencies: algs4.jar
 *
 *  Data structure representing the state of a two-dimensional NxN board for
 *  solving 8-puzzle problem.
 *  Each block of the board has an int value, and 0 represents an empty block.
 *
 ****************************************************************************/

/**
 *
 * @author Maxim Butyrin
 *
 */
package hw4.puzzle;
import java.util.Arrays;


public class Board {

    private final int N;   // size of the board side
    private final int[] blocks;     // array containing board blocks

    private int hamming = -1;       // initial value to enforce calculation
    private int manhattan = -1;     // initial value to enforce calculation


    /**
     * Construct a board from an N-by-N array of blocks
     * (where blocks[i][j] = block in row i, column j)
     * for memory optimization given 2d array converts to 1d array.
     *
     * @param blocks 2d int array representing each block of the board
     */
    public Board(int[][] blocks) {
        N = blocks.length;
        this.blocks = copyBlocks(blocks);
    }

    /**
     * Private constructor for creation twins and neighbors of the board.
     *
     * @param blocks      1d int array representing each block of the board
     * @param N  int size of the board side
     */
    private Board(int[] blocks, int N) {
        this.blocks = copyBlocks(blocks);
        this.N = N;
    }

    /*
     * helper for copying arrays
     */
    private int[] copyBlocks(int[][] source) {
        int[] copy = new int[source.length * source.length];
        for (int i = 0; i < source.length; i++) {
            int[] xs = source[i];
            int xsLength = xs.length;
            System.arraycopy(xs, 0, copy, i * source.length, xsLength);
        }
        return copy;
    }

    /*
     * helper for copying arrays
     */
    private int[] copyBlocks(int[] source) {
        int[] copy = new int[source.length];
        System.arraycopy(source, 0, copy, 0, source.length);
        return copy;
    }

    /**
     * Returns value of tile at row i, column j (or 0 if blank)
     * @param i row
     * @param j column
     * @return int value of tile at given coordinates
     */
    public int tileAt(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N ) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return blocks[i * N + j];
    }

    /**
     * Size of the side of this board.
     * @return size of the side of this board
     */
    public int size() {
        return N;
    }

    /**
     * Number of blocks in the wrong position.
     * Used for calculating Hamming priority function.
     *
     * @return int number of blocks in the wrong position
     */
    public int hamming() {

        if (hamming >= 0) { return hamming; }

        hamming = 0;

        for (int i = 0; i < blocks.length - 1; i++) {
            if (blocks[i] != i + 1) {
                hamming++;
            }
        }
        return hamming;
    }

    /**
     * Sum of Manhattan distances between blocks of this board and goal board.
     * Used for calculating Manhattan priority function.
     *
     * @return int sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {

        if (manhattan >= 0) { return manhattan; }

        manhattan = 0;

        // x-dimension, traversing cols
        for (int i = 0; i < N; i++)

            // y-dimension, traversing rows
            for (int j = 0; j < N; j++) {

                // we don't compute Manhattan distance for an empty block (0)
                if (blocks[i * N + j] != 0) {

                    int x = (blocks[i * N + j] - 1) / N;
                    int y = (blocks[i * N + j] - 1) - x * N;

                    // x-distance to expected coordinate
                    int dx = x - i;

                    // y-distance to expected coordinate
                    int dy = y - j;

                    manhattan += Math.abs(dx) + Math.abs(dy);
                }
            }
        return manhattan;
    }

    /**
     * Checks if this board is a goal board.
     *
     * @return true   if goal is reached
     *         false  otherwise
     */
    public boolean isGoal() {
        return hamming() == 0;
    }

    /**
     * Checks if given object is equal to this board
     * @param y the Object (Board) to compare to
     * @return true  if given object is equal to this board
     *         false otherwise
     */
    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null) {
            return false;
        }

        if (this.getClass() != y.getClass()) {
            return false;
        }

        Board that = (Board) y;

        return Arrays.equals(this.blocks, that.blocks);
    }


    /**
     * Returns String representation of this board.
     * @return String representation of this board
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
