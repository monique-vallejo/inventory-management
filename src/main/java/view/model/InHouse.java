package view.model;

    /**
        *FUTURE ENHANCEMENT: More variables than the ones provided.
        * This class is a public class called InHouse, it provides the model.

     */
public class InHouse extends Part{

    /**
         *  A private int for the machine ID for Part.
     */
    private int machineId;

        /**
             * The following are variables for the InHouse.
             * @param id for the ID.
             * @param name for the Name.
             * @param price for the Price per unit.
             * @param stock for the inventory(stock).
             * @param min for the minimum
             * @param max for the maximum.
             * @param machineId for the machine ID.
         */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId)
    {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

        /**
             * The getter for the machine ID.
             * @return machineId for Part.
         */
    public int getMachineId()
    {
        return machineId;
    }

        /**
             * The setter for the machine ID.
             * @param machineId for Part.
             *ERROR RUNTIME: The setter doesn't have any usages, but I kept it there for security reasons.
         */
    public void setMachineId(int machineId)
    {
        this.machineId = machineId;
    }
}
