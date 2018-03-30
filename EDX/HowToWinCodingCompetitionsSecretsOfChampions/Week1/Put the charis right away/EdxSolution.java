import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Mukul Dhariwal (Murgan)
 */
public class EdxSolution {
	public static void main(String args[]) {
		InputReader inputReader = new InputReader("input.txt", true);
		OutputWriter outputWriter = new OutputWriter("output.txt");

		// sides a , b , c
		double[] lengths = new double[3];

		for (int i = 0; i < 3; i++) {
			lengths[i] = inputReader.readDouble();
		}

		double[] firstCoordinates = new double[2];
		firstCoordinates[0] = 0;
		firstCoordinates[1] = 0;

		double[] secondCoordinates = new double[2];

		// lets take any one side as the length of and put that as x coordinates side c
		// is taken

		secondCoordinates[0] = lengths[2];
		secondCoordinates[1] = 0;

		double[] thirdCoordinates = new double[2];
		thirdCoordinates[0] = getThirdCoordinatesX(lengths);
		thirdCoordinates[1] = getThirdCoordinatesY(lengths[1], thirdCoordinates[0]);

		// convert to midpoints

		Point pointA = convertToMidPoints(firstCoordinates, secondCoordinates);
		Point pointB = convertToMidPoints(secondCoordinates, thirdCoordinates);
		Point pointC = convertToMidPoints(firstCoordinates, thirdCoordinates);
		Triangle triangle = new Triangle(pointA, pointB, pointC);
		// first to second
		double innerTriangleSideA = getDistanceUsingDistanceFormula(triangle.getPointA(), triangle.getPointB());
		// second to third
		double innerTriangleSideB = getDistanceUsingDistanceFormula(triangle.getPointA(), triangle.getPointC());
		// first to third
		double innerTriangleSideC = getDistanceUsingDistanceFormula(triangle.getPointB(), triangle.getPointC());
		double average = (innerTriangleSideA + innerTriangleSideB + innerTriangleSideC) / 3;

		outputWriter.writeSingleLine(String.valueOf(average));
		outputWriter.close();
	}

	private static Point convertToMidPoints(double[] firstCoordinates, double[] secondCoordinates) {
		double x = (firstCoordinates[0] + secondCoordinates[0]) / 2;
		double y = (firstCoordinates[1] + secondCoordinates[1]) / 2;

		return new Point(x, y);
	}

	private static double getDistanceUsingDistanceFormula(Point point1, Point point2) {
		double length = Math.pow((point1.getX() - point2.getX()), 2) + Math.pow((point1.getY() - point2.getY()), 2);
		length = Math.sqrt(length);
		return length;
	}

	private static double getThirdCoordinatesY(double sideB, double thirdCoordinateMidpointX) {
		double coordinate = Math.pow(sideB, 2) - Math.pow(thirdCoordinateMidpointX, 2);
		coordinate = Math.sqrt(coordinate);
		return coordinate;
	}

	private static double getThirdCoordinatesX(double[] lengths) {
		double coordinate = Math.pow(lengths[0], 2) - Math.pow(lengths[1], 2) - Math.pow(lengths[2], 2);
		coordinate = coordinate / (2 * lengths[2]);
		coordinate = coordinate * -1;
		return coordinate;
	}

}

class Point {
	double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}

class Triangle {
	Point pointA;
	Point pointB;
	Point pointC;

	public Triangle(Point pointA, Point pointB, Point pointC) {
		this.pointA = pointA;
		this.pointB = pointB;
		this.pointC = pointC;
	}

	Point getPointA() {
		return pointA;
	}

	Point getPointB() {
		return pointB;
	}

	Point getPointC() {
		return pointC;
	}
}

class InputReader {
	Scanner scanner;
	File inputFile;

	public InputReader(String inputFileName, boolean createFileReader) {
		inputFile = new File(inputFileName);
		if (createFileReader) {
			try {
				scanner = new Scanner(inputFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			scanner = new Scanner(System.in);
		}
	}

	public int readInt() {
		return scanner.nextInt();
	}

	public double readDouble() {
		return scanner.nextDouble();
	}

	public long readLong() {
		return scanner.nextLong();
	}

	public boolean hasNextValue() {
		return scanner.hasNext();
	}
}

class OutputWriter {
	BufferedWriter bufferedWriter;
	File outputFile;
	FileWriter fileWriter;

	public OutputWriter(String outputFileName) {
		outputFile = new File(outputFileName);
		try {
			fileWriter = new FileWriter(outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bufferedWriter = new BufferedWriter(fileWriter);
	}

	public void writeSingleLine(String line) {

		try {
			bufferedWriter.write(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}