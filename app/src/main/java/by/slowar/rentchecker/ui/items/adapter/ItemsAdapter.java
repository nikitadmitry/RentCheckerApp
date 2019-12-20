package by.slowar.rentchecker.ui.items.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import by.slowar.rentchecker.R;
import by.slowar.rentchecker.data.model.Item;

/**
 * Created by SlowAR on 03.12.2019.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsViewHolder> {

    private List<Item> items;
    private Listener listener;

    public ItemsAdapter(Listener listener) {
        this.listener = listener;
        items = new LinkedList<>();
    }

    public ItemsAdapter(List<Item> newItems, Listener listener) {
        items = new LinkedList<>(newItems);
        this.listener = listener;
    }

    public void setItems(List<Item> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.item, parent, false);
        ItemsViewHolder itemsViewHolder = new ItemsViewHolder(binding);
        itemsViewHolder.setListener(listener);
        return itemsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<Item> getItems() {
        return items;
    }

    public interface Listener {

        void onItemClick(Item item);
    }
}