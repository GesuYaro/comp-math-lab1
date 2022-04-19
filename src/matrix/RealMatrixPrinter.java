package matrix;

import java.io.IOException;
import java.io.Writer;

public class RealMatrixPrinter implements MatrixPrinter<Double> {

    private Writer writer;

    public RealMatrixPrinter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void printMatrix(Matrix<Double> matrix) throws IOException {
        int formatLength = findLongestNumber(matrix);
        for (int row = 0; row < matrix.rowsCount(); row++) {
            for (int column = 0; column < matrix.columnsCount(); column++) {
                String strVal = String.valueOf(matrix.getNode(row, column));
                strVal = " ".repeat(formatLength - strVal.length() + 1) + strVal;
                writer.write(strVal);
            }
            writer.write("\n");
        }
        writer.flush();
    }

    private int findLongestNumber(Matrix<Double> matrix) {
        int longest = 1;
        for (int row = 0; row < matrix.rowsCount(); row++) {
            for (int column = 0; column < matrix.columnsCount(); column++) {
                int strValLen = String.valueOf(matrix.getNode(row, column)).length();
                if (strValLen > longest) {
                    longest = strValLen;
                }
            }
        }
        return longest;
    }

}
