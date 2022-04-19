package matrix;

import java.io.IOException;

public interface MatrixReader<T> {
    void readMatrix(Matrix<T> matrix) throws IOException;
}
