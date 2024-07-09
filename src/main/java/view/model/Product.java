package view.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * FUTURE ENHANCEMENT: Adding more variables about the products.
 * This is a public abstract class called Product.

 */

public class Product {
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
     * This is a list of all the associated Parts.
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * @param id for the ID
     * @param name for the Name.
     * @param price for the Price
     * @param stock for the Inventory(stock).
     * @param min for the Minimum.
     * @param max for the Maximum.
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
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

    /**
     * This adds any associated part.
     * @param part addAssociatedPart
     */
    public void addAssociatedPart(Part part)
    {
        for (Part p : Inventory.getAllParts())
        {
            if (part.getId() == p.getId())
            {
                associatedParts.add(p);
            }
        }
    }

    /**
     * This deletes the associated part.
     * @param selectedAssociatedPart  Part
     * @return deleteAssociatedPart
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart)
    {
        for (Part part: getAllAssociatedParts())
        {
            if (part.getId() == selectedAssociatedPart.getId())
            {
                return getAllAssociatedParts().remove(part);
            }
        }
        return false;
    }

    /**
     * This provides a list of the associatedParts
     * @return associatedParts
     */
    public ObservableList<Part> getAllAssociatedParts()
    {
        return associatedParts;
    }

    /**
     * This saves the parts from the list.
     * @param assocParts associatedPartS
     * ERROR RUNTIME: Again, this shows no usage, but I decided to keep it, regardless.
     */
    public void saveParts(ObservableList<Part> assocParts)
    {
        associatedParts = assocParts;
    }

}
