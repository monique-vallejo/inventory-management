package view.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
     * FUTURE ENHANCEMENT: A future enhancement could be a reminder in the application to make sure all the inventory is accurate.
     * This is a public class called Inventory that will provide the addition, search, and deletion of parts.

     */
public class Inventory {
        /**
         * The following ObservableLists are categorized into four. The first two include all parts and products.
         * The last two include a filtered list of all the parts and products.
         */
        private static ObservableList<Part> allParts = FXCollections.observableArrayList();
        private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

        private static int partIDCount = 0;
        private static int productIDCount = 0;

        // All the voids for the parts.

        /**
         * This adds the part.
         *
         * @param part from Part
         */
        public static void addPart(Part part) {

            allParts.add(part);
        }

    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }
        /**
         * This updates the part selected.
         *
         * @param index        int
         * @param part Part
         */
        public static void updatePart(int index, Part part) {
            allParts.set(index,part);
        }

        public static int getPartIDCount() {
            partIDCount++;
                    return partIDCount;
        }

        /**
         * This looks up the part based on their ID.
         *
         * @param partId int
         * @return part
         */
        public static Part lookupPart(int partId) {
            for (Part part : Inventory.getAllParts()) {
                if (part.getId() == partId) {
                    return part;
                }
            }
            return null;
        }

        // All the voids for the products.

        /**
         * This adds the product.
         *
         * @param product from Product
         */
        public static void addProduct(Product product) {
            allProducts.add(product);
        }

        /**
         * This looks up the product based on their ID.
         *
         * @param productId int
         * @return part
         */
        public static Product lookupProduct(int productId) {
            for (Product product : Inventory.getAllProducts()) {
                if (productId == product.getId()) {
                    return product;
                }
            }
            return null;
        }

        /**
         * This returns a filtered list based on the part's name.
         *
         * @param partName String
         * @return Name
         */

        public static ObservableList<Part> lookupPart(String partName) {
            ObservableList<Part> PTName = FXCollections.observableArrayList();

            for (Part part: allParts) {
                if (part.getName().contains(partName)) {
                    PTName.add(part);
                }
            }
            return PTName;
        }

        /**
         * This returns a filtered list based on the product's name.
         *
         * @param productName String
         * @return Name
         */
        public static ObservableList<Product> lookupProduct(String productName) {
            ObservableList<Product> ProductName = FXCollections.observableArrayList();

            for (Product product: allProducts) {
                if (product.getName().contains(productName)) {
                    ProductName.add(product);
                }
            }
            return ProductName;
        }




        /**
         * This updates the product selected.
         *
         * @param index      int
         * @param newProduct Product
         */
        public static void updateProduct(int index, Product newProduct) {
            int i = -1;
            for (Product product : Inventory.getAllProducts()) {
                i++;
                if (product.getId() == newProduct.getId()) {
                    Inventory.getAllProducts().set(i, newProduct);
                }
            }
        }

        /**
         * This deletes the part selected.
         *
         * @param selectedPart Part
         * @return deletePart
         */

        /**
         * This deletes the product selected.
         *
         * @param selectedProduct Product
         * @return deleteProduct
         */
        public static boolean deleteProduct(Product selectedProduct) {
            for (Product product : Inventory.getAllProducts()) {
                if (product.getId() == selectedProduct.getId()) {
                    return Inventory.getAllProducts().remove(product);
                }
            }
            return false;
        }


        /**
         * This return a list of all the parts.
         *
         * @return allParts
         */
        public static ObservableList<Part> getAllParts() {
            return allParts;
        }

        /**
         * This returns a list of all the products.
         *
         * @return allProducts
         */
        public static ObservableList<Product> getAllProducts() {
            return allProducts;
        }

    }

