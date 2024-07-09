package view.model;

    /**
     * FUTURE ENHANCEMENT: Attempt to simplify the code, or include stricter invalids so errors don't occur.
     * This is a public class that ties in with the error messages, specifically the min, max, and stock.

     */
public class INVALID extends Exception {
        /**
             * The following are all private integers -- three for stock, minimum, and maximum.
         */

        /**
         * Inventory(stock)
         */
    private int inv;
        /**
         * Minimum
         */
    private int min;
        /**
         * Maximum.
         */
    private int max;

        /**
         * @param inv for INVALID
         * @param min for INVALID
         * @param max for INVALID
         */
    public INVALID(int inv, int min, int max) {
        this.inv = inv;
        this.min = min;
        this.max = max;
    }

        /**
         * Getters for each of the following integer.
         * @return inv
         */
    public int getInv() {
        return inv;
    }
        /**
         * Getters for each of the following integer.
         * @return min
         */
    public int getMin() {
        return min;
    }
        /**
         * Getters for each of the following integer.
         * @return max
         */
    public int getMax() {
        return max;
    }


        /**
         * This is the errorr messages for the data is deemed as invalid.
         * @return errorMessage
         * ERROR RUNTIME: I attempted to place these into cases, but failed so settled with if else statements.
         */
    public String errorMessage() {
        if (min > max) {
            return "Min amount cannot be greater than max amount! Try again!";
        } else if (max < min) {
            return "Max amount cannot be less than min amount! Try again!";
        } else if (inv > max || inv < min) {
            return "Inventory amount must be in between min and max value! Try again!";
        } else {
            return "Invalid!";
        }
    }
}

