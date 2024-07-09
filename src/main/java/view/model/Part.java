package view.model;

import javafx.collections.ObservableList; /**
     * FUTURE ENHANCEMENT: Adding more variables to this class.
     * This is a public abstract class called Part.
     * This was also provided with the assignment.

     */
public abstract class Part {
    /**
     * The following are all the variables.
     */
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * @param id for the ID
     * @param name for the Name.
     * @param price for the Price
     * @param stock for the Inventory(stock).
     * @param min for the Minimum.
     * @param max for the Maximum.
     * RUNTIME ERROR: For sake of organization, I grouped together the setters and getters since I missed certain variables.
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    // This is a list of getters for the variables above.

        /**
         * ID getter.
         * @return id
         */
    public int getId()
    {
        return id;
    }

        /**
         * Name getter.
         * @return name
         */

    public String getName()
    {
        return name;
    }

        /**
         * Price getter.
         * @return price.
         */
    public double getPrice()
    {
        return price;
    }

        /**
         * Inventory(stock) getter.
         * @return stock
         */
    public int getStock()
    {
        return stock;
    }

        /**
         * Minimum getter.
         * @return min
         */

    public int getMin()
    {
        return min;
    }

        /**
         * Maximum getter.
         * @return max
         */
    public int getMax()
    {
        return max;
    }

        // This is a list of setters for the variables above.

        /**
         * ID setter
         * @param id set
         */
    public void setId(int id)
    {
        this.id = id;
    }

        /**
         * Name setter
         * @param name set
         */
    public void setName(String name)
    {
        this.name = name;
    }

        /**
         * Price setter
         * @param price set
         */

    public void setPrice(double price)
    {
        this.price = price;
    }

        /**
         * Inventory(stock) setter
         * @param stock set
         */
    public void setStock(int stock)
    {
        this.stock = stock;
    }

        /**
         * Minimum setter.
         * @param min set
         */
    public void setMin(int min)
    {
        this.min = min;
    }

        /**
         * Maximum Setter
         * @param max set
         */
    public void setMax(int max)
    {
        this.max = max;
    }

}

