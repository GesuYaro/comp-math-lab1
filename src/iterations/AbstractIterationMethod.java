package iterations;

import matrix.Matrix;

public abstract class AbstractIterationMethod {

    public boolean isDiagonallyDominant(Matrix<Double> matrix) {
        boolean haveStrictlyGreater = false;
        int rows = matrix.rowsCount();
        int cols = matrix.columnsCount();
        if (matrix.columnsCount() == matrix.rowsCount()) { // is matrix square
            for (int row = 0; row < rows; row++) {
                int sum = 0;
                for (int col = 0; col < cols; col++) {
                    if (col != row) { // removing diagonal element from sum
                        sum += Math.abs(matrix.getNode(row, col));
                    }
                }
                if (Math.abs(matrix.getNode(row, row)) > sum) { // checking if at least in one row there are strict inequality
                    haveStrictlyGreater = true;
                }
                if (Math.abs(matrix.getNode(row, row)) < sum) {
                    return false;
                }
                if (matrix.getNode(row, row) == 0) {
                    return false;
                }
            }
        } else {
            return false; // matrix isn't square
        }
        return haveStrictlyGreater;
    }

    public boolean toDiagonallyDominant(Matrix<Double> matrix) {
        for (int row = 0; row < matrix.rowsCount(); row++) {
            int dominantIndex = findRowDominantIndex(matrix, row);
            if (dominantIndex >= row) {
                matrix.swapColumns(row, dominantIndex);
            } else {
                return false;
            }
        }
        return isDiagonallyDominant(matrix);
    }

    private int findBiggestAbs(Matrix<Double> matrix, int row) {
        int index = 0;
        double biggest = matrix.getNode(row, 0);
        for (int i = 1; i < matrix.columnsCount(); i++) {
            double cur = matrix.getNode(row, i);
            if (Math.abs(cur) > biggest) {
                index = i;
                biggest = cur;
            }
        }
        return index;
    }

    private int findRowDominantIndex(Matrix<Double> matrix, int row) {
        int biggestAbsIndex = findBiggestAbs(matrix, row);
        double sub = 0;
        for (int i = 0; i < matrix.columnsCount(); i++) {
            if (biggestAbsIndex != i) {
                sub += matrix.getNode(row, i);
            }
        }
        if (matrix.getNode(row, biggestAbsIndex) >= sub){
            return biggestAbsIndex;
        } else {
            return -1;
        }
    }

}
