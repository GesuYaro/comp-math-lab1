package main;

import iterations.Result;
import iterations.gaussseidel.GaussSeidelMethod;
import matrix.*;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static int rows;
    private static double eps;
    private static Scanner scanner;
    private static BufferedWriter writer;

    public static void main(String[] args) {
        scanner = new Scanner(System.in).useLocale(Locale.US);
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            read_options();
        } catch (FileNotFoundException e) {
            System.out.println("Проблемы с доступом к файлу, ввод осуществляется через консоль");
        }

        read_sizes();
        Matrix<Double> matrixA = new RealMatrix(rows, rows, "A");
        Matrix<Double> matrixB = new RealMatrix(rows, 1, "B");
        MatrixReader<Double> reader = new RealMatrixReader(scanner, writer);
        BufferedWriter printerWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        MatrixPrinter<Double> printer = new RealMatrixPrinter(printerWriter);
        GaussSeidelMethod method = new GaussSeidelMethod();
        try {
            reader.readMatrix(matrixA);
            printer.printMatrix(matrixA);
            reader.readMatrix(matrixB);
            printer.printMatrix(matrixB);
            if (method.isDiagonallyDominant(matrixA) || method.toDiagonallyDominant(matrixA)) {
                Result<Double> answers =  method.solve(matrixA, matrixB, eps);
                System.out.println("\n=============== ОТВЕТ ===============");
                printer.printMatrix(answers.getMatrix());
                System.out.println("Количество итераций: " + answers.getIterations());
            } else {
                System.out.println("Матрица не удовлетворяет условию диагонального преобладания");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void read_sizes() {
        System.out.println("Введите размерность матрицы:");
        while (true) {
            if (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    break;
                }
                else {
                    scanner.next();
                    System.out.println("Некорректно");
                    System.out.println("Введите размерность матрицы:");
                }
            }
        }
        rows = scanner.nextInt();
        System.out.println("Введите точность вычислений:");
        while (true) {
            if (scanner.hasNext()) {
                if (scanner.hasNextDouble()) {
                    break;
                }
                else {
                    scanner.next();
                    System.out.println("Некорректно");
                    System.out.println("Введите точность вычислений:");
                }
            }
        }
        eps = scanner.nextDouble();
    }

    private static void read_options() throws FileNotFoundException {
        System.out.println("Хотите считать данные из файла y/n?");
        if (scanner.hasNext()) {
            String answer = scanner.next();
            if (answer.equals("y")) {
                System.out.println("Введите название файла:");
                if (scanner.hasNext()) {
                    String filename = scanner.next();
                    scanner = new Scanner(new File(filename)).useLocale(Locale.US);
                    writer = null;
                }
            }
        }
    }
}
