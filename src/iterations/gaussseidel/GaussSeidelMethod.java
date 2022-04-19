package iterations.gaussseidel;

import iterations.AbstractIterationMethod;
import iterations.Result;
import matrix.Matrix;
import matrix.MatrixException;
import matrix.RealMatrix;

public class GaussSeidelMethod extends AbstractIterationMethod {

    private double newX(Matrix<Double> matrixA, Matrix<Double> matrixB, int row, double[] x) {
        double sub = 0;
        for (int i = 0; i < matrixA.columnsCount(); i++) {
            if (i != row) {
                sub += matrixA.getNode(row, i) * x[i];
            }
        }
        return (matrixB.getNode(row, 0) - sub) / matrixA.getNode(row, row);
    }

    private double findBiggestDifference(double[] x, double[] xPrevious) {
        double biggestDifference = 0;
        for (int i = 0; i < x.length; i++) {
            double dif = Math.abs(x[i] - xPrevious[i]);
            if (dif > biggestDifference) {
                biggestDifference = dif;
            }
        }
        return biggestDifference;
    }

    public Result<Double> solve(Matrix<Double> matrixA, Matrix<Double> matrixB, double e) {
        if (matrixA.rowsCount() != matrixB.rowsCount()) {
            throw new MatrixException("Матрицы имеют разное количество строк");
        }
        int xCount = matrixA.rowsCount();
        double[] x = new double[xCount];
        double[] xPrevious = new double[xCount];
        for (int i = 0; i < xCount; i++) {
            x[i] = 0;
        }

        int iterationNumber = 0;
        do {
            for (int i = 0; i < xCount; i++) {
                xPrevious[i] = x[i];
                x[i] = newX(matrixA, matrixB, i, x);
            }
            ++iterationNumber;
//            System.out.println("Итерация № " + ++iterationNumber);
//            debugInfo(x);
        } while (findBiggestDifference(x, xPrevious) > e);

        Matrix<Double> answers = new RealMatrix(xCount, 1, "Answers");
        for (int i = 0; i < xCount; i++) {
            answers.setNode(i, 0, x[i]);
        }
        return new Result(answers, iterationNumber);
    }

    private void debugInfo(double[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println();
    }

}
