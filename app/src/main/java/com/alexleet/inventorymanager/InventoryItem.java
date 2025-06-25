package com.alexleet.inventorymanager;

/**
 * InventoryItem represents a single item in the inventory.
 * Each item has a name and a quantity that can be increased or decreased.
 */
public class InventoryItem {

    // The name of the item (e.g., "Apples", "Chairs")
    private final String name;

    // The current quantity of this item in stock
    private int quantity;

    /**
     * Constructor that creates a new inventory item with a given name.
     * The quantity is set to 0 by default.
     */
    public InventoryItem(String name) {
        this.name = name;
        this.quantity = 0; // default starting quantity
    }

    /**
     * Returns the name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Increases the quantity of the item by 1.
     */
    public void increaseQuantity() {
        quantity++;
    }

    /**
     * Decreases the quantity of the item by 1,
     * but only if the quantity is greater than 0.
     */
    public void decreaseQuantity() {
        if (quantity > 0) quantity--;
    }
}
