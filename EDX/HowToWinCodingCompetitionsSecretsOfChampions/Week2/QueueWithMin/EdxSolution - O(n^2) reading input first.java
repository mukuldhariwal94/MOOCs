import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import mooc.EdxIO;

/*
 * Mukul Dhariwal (Murgan)
 */
public class EdxSolution {

	public static void main(String args[]) {
		double start = System.currentTimeMillis();
		StringBuilder output = new StringBuilder();
		EdxIO edxIO = EdxIO.create();
		int n = edxIO.nextInt();
		int[] numbers = new int[1000000];
		char[] operations = new char[1000000];
		CustomQueue queue = new CustomQueue();
		int characterOperations = 0;
		int numbersCounter = 0;
		char c;
		for (int i = 0; i < n; i++) {
			c = edxIO.nextChar();
			if (c == '+') {
				numbers[numbersCounter++] = edxIO.nextInt();
			}
			operations[characterOperations++] = c;
		}
		int j = 0;
		for (int i = 0; i < characterOperations; i++) {
			c = operations[i];
			if (c == '+') {
				queue.push(numbers[j++]);
			} else if (c == '?') {
				output.append(queue.getMin()).append("\n");
			} else {
				queue.pop();
			}
		}
		edxIO.print(output);
		edxIO.close();
		double end = System.currentTimeMillis();
		// print time taken
		System.out.println((end - start) / 1000);
	}
}

class CustomQueue {
	int[] queue = new int[1000000];
	int head = 0, tail = 0;
	int min = Integer.MAX_VALUE;

	public void push(int element) {

		if (element < min) {
			min = element;
		}

		queue[tail++] = element;
	}

	public int pop() {
		int elementToBeRemoved = queue[head];
		head++;
		if (min == elementToBeRemoved) {
			min = findMin(queue);
		}
		return elementToBeRemoved;
	}

	public int getMin() {
		return min;
	}

	private int findMin(int[] queue) {
		int min = Integer.MAX_VALUE;
		for (int i = head; i < tail; i++) {
			if ((queue[i] < min)) {
				min = queue[i];
			}
		}
		return min;
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