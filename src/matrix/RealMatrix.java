package matrix;

public class RealMatrix implements Matrix<Double> {

    private final double[][] nodes;
    private final String name;

    public RealMatrix(int rows, int columns, String name) {
        this.nodes = new double[rows][columns];
        this.name = name;
    }

    @Override
    public Double getNode(int row, int column) {
        return nodes[row][column];
    }

    @Override
    public void setNode(int row, int column, Double node) {
        nodes[row][column] = node;
    }

    @Override
    public int rowsCount() {
        return nodes.length;
    }

    @Override
    public int columnsCount() {
        return nodes[0].length;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void swapRows(int rowNumber1, int rowNumber2) {
        if (rowNumber1 == rowNumber2) {
            return;
        }
        double[] tmp = nodes[rowNumber1];
        nodes[rowNumber1] = nodes[rowNumber2];
        nodes[rowNumber2] = tmp;
    }

    @Override
    public void swapColumns(int columnNumber1, int columnNumber2) {
        if (columnNumber1 == columnNumber2) {
            return;
        }
        for (int row = 0; row < rowsCount(); row++) {
            double tmp = nodes[row][columnNumber1];
            nodes[row][columnNumber1] = nodes[row][columnNumber2];
            nodes[row][columnNumber2] = tmp;
        }
    }
}
