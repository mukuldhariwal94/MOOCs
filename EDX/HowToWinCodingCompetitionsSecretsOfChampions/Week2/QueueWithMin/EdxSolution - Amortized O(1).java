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
				queue.dequeue();
			}
		}
		edxIO.print(output);
		edxIO.close();
		double end = System.currentTimeMillis();
		// print time taken
		System.out.println((end - start) / 1000);
	}
}

class CustomStack {
	int[] stack = new int[2];
	int[] stackWithMin = new int[2];
	int number = 2;
	int top = -1;
	int min = Integer.MAX_VALUE;

	public void push(int element) {

		if (top == (stack.length - 1)) {
			int[] newStack = new int[(int) Math.pow(2, number++)];
			copyElements(newStack, false);
			stack = newStack;
			int[] newStackWithMin = new int[(int) Math.pow(2, number++)];
			copyElements(newStackWithMin, true);
			stackWithMin = newStackWithMin;
		}

		if (element < min) {
			min = element;
		}
		top++;
		stack[top] = element;
		stackWithMin[top] = min;
	}

	private void copyElements(int[] newStack, boolean minStack) {
		int[] stackToBeCopied = stackWithMin;
		if (!minStack) {
			stackToBeCopied = stack;
		}
		for (int i = 0; i < stackToBeCopied.length; i++) {
			newStack[i] = stackToBeCopied[i];
		}
	}

	public int getMin() {
		return stackWithMin[top];
	}

	public int pop() {
		int element = stack[top];
		if (element == min) {
			if (top == 0) {
				min = Integer.MAX_VALUE;
			} else {
				min = stackWithMin[top - 1];
			}
		}
		top--;
		return element;
	}

	public boolean isEmpty() {
		return top == -1 ? true : false;
	}
}

class CustomQueue {
	CustomStack customStack1 = new CustomStack();
	CustomStack customStack2 = new CustomStack();

	public void push(int element) {

		customStack2.push(element);
	}

	public int dequeue() {
		if (customStack1.isEmpty()) {
			while (!customStack2.isEmpty()) {
				customStack1.push(customStack2.pop());
			}
		}

		return customStack1.pop();
	}

	public int getMin() {
		int firstMin = Integer.MAX_VALUE;
		if (!customStack1.isEmpty()) {
			firstMin = customStack1.getMin();
		}
		int secondMin = Integer.MAX_VALUE;
		if (!customStack2.isEmpty()) {
			secondMin = customStack2.getMin();
		}

		if (firstMin < secondMin) {
			return firstMin;
		}

		return secondMin;
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