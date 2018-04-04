import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Mukul Dhariwal (Murgan)
 */
public class EdxSolution {
	private static long[] divisors;
	static long max = 0;

	public static void main(String args[]) {
		InputReader inputReader = new InputReader("input.txt", true, true);
		OutputWriter outputWriter = new OutputWriter("output.txt");
		String line = inputReader.readLineUsingBufferedReader();
		long k = Long.parseLong(line);
		divisors = new long[(int) k + 1];
		createDivisorsArray(k);
		outputWriter.writeSingleLine(String.valueOf(findMaxAndNumberOfTimesMaxOccurs(k)));
		outputWriter.close();
	}

	private static int findMaxAndNumberOfTimesMaxOccurs(long k) {
		int count = 0;
		for (int i = 1; i <= k; i++) {
			if (divisors[i] == max) {
				count++;
			}
		}
		return count;
	}

	private static void createDivisorsArray(long k) {
		for (int i = 1; i <= k; i++) {
			divisors[i] = countDivisors(i);
			if (divisors[i] > max) {
				max = divisors[i];
			} else {
				divisors[i] = max;
			}
		}
	}

	static int countDivisors(int n) {
		int cnt = 0;
		for (int i = 1; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				if (n / i == i)
					cnt++;

				else // Otherwise count both
					cnt = cnt + 2;
			}
		}
		return cnt;
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