package borakdmytro.util;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Reads data from selected {@link MenuInputReader#inputStream}.
 */
public class MenuInputReader {
    /**
     * Stream from which the data is read.
     * Can be changed by assigning the required stream.
     * By default - {@link System#in}
     */
    public static InputStream inputStream = System.in;

    /**
     * reads and returns int from {@link MenuInputReader#inputStream}
     */
    public static int readInteger() {
        while (true) {
            String input = readString();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\n Input int");
            }
        }
    }

    /**
     * reads and returns int within range from {@link MenuInputReader#inputStream}
     * @param minValue lower limit including
     * @param maxValue upper limit including
     * @return int value within range
     */
    public static int readInteger(int minValue, int maxValue) {
        int input = readInteger();
        while (input < minValue || input > maxValue) {
            System.out.printf("\n Enter a number within the valid range %d - %d\n", minValue, maxValue);
            input = readInteger();
        }
        return input;
    }

    /**
     * reads and returns long from {@link MenuInputReader#inputStream}
     */
    public static long readLong() {
        while (true) {
            String input = readString();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("\n Input long");
            }
        }
    }

    /**
     * reads and returns long within range from {@link MenuInputReader#inputStream}
     * @param minValue lower limit including
     * @param maxValue upper limit not including
     * @return long value within range
     */
    public static long readLong(long minValue, long maxValue) {
        long input = readLong();
        while (input < minValue || input >= maxValue) {
            System.out.printf("\n Enter a number within the valid range %d - %d\n", minValue, maxValue);
            input = readLong();
        }
        return input;
    }

    /**
     * reads and returns double from {@link MenuInputReader#inputStream}
     */
    public static double readDouble() {
        while (true) {
            String input = readString();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("\n Input double");
            }
        }
    }

    /**
     * reads and returns double within range from {@link MenuInputReader#inputStream}
     * @param minValue lower limit including
     * @param maxValue upper limit not including
     * @return double value within range
     */
    public static double readDouble(double minValue, double maxValue) {
        double input = readDouble();
        while (input < minValue || input >= maxValue) {
            System.out.printf("\n Enter a number within the valid range %f - %f\n", minValue, maxValue);
            input = readDouble();
        }
        return input;
    }

    /**
     * reads and returns {@link String} from {@link MenuInputReader#inputStream}
     */
    public static String readString() {
        Scanner scanner = new Scanner(inputStream);
        System.out.print(">>> ");
        return scanner.nextLine();
    }
}