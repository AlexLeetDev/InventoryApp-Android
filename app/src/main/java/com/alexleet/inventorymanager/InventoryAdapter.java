package com.alexleet.inventorymanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * RecyclerView Adapter for displaying and interacting with inventory items.
 * Handles displaying item name, quantity, and action buttons for each item.
 */
public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {

    private final List<InventoryItem> items; // List of inventory items to display
    private final OnInventoryActionListener actionListener; // Listener for item button actions

    /**
     * Interface for handling item action events (delete, increase, decrease).
     */
    public interface OnInventoryActionListener {
        void onDeleteClick(int position);
        void onIncreaseClick(int position);
        void onDecreaseClick(int position);
    }

    /**
     * Constructor for InventoryAdapter.
     * @param items List of inventory items
     * @param listener Callback interface for item actions
     */
    public InventoryAdapter(List<InventoryItem> items, OnInventoryActionListener listener) {
        this.items = items;
        this.actionListener = listener;
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inventory, parent, false);
        return new InventoryViewHolder(view);
    }

    /**
     * ViewHolder class helps show each item by keeping track of its views.
     */
    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        InventoryItem item = items.get(position);

        // Display item name and quantity
        holder.textItem.setText(item.getName());
        holder.textQuantity.setText(String.valueOf(item.getQuantity()));

        // Set button actions for delete, increase, and decrease
        holder.btnDelete.setOnClickListener(v -> actionListener.onDeleteClick(position));
        holder.btnIncrease.setOnClickListener(v -> actionListener.onIncreaseClick(position));
        holder.btnDecrease.setOnClickListener(v -> actionListener.onDecreaseClick(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class InventoryViewHolder extends RecyclerView.ViewHolder {
        TextView textItem, textQuantity;
        MaterialButton btnDelete, btnIncrease, btnDecrease;

        InventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textItem = itemView.findViewById(R.id.textItem);
            textQuantity = itemView.findViewById(R.id.textQuantity);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
        }
    }
}
