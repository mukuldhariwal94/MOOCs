import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Mukul Dhariwal (Murgan)
 */
public class EdxSolution {
	private static int[] divisors;
	static int max = 0;
	static OutputWriter outputWriter;

	public static void main(String args[]) {
		double start = System.currentTimeMillis();

		InputReader inputReader = new InputReader("input.txt", true, true);
		OutputWriter outputWriter = new OutputWriter("output.txt");
		int n = Integer.parseInt(inputReader.readLineUsingBufferedReader());
		CustomStack stack = new CustomStack();
		for (int i = 0; i < n; i++) {
			String operation = inputReader.readLineUsingBufferedReader();
			String[] operations = operation.split(" ");
			if (operations[0].equals("+")) {
				stack.push(Integer.parseInt(operations[1]));
			} else {
				outputWriter.writeSingleLine(String.valueOf(stack.pop()));
				outputWriter.writeSingleLine("\n");
			}
		}
		outputWriter.close();

		double end = System.currentTimeMillis();
		// print time taken
		System.out.println((end - start) / 1000);
	}
}

class CustomStack {
	int[] stack = new int[2];
	int number = 2;
	int top = -1;

	public void push(int element) {

		if (top == (stack.length - 1)) {
			int[] newStack = new int[(int) Math.pow(2, number++)];
			copyElements(newStack);
			stack = newStack;
		}
		stack[++top] = element;
	}

	private void copyElements(int[] newStack) {
		for (int i = 0; i < stack.length; i++) {
			newStack[i] = stack[i];
		}
	}

	public int pop() {
		return stack[top--];
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