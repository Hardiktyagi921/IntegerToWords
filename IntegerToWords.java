import java.util.Scanner;

public class IntegerToWords {

    private static final String[] belowTwenty = {
            "", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen",
            "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] tens = {
            "", "", "Twenty", "Thirty", "Forty", "Fifty",
            "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private static final String[] thousands = {
            "", "Thousand", "Million", "Billion"
    };

    public static String numberToWords(int num) {
        if (num == 0)
            return "Zero";

        if (num < 0)
            return "Negative " + numberToWords(-num);

        StringBuilder result = new StringBuilder();
        int i = 0;

        while (num > 0) {
            if (num % 1000 != 0) {
                String part = helper(num % 1000);
                if (!part.isEmpty()) {
                    if (result.length() == 0)
                        result.insert(0, part + (thousands[i].isEmpty() ? "" : " " + thousands[i]));
                    else
                        result.insert(0, part + (thousands[i].isEmpty() ? "" : " " + thousands[i]) + " ");
                }
            }
            num /= 1000;
            i++;
        }

        return result.toString().trim();
    }

    private static String helper(int num) {
        if (num == 0)
            return "";
        else if (num < 20)
            return belowTwenty[num];
        else if (num < 100)
            return tens[num / 10] + (num % 10 != 0 ? " " + helper(num % 10) : "");
        else
            return belowTwenty[num / 100] + " Hundred" +
                    (num % 100 != 0 ? " " + helper(num % 100) : "");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        int num = sc.nextInt();

        System.out.println(numberToWords(num));

        sc.close();
    }
}