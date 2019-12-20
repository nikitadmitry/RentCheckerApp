package by.slowar.rentchecker.ui.items.info.adapter;

import androidx.recyclerview.widget.RecyclerView;

import by.slowar.rentchecker.ui.items.info.PhotoItemInfoBinding;

import static by.slowar.rentchecker.ui.items.info.adapter.PhotoItemInfoAdapter.Listener;

/**
 * Created by SlowAR on 11.12.2019.
 */

public class PhotoItemViewHolder extends RecyclerView.ViewHolder {

    private PhotoItemInfoBinding binding;
    private Listener listener;

    public PhotoItemViewHolder(PhotoItemInfoBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void bind(String url) {
        binding.setPhotoUrl(url);
        binding.setListener(listener);
        binding.executePendingBindings();
    }
}