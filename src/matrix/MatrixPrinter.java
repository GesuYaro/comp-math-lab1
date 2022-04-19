package matrix;

import java.io.IOException;

public interface MatrixPrinter<T> {
    void printMatrix(Matrix<T> matrix) throws IOException;
}
