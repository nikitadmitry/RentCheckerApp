package by.slowar.rentchecker.ui.items.adapter;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import by.slowar.rentchecker.data.model.Item;

/**
 * Created by SlowAR on 04.12.2019.
 */

public class ItemsDiffUtilCallback extends DiffUtil.Callback {

    private final List<Item> oldItems;
    private final List<Item> newItems;

    public ItemsDiffUtilCallback(List<Item> oldItems, List<Item> newItems) {
        this.oldItems = oldItems;
        this.newItems = newItems;
    }

    @Override
    public int getOldListSize() {
        return oldItems.size();
    }

    @Override
    public int getNewListSize() {
        return oldItems.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Item oldItem = oldItems.get(oldItemPosition);
        Item newItem = newItems.get(newItemPosition);
        return oldItem.getAddress().equals(newItem.getAddress());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Item oldItem = oldItems.get(oldItemPosition);
        Item newItem = newItems.get(newItemPosition);
        return oldItem.getPhone().equals(newItem.getPhone()) && oldItem.getPrice() == newItem.getPrice() &&
                oldItem.getOwnerName().equals(newItem.getOwnerName());
    }
}