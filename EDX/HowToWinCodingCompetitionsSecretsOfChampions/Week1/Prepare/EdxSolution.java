import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Mukul Dhariwal (Murgan)
 */
public class EdxSolution {
	public static void main(String args[]) {
		InputReader inputReader = new InputReader("input.txt", true);
		OutputWriter outputWriter = new OutputWriter("output.txt");
		int n = inputReader.readInt();
		int[] theory = new int[n];
		int[] practical = new int[n];

		for (int i = 0; i < practical.length; i++) {
			practical[i] = inputReader.readInt();
		}

		for (int i = 0; i < theory.length; i++) {
			theory[i] = inputReader.readInt();
		}

		int sum = 0;
		boolean elementPickedFromPractical = false, elementPickedFromTheory = false;
		for (int i = 0; i < practical.length; i++) {
			int greaterValue = 0;

			if (practical[i] > theory[i]) {
				elementPickedFromPractical = true;
				greaterValue = practical[i];
			} else {
				elementPickedFromTheory = true;
				greaterValue = theory[i];
			}

			sum += greaterValue;
		}

		if (!elementPickedFromPractical) {
			Integer[] minValueIndexs = finMinValueIndexs(theory);
			int indexToRemove = findIndex(minValueIndexs, practical);
			sum = sum - theory[indexToRemove] + practical[indexToRemove];
		}

		if (!elementPickedFromTheory) {
			Integer[] minValueIndexs = finMinValueIndexs(practical);
			int indexToRemove = findIndex(minValueIndexs, theory);
			sum = sum - practical[indexToRemove] + theory[indexToRemove];
		}

		outputWriter.writeSingleLine(String.valueOf(sum));
		outputWriter.close();
	}

	private static int findIndex(Integer[] minValueIndexs, int[] array) {
		int valueToRemove = array[minValueIndexs[0]];
		int indexToRemove = minValueIndexs[0];
		for (int i = 0; i < minValueIndexs.length; i++) {
			if (array[minValueIndexs[i]] < valueToRemove) {
				indexToRemove = minValueIndexs[i];
			}
		}
		return indexToRemove;
	}

	private static Integer[] finMinValueIndexs(int[] array) {
		int minValue = 100000;
		for (int i = 0; i < array.length; i++) {
			if (array[i] < minValue) {
				minValue = array[i];
			}
		}
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			if (array[i] == minValue) {
				list.add(i);
			}
		}
		Integer[] minValueIndexs = new Integer[list.size()];
		list.toArray(minValueIndexs);
		return minValueIndexs;
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