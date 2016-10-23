import edu.princeton.cs.algs4.Queue;

import java.util.Observable;
/**
 *  @author Josh Hug
 */

public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;            edgeTo[v] is previous vertex on path from s to v
    public boolean[] marked;        marked[v] is true iff v connected to s
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at vertex x. */
    private void bfs(int s) {
        /* Your code here. */
        Queue<Integer> q = new Queue<>();

        // setting up starting vertex
        q.enqueue(s);

        marked[s] = true;

        while (!q.isEmpty()) {
            int v = q.dequeue();           // for freshly dequeued vertex v,

            for (int w : maze.adj(v)) {    // for each neighbor that is unmarked:
                if (!marked[w]) {
                    q.enqueue(w);          // enqueue that neighbor
                    marked[w] = true;      // mark it
                    edgeTo[w] = v;         // set its edgeTo to v

                    distTo[w] = distTo[v] + 1;
                    announce();

                    // mark and stop if we found target
                    if (w == t) {
                        targetFound = true;
                    }

                    if (targetFound) {
                        return;
                    }
                }
            }
        }



    }


    @Override
    public void solve() {
        bfs(s);
    }
}

