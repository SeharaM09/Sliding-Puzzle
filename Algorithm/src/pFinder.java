import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class pFinder {

    private String fileName;
    private Node source;
    private char[][] matrix;
    private final boolean[][] brushed;
    private final ArrayList<Node> order = new ArrayList<>();
    private boolean pathSetup = false;

    public pFinder(char[][] grid, String fileName) {
        this.matrix = grid;
        this.fileName = fileName;
        this.brushed = new boolean[grid.length][grid.length];
    }


    public void spotter() {
        fLoop:
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                // Finding source
                if (matrix[i][j] == 'S') {
                    source = new Node(i, j);
                    break fLoop;
                }
            }
        }
    }

    public boolean Credibility(int row, int column) {
        if (row >= 0 && column >= 0
                && row < matrix.length && column < matrix[0].length
                && matrix[row][column] != '0'
                && !brushed[row][column]) {
            return true;
        }
        return false;
    }

    public void slider(Node node, int x, int y, String direction) {
        int row = node.getRow();
        int column = node.getColumn();

        while(true) {
            row += y;
            column += x;

            if (!Credibility(row, column)) {
                break;
            }

            if (matrix[row][column] == 'F') {
                Node neighbourNode = new Node(row, column);
                neighbourNode.setPrevious(node);
                neighbourNode.setPass(direction);

                order.add(0, neighbourNode);
                brushed[row][column] = true;
                break;
            }

            int nextRow  = row + y;
            int nextColumn = column + x;

            if ((nextRow < 0 || nextColumn < 0) || (nextRow >= matrix.length || nextColumn >= matrix.length) || (matrix[nextRow][nextColumn] == '0')) {
                Node neighbourNode = new Node(row, column);
                neighbourNode.setPrevious(node);
                neighbourNode.setPass(direction);

                order.add(neighbourNode);
                brushed[row][column] = true;
                break;
            }
        }
    }
    
    public void writingShortestP(Node node, long elapsedTime) throws IOException {
        ArrayList<String> track = new ArrayList<>();

        while (node.getPrevious() != null) {
            String step = "Move " + node.getPass() + " to " + "(" + (node.getColumn() + 1) + ", " + (node.getRow() + 1) + ")";
            track.add(step);
            node = node.getPrevious();
        }

        track.add("Start at " + "(" + (node.getColumn() + 1) + ", " + (node.getRow() + 1) + ")");

        FileWriter myWriter = new FileWriter("results/"  + "output.txt");

        int count = 1;

        Collections.reverse(track);

        String output = "";

        for (String step : track) {
            output += count + ". " + step + "\n";
            count += 1;
        }
        output += count + ". Done!\n\n";

        output += "Time elapsed : " + elapsedTime;

        myWriter.write(output);

        myWriter.close();
    }


    public void shortestPSpotter() throws IOException {
        long start = System.currentTimeMillis();
        long end = 0;
        spotter();

        order.add(source);
        brushed[source.getRow()][source.getColumn()] = true;
        Node nodeVisited = null;

        while (!order.isEmpty()) {
            nodeVisited = order.remove(0);
            int rowNumberVisited = nodeVisited.getRow();
            int columnNumberVisited = nodeVisited.getColumn();

            if (matrix[rowNumberVisited][columnNumberVisited] == 'F') {
                end = System.currentTimeMillis();
                pathSetup = true;
                break;
            }

            slider(nodeVisited, -1, 0, "left");
            slider(nodeVisited, 1, 0, "right");
            slider(nodeVisited, 0, 1, "bottom");
            slider(nodeVisited, 0, -1, "up");
        }

        if (pathSetup) {
            System.out.println("Path Found");
            writingShortestP(nodeVisited, end - start);
        } else {
            FileWriter myWriter = new FileWriter("results/" + fileName + "_results.txt");

            myWriter.write("No Path Found");

            myWriter.close();
            System.out.println("No Path Found");
        }
    }
}
