package borakdmytro.util;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Reads data from selected {@link MenuIO#inputStream}.
 */
public class MenuIO {
    /**
     * Stream from which the data is read.
     * Can be changed by assigning the required stream.
     * By default - {@link System#in}
     */
    public static InputStream inputStream = System.in;
    public static PrintStream outputStream = System.out;

    public static void write(String text) {
        outputStream.println("\n" + text);
    }

    /**
     * reads and returns int from {@link MenuIO#inputStream}
     */
    public static int readInteger() {
        while (true) {
            String input = readString();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                write("Input int");
            }
        }
    }

    /**
     * reads and returns int within range from {@link MenuIO#inputStream}
     * @param minValue lower limit including
     * @param maxValue upper limit including
     * @return int value within range
     */
    public static int readInteger(int minValue, int maxValue) {
        int input = readInteger();
        while (input < minValue || input > maxValue) {
            write(String.format("Enter a number within the valid range %d - %d", minValue, maxValue));
            input = readInteger();
        }
        return input;
    }

    /**
     * reads and returns long from {@link MenuIO#inputStream}
     */
    public static long readLong() {
        while (true) {
            String input = readString();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                write("Input long");
            }
        }
    }

    /**
     * reads and returns long within range from {@link MenuIO#inputStream}
     * @param minValue lower limit including
     * @param maxValue upper limit not including
     * @return long value within range
     */
    public static long readLong(long minValue, long maxValue) {
        long input = readLong();
        while (input < minValue || input >= maxValue) {
            write(String.format("Enter a number within the valid range %d - %d", minValue, maxValue));
            input = readLong();
        }
        return input;
    }

    /**
     * reads and returns double from {@link MenuIO#inputStream}
     */
    public static double readDouble() {
        while (true) {
            String input = readString();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                write("Input double");
            }
        }
    }

    /**
     * reads and returns double within range from {@link MenuIO#inputStream}
     * @param minValue lower limit including
     * @param maxValue upper limit not including
     * @return double value within range
     */
    public static double readDouble(double minValue, double maxValue) {
        double input = readDouble();
        while (input < minValue || input >= maxValue) {
            write(String.format("Enter a number within the valid range %f - %f", minValue, maxValue));
            input = readDouble();
        }
        return input;
    }

    /**
     * reads and returns {@link String} from {@link MenuIO#inputStream}
     */
    public static String readString() {
        Scanner scanner = new Scanner(inputStream);
        System.out.print(">>> ");
        return scanner.nextLine();
    }
}