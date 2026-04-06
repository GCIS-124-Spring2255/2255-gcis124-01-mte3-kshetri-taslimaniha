// Exam: 2255 GCIS 124, Mid Term Exam #3, Question 1
// Filename: Converter.java (inside tempconvert package)

package mte3.tempconvert;
import java.util.Scanner;


public class Converter {

    
    private static class CelsiusToFahrenheit implements TempConvert {
        @Override
        public double convert(double temp) { return temp * 9.0 / 5.0 + 32; }
    }

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Please enter temperature: ");
            double input = scanner.nextDouble();

            // (Part 1) Convert C → F using static inner class
            TempConvert cToF = new CelsiusToFahrenheit();
            double resultCF = cToF.convert(input);
            System.out.println("C to F: " + resultCF);

            // (Part 2) Convert F → C using anonymous class
            TempConvert fToC = new TempConvert() {
                @Override
                public double convert(double temp) {
                    return (temp - 32) * 5.0 / 9.0;
                }
            };
            double resultFC = fToC.convert(input);
            System.out.println("F to C: " + resultFC);

            // (Part 3) Convert F → K using lambda
            TempConvert fToK = (temp) -> (temp - 32) * 5.0 / 9.0 + 273.15;
            double resultFK = fToK.convert(input);
            System.out.println("F to K: " + resultFK);

        }
    }
}
