public class CreditCardAuthentication {

    //Method to check valid credit card numbers
    public static boolean creditCardAuthentication(String card_number) {

        String card_no_reversed = "";
        int total = 0;

        // Reverse the card number
        for (int i = card_number.length() - 1; i >= 0; i--) {
            card_no_reversed = card_no_reversed + card_number.charAt(i);
        }

        // Iterate over the reversed card number
        for (int k = 0; k < card_no_reversed.length(); k++) {
            int n = Character.getNumericValue(card_no_reversed.charAt(k)); // Get numeric value of the character

            // Double every second digit from the right (odd index in the reversed string)
            if (k % 2 != 0) {
                n = n * 2;
                // If doubling results in a number greater than 9, subtract 9
                if (n > 9) {
                    n -= 9;
                }
            }

            // Add the value to the total sum
            total = total + n;
        }

        // If total modulo 10 is 0, the card number is valid
        return total % 10 == 0;
    }
}
