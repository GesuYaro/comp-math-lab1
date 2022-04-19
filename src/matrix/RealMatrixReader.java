package matrix;

import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class RealMatrixReader implements MatrixReader<Double> {

    private final String printMessage = "Введите элемент %s[%d][%d]:\n";
    private final String errorMessage = "Некорректно\n";

    private Scanner reader;
    private Writer writer;

    public RealMatrixReader(Scanner reader) {
        this.reader = reader;
    }

    public RealMatrixReader(Scanner reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void readMatrix(Matrix<Double> matrix) throws IOException {
        double element = 0;
        for (int row = 0; row < matrix.rowsCount(); row++) {
            for (int column = 0; column < matrix.columnsCount(); column++) {
                printMessage(row, column, matrix.getName());
                while (true) {
                    if (reader.hasNext()) {
                        if (reader.hasNextDouble()) {
                            break;
                        }
                        else {
                            reader.next();
                            printError();
                            printMessage(row, column, matrix.getName());
                        }
                    }
                }
                element = reader.nextDouble();
                matrix.setNode(row, column, element);
            }
        }

    }

    private void printMessage(int row, int col, String matrixName) throws IOException {
        if (writer != null) {
            String message = String.format(printMessage, matrixName, row, col);
            writer.write(message);
            writer.flush();
        }
    }

    private void printError() throws IOException {
        if (writer != null) {
            writer.write(errorMessage);
            writer.flush();
        }
    }

}
