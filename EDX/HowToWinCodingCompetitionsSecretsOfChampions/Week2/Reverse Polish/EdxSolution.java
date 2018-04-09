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

import mooc.EdxIO;

/*
 * Mukul Dhariwal (Murgan)
 */
public class EdxSolution {
	static int max = 0;
	static OutputWriter outputWriter;
	static CustomStack stack = new CustomStack();

	public static void main(String args[]) {
		double start = System.currentTimeMillis();
		StringBuilder output = new StringBuilder();
		EdxIO edxIO = EdxIO.create();
		int n = Integer.parseInt(edxIO.next());
		for (int i = 0; i < n; i++) {
			Character c = edxIO.nextChar();
			if (Character.isDigit(c)) {
				stack.push(String.valueOf(c));
			} else {
				int firstNum = Integer.parseInt(stack.pop());
				int secondNum = Integer.parseInt(stack.pop());
				int result = 0;
				switch (c) {
				case '+':
					result = secondNum + firstNum;
					stack.push(String.valueOf(result));
					break;
				case '-':
					result = secondNum - firstNum;
					stack.push(String.valueOf(result));
					break;
				case '*':
					result = secondNum * firstNum;
					stack.push(String.valueOf(result));
					break;
				case '/':
					result = secondNum / firstNum;
					stack.push(String.valueOf(result));
					break;
				}
			}
		}
		output.append(stack.pop());
		edxIO.print(output);
		edxIO.close();
		double end = System.currentTimeMillis();
		// print time taken
		System.out.println((end - start) / 1000);
	}
}

class CustomStack {
	String[] stack = new String[1000000];
	int top = -1;

	public void push(String element) {
		top++;
		stack[top] = element;
	}

	public void clear() {
		top = -1;
		stack = new String[10000];
	}

	public String pop() {
		if (top == -1) {
			return "X";
		}
		return stack[top--];
	}

	public String peek() {
		if (top == -1) {
			return "X";
		}
		return stack[top];
	}

	public int getTop() {
		return top;
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
