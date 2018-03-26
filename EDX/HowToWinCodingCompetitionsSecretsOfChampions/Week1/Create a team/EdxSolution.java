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
		int[][] inputArray = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				inputArray[i][j] = inputReader.readInt();
			}
		}
		outputWriter.writeSingleLine(String.valueOf(getMaxEfficiency(inputArray)));
		outputWriter.close();
	}

	private static double getMaxEfficiency(int[][] inputArray) {
		double[] efficiencyValueCombinations = new double[6];
		double sumOfSqaures;

		// (0,0) (1,1) (2,2)
		sumOfSqaures = getSqauredValue(inputArray, 0, 0) + getSqauredValue(inputArray, 1, 1)
				+ getSqauredValue(inputArray, 2, 2);

		efficiencyValueCombinations[0] = Math.sqrt(sumOfSqaures);

		// (0,0) (1,2) (2,1)
		sumOfSqaures = getSqauredValue(inputArray, 0, 0) + getSqauredValue(inputArray, 1, 2)
				+ getSqauredValue(inputArray, 2, 1);

		efficiencyValueCombinations[1] = Math.sqrt(sumOfSqaures);

		// (0,1) (1,0) (2,2)
		sumOfSqaures = getSqauredValue(inputArray, 0, 1) + getSqauredValue(inputArray, 1, 0)
				+ getSqauredValue(inputArray, 2, 2);

		efficiencyValueCombinations[2] = Math.sqrt(sumOfSqaures);

		// (0,1) (1,2) (2,0)
		sumOfSqaures = getSqauredValue(inputArray, 0, 1) + getSqauredValue(inputArray, 1, 2)
				+ getSqauredValue(inputArray, 2, 0);

		efficiencyValueCombinations[3] = Math.sqrt(sumOfSqaures);

		// (0,2) (1,0) (2,1)
		sumOfSqaures = getSqauredValue(inputArray, 0, 2) + getSqauredValue(inputArray, 1, 0)
				+ getSqauredValue(inputArray, 2, 1);

		efficiencyValueCombinations[4] = Math.sqrt(sumOfSqaures);

		// (0,2) (1,1) (2,0)
		sumOfSqaures = getSqauredValue(inputArray, 0, 2) + getSqauredValue(inputArray, 1, 1)
				+ getSqauredValue(inputArray, 2, 0);

		efficiencyValueCombinations[5] = Math.sqrt(sumOfSqaures);

		double max = -100;

		for (int i = 0; i < efficiencyValueCombinations.length; i++) {
			if (efficiencyValueCombinations[i] > max) {
				max = efficiencyValueCombinations[i];
			}
		}
		return max;
	}

	private static double getSqauredValue(int[][] inputArray, int i, int j) {
		return Math.pow(inputArray[i][j], 2);
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