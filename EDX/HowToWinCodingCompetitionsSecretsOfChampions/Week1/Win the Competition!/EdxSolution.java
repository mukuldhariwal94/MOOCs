import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Mukul Dhariwal (Murgan)
 */
public class EdxSolution {
	public static void main(String args[]) {
		InputReader inputReader = new InputReader("input.txt", true);
		OutputWriter outputWriter = new OutputWriter("output.txt");
		int n = inputReader.readInt();
		int[] timeTaken = new int[n];

		for (int i = 0; i < timeTaken.length; i++) {
			timeTaken[i] = inputReader.readInt();
		}
		Arrays.sort(timeTaken);
		int counter = 0;
		int sum = 0;
		for (int i = 0; i < timeTaken.length; i++) {
			sum += timeTaken[i];
			if (sum <= 18000) {
				counter++;
			}
		}
		outputWriter.writeSingleLine(String.valueOf(counter));
		outputWriter.close();
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