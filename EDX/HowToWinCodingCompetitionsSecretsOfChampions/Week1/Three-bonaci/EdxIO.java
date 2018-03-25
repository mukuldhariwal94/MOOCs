import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Mukul Dhariwal (Murgan)
 */
public class EdxIO {
	public static void main(String args[]) {
		InputReader inputReader = new InputReader("input.txt", true);
		OutputWriter outputWriter = new OutputWriter("output.txt");
		long A0 = inputReader.readInt();
		long A1 = inputReader.readInt();
		long A2 = inputReader.readInt();
		int n = inputReader.readInt();
		long[] series = new long[100001];
		series[0] = A0;
		series[1] = A1;
		series[2] = A2;
		for (int i = 3; i <= n; i++) {
			series[i] = series[i - 1] + series[i - 2] - series[i - 3];
		}

		long nthFib = series[n];
		outputWriter.writeSingleLine(String.valueOf(nthFib));
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