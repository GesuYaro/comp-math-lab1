package iterations;

import matrix.Matrix;

public class Result<T> {

    private Matrix<T> matrix;
    private int iterations;

    public Result(Matrix<T> matrix, int iterations) {
        this.matrix = matrix;
        this.iterations = iterations;
    }

    public Matrix<T> getMatrix() {
        return matrix;
    }

    public int getIterations() {
        return iterations;
    }
}
