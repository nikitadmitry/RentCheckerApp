package by.slowar.rentchecker.ui.items.info.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import by.slowar.rentchecker.R;
import by.slowar.rentchecker.ui.items.info.PhotoItemInfoBinding;

/**
 * Created by SlowAR on 11.12.2019.
 */

public class PhotoItemInfoAdapter extends RecyclerView.Adapter<PhotoItemViewHolder> {

    private List<String> urlList;
    private Listener listener;

    public PhotoItemInfoAdapter(List<String> urlList, Listener listener) {
        this.urlList = urlList;
        this.listener = listener;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList.clear();
        this.urlList.addAll(urlList);
    }

    @NonNull
    @Override
    public PhotoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PhotoItemInfoBinding binding = DataBindingUtil.inflate(inflater, R.layout.photo_item_info, parent, false);
        PhotoItemViewHolder holder = new PhotoItemViewHolder(binding);
        holder.setListener(listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoItemViewHolder holder, int position) {
        holder.bind(urlList.get(position));
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    public interface Listener {

        void onImageClick(ImageView imageView);
    }
}