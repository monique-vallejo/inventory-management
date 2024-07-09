package view.model;

    /**
     * FUTURE ENHANCEMENT: Besides the company name, maybe it includes the region of origin or manufacture.
     * This class is a public class called Outsourced, it models a outsourced part.

     */
public class Outsourced extends Part
{
    /**
     * A private string that is used for the company name.
     */
    private String companyName;

    /**
     * The following are all the variables.
     * @param id for the ID
     * @param name for the Name.
     * @param price for the Price
     * @param stock for the Inventory(stock).
     * @param min for the Minimum.
     * @param max for the Maximum.
     * @param companyName for the Company Name.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName)
    {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * The getter for the company name.
     * @return getCompanyName
     */
    public String getCompanyName()
    {
        return companyName;
    }

    /**
     * The setter for the company name.
     * @param companyName setCompanyName
     * RUNTIME ERRROR: The setter displays no usage, however I kept it.
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
}
