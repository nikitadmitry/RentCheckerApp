package by.slowar.rentchecker.ui.items.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import by.slowar.rentchecker.data.model.Item;

/**
 * Created by SlowAR on 03.12.2019.
 */

public class ItemsViewHolder extends RecyclerView.ViewHolder {

    private ItemBinding itemBinding;
    private ItemsAdapter.Listener listener;

    public ItemsViewHolder(@NonNull ItemBinding itemBinding) {
        super(itemBinding.getRoot());
        this.itemBinding = itemBinding;
    }

    public void setListener(ItemsAdapter.Listener listener) {
        this.listener = listener;
    }

    public void bind(Item item) {
        itemBinding.setItem(item);
        itemBinding.setListener(listener);
        itemBinding.executePendingBindings();
    }
}