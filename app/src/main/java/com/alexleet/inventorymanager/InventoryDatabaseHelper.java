package com.alexleet.inventorymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * InventoryDatabaseHelper handles all database-related operations for the inventory system.
 * This includes creating the table, adding, deleting, updating, and fetching inventory items.
 */
public class InventoryDatabaseHelper extends SQLiteOpenHelper {

    // Database configuration
    private static final String DATABASE_NAME = "InventoryDB.db";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    private static final String TABLE_NAME = "inventory";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "item_name";
    private static final String COLUMN_QUANTITY = "quantity";

    // Constructor
    public InventoryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time.
     * Creates the inventory table with columns for ID, item name, and quantity.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT UNIQUE, "
                + COLUMN_QUANTITY + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    /**
     * Called when the database version is upgraded.
     * Drops the old table and recreates it.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Adds a new item to the inventory.
     * Returns true if added successfully, false if the item already exists.
     */
    public boolean addItem(String itemName) {
        if (itemExists(itemName)) return false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, itemName);
        values.put(COLUMN_QUANTITY, 0); // Default quantity

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }

    /**
     * Deletes an item from the inventory based on its name.
     */
    public void deleteItem(String itemName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_NAME + "=?", new String[]{itemName});
        db.close();
    }

    /**
     * Updates the quantity of an existing item.
     * Returns true if the update was successful.
     */
    public boolean updateQuantity(String itemName, int newQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUANTITY, newQuantity);

        int rowsAffected = db.update(TABLE_NAME, values, COLUMN_NAME + "=?", new String[]{itemName});
        db.close();
        return rowsAffected > 0;
    }

    /**
     * Retrieves all inventory items from the database.
     * Returns a list of InventoryItem objects.
     */
    public List<InventoryItem> getAllItems() {
        List<InventoryItem> itemList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                null, null, null, null, null,
                COLUMN_NAME + " ASC");

        // Loop through results
        if (cursor.moveToFirst()) {
            do {
                // Get item name and quantity from the current row
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY));

                // Create InventoryItem and increase quantity as needed
                InventoryItem item = new InventoryItem(name);
                while (item.getQuantity() < quantity) {
                    item.increaseQuantity();
                }
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return itemList;
    }

    /**
     * Checks if an item already exists in the inventory.
     * Returns true if found.
     */
    public boolean itemExists(String itemName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID},
                COLUMN_NAME + "=?",
                new String[]{itemName},
                null, null, null);

        boolean exists = cursor.moveToFirst();
        cursor.close();
        db.close();
        return exists;
    }
}
