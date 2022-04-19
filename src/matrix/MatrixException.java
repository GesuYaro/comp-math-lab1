package matrix;

public class MatrixException extends RuntimeException {

    public MatrixException(String message) {
        super(message);
    }

    public MatrixException() {
        super("Problem with matrix");
    }

}
