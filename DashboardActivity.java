package com.alexleet.inventorymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.alexleet.inventorymanager.databinding.ActivityDashboardBinding;

import java.util.List;

/**
 * DashboardActivity displays the inventory grid and allows the user
 * to add items, update quantities, and access SMS permission settings.
 */
public class DashboardActivity extends AppCompatActivity {

    // View binding for layout views
    private ActivityDashboardBinding binding;

    // Adapter for RecyclerView
    private InventoryAdapter adapter;

    // Database helper instance for inventory operations
    private InventoryDatabaseHelper dbHelper;

    // List holding current inventory items
    private List<InventoryItem> inventoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize database helper
        dbHelper = new InventoryDatabaseHelper(this);

        // Load data from database
        inventoryList = dbHelper.getAllItems();

        // Set up adapter with action listeners
        adapter = new InventoryAdapter(inventoryList, new InventoryAdapter.OnInventoryActionListener() {
            @Override
            public void onDeleteClick(int position) {
                InventoryItem item = inventoryList.get(position);
                dbHelper.deleteItem(item.getName());
                inventoryList.remove(position);
                adapter.notifyItemRemoved(position);
            }

            @Override
            public void onIncreaseClick(int position) {
                InventoryItem item = inventoryList.get(position);
                item.increaseQuantity();
                boolean updated = dbHelper.updateQuantity(item.getName(), item.getQuantity());
                if (updated) {
                    adapter.notifyItemChanged(position);
                } else {
                    Toast.makeText(DashboardActivity.this, "Failed to update quantity", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onDecreaseClick(int position) {
                InventoryItem item = inventoryList.get(position);
                item.decreaseQuantity();
                boolean updated = dbHelper.updateQuantity(item.getName(), item.getQuantity());
                if (updated) {
                    adapter.notifyItemChanged(position);
                } else {
                    Toast.makeText(DashboardActivity.this, "Failed to update quantity", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set up RecyclerView
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        binding.recyclerView.setAdapter(adapter);

        // Add item button logic
        binding.btnAddItem.setOnClickListener(view -> {
            String newItemName = (binding.editTextNewItem.getText() != null)
                    ? binding.editTextNewItem.getText().toString().trim()
                    : "";

            if (newItemName.isEmpty()) {
                Toast.makeText(this, "Enter an item name", Toast.LENGTH_LONG).show();
            } else if (dbHelper.itemExists(newItemName)) {
                Toast.makeText(this, "Item already exists", Toast.LENGTH_LONG).show();
            } else {
                boolean added = dbHelper.addItem(newItemName);
                if (added) {
                    InventoryItem newItem = new InventoryItem(newItemName);
                    inventoryList.add(newItem);
                    adapter.notifyItemInserted(inventoryList.size() - 1);
                    binding.editTextNewItem.setText("");

                    Toast.makeText(this, newItemName + " added to inventory", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Failed to add item", Toast.LENGTH_LONG).show();
                }
            }
        });

        // SMS Settings button click
        binding.btnSmsSettings.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, SmsPermissionActivity.class);
            startActivity(intent);
        });
    }
}
