/****************************************************************************
 *  Compilation:  javac hw4/puzzle/Solver.java
 *  Execution:    java hw4/puzzle/Solver hw4/puzzle/input.txt
 *  Dependencies: Board.java, algs4.jar
 *
 * Solver for the 8-puzzle problem.
 * This implementation uses the A* search algorithm with Manhattan priority
 * function.
 *
 ****************************************************************************/

/**
 *
 * @author Maxim Butyrin
 *
 */

package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private SearchNode resultNode;
    private Stack<Board> solution;

    private class SearchNode implements Comparable<SearchNode> {

        private final Board board;
        private final int moves;
        private final int priorityManhattan;
        private final int priorityHamming;
        private final SearchNode parent;

        public SearchNode(Board board) {
            this(board, null);
        }

        public SearchNode(Board board, SearchNode parent) {
            this.board = board;
            this.parent = parent;

            if (parent == null) {
                moves = 0;
            } else {
                moves =  parent.moves + 1;
            }

            priorityManhattan = board.manhattan() + moves;
            priorityHamming = board.hamming() + moves;

        }

        /*
         * Defines SearchNode ordering by Manhattan priority function;
         * ties are solved by Hamming priority function.
         */
        @Override
        public int compareTo(SearchNode that) {
            if (this.priorityManhattan < that.priorityManhattan) {
                return -1;
            }
            if (this.priorityManhattan == that.priorityManhattan) {
                return this.priorityHamming < that.priorityHamming ? -1 :
                        this.priorityHamming == that.priorityHamming ? 0 : 1;
            }
            return 1;
        }
    }

    /*
    Constructor which solves the puzzle, computing
    everything necessary for moves() and solution() to
    not have to solve the problem again. Solves the
    puzzle using the A* algorithm. Assumes a solution exists
     */
    public Solver(Board initial) {

        solution = new Stack<>();
        MinPQ<SearchNode> originPQ = new MinPQ<>();
        SearchNode currentNode = new SearchNode(initial);
        Board prevBoard;

        originPQ.insert(currentNode);

        while (!currentNode.board.isGoal()) {

            currentNode = originPQ.delMin();

            if (currentNode.parent == null) {
                prevBoard = null;
            } else {
                prevBoard = currentNode.parent.board;
            }

            for (Board board : BoardUtils.neighbors(currentNode.board)) {
                if (!board.equals(prevBoard))
                    originPQ.insert(new SearchNode(board, currentNode));
            }
        }

        resultNode = currentNode;
        solution.push(currentNode.board);

        while (currentNode.parent != null) {
            currentNode = currentNode.parent;
            solution.push(currentNode.board);
            }



    }

    /*
    Returns the minimum number of moves to solve the
    initial board
     */
    public int moves() {
        return resultNode.moves;
    }

    /*
    Returns the sequence of Boards from the initial board
    to the solution.
     */
    public Iterable<Board> solution() {
        return solution;
    }

    // DO NOT MODIFY MAIN METHOD

    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board initial = new Board(tiles);
        Solver solver = new Solver(initial);
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution()) {
            StdOut.println(board);
       }
    }

}
