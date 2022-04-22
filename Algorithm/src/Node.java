public class Node {

    private int row;

    private int column;

    private Node previous;

    private String pass;

    public Node(int rowNumber, int columnNumber) {
        this.row = rowNumber;
        this.column = columnNumber;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass= pass;
    }
}
