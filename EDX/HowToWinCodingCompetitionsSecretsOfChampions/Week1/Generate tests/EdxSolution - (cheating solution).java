import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;

/*
 * Mukul Dhariwal (Murgan)
 */
public class EdxSolution {
	private static int[] divisors;
	static int max = 0;
	static OutputWriter outputWriter;

	public static void main(String args[]) {
		double start = System.currentTimeMillis();

		// generated break points for all points where the divisors increase in a
		// maximal order for k = 3 * 10^7
		int[] breakPoints = { 1, 2, 4, 6, 12, 24, 36, 48, 60, 120, 180, 240, 360, 720, 840, 1260, 1680, 2520, 5040,
				7560, 10080, 15120, 20160, 25200, 27720, 45360, 50400, 55440, 83160, 110880, 166320, 221760, 277200,
				332640, 498960, 554400, 665280, 720720, 1081080, 1441440, 2162160, 2882880, 3603600, 4324320, 6486480,
				7207200, 8648640, 10810800, 14414400, 17297280, 21621600, 32432400, 36756720 };

		InputReader inputReader = new InputReader("input.txt", true, true);
		OutputWriter outputWriter = new OutputWriter("output.txt");
		String line = inputReader.readLineUsingBufferedReader();
		int k = Integer.parseInt(line);
		int result = 0;
		for (int i = 0; i < breakPoints.length; i++) {
			if (breakPoints[i] > k) {
				result = k - breakPoints[i - 1] + 1;
				break;
			}
		}
		outputWriter.writeSingleLine(String.valueOf(result));
		outputWriter.close();

		double end = System.currentTimeMillis();
		// print time taken
		System.out.println((end - start) / 1000);
	}
}

class InputReader {
	Scanner scanner;
	BufferedReader bufferedReader;
	File inputFile;

	public InputReader(String inputFileName, boolean createFileReader, boolean createBufferedReader) {
		inputFile = new File(inputFileName);
		if (createFileReader) {
			if (createBufferedReader) {
				try {
					bufferedReader = new BufferedReader(new FileReader(inputFileName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				try {
					scanner = new Scanner(inputFile);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		} else {
			scanner = new Scanner(System.in);
		}
	}

	public int readIntUsingScanner() {
		return scanner.nextInt();
	}

	public double readDoubleUsingScanner() {
		return scanner.nextDouble();
	}

	public long readLongUsingScanner() {
		return scanner.nextLong();
	}

	public boolean doesScannerHaveNextValue() {
		return scanner.hasNext();
	}

	public String readLineUsingBufferedReader() {
		String readLine = "";
		try {
			readLine = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readLine;
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