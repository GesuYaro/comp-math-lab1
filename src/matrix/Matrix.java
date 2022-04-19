package matrix;

public interface Matrix<T> {

    T getNode(int row, int column);
    void setNode(int row, int column, T node);
    int rowsCount();
    int columnsCount();
    String getName();
    void swapRows(int rowNumber1, int rowNumber2);
    void swapColumns(int columnNumber1, int columnNumber2);

}
