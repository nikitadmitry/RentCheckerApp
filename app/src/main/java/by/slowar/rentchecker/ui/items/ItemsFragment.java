package by.slowar.rentchecker.ui.items;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import by.slowar.mvp.presenter.MvpPresenter;
import by.slowar.rentchecker.App;
import by.slowar.rentchecker.R;
import by.slowar.rentchecker.common.mvp.MvpFragment;
import by.slowar.rentchecker.data.model.Item;
import by.slowar.rentchecker.di.modules.RouterModule;
import by.slowar.rentchecker.ui.items.adapter.ItemsAdapter;

public class ItemsFragment extends MvpFragment<ItemsMvp.View> implements ItemsMvp.View, ItemsAdapter.Listener {

    private Listener mListener;
    private ItemsFragmentBinding binding;

    @Inject
    public ItemsMvp.Presenter presenter;

    public ItemsFragment() {
        // Required empty public constructor
    }

    public static ItemsFragment newInstance() {
        ItemsFragment fragment = new ItemsFragment();
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        App.getAppComponent().getScreenComponent(new RouterModule(getContext())).inject(this);
        if (context instanceof Listener) {
            mListener = (Listener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement Listener");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_items, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ItemsAdapter adapter = new ItemsAdapter(this);
        binding.itemsRecyclerView.setAdapter(adapter);
    }

    public void addItemsToList(Set<Item> items) {
        ItemsAdapter adapter = (ItemsAdapter) binding.itemsRecyclerView.getAdapter();
        if (adapter != null) {
            dispatchNewItems(new ArrayList<>(items), adapter);
            binding.emptyText.setVisibility(adapter.getItems().size() > 0 ? View.GONE : View.VISIBLE);
        }
    }

    private void dispatchNewItems(List<Item> newItems, ItemsAdapter adapter) {
//        ItemsDiffUtilCallback diffUtilCallback = new ItemsDiffUtilCallback(adapter.getItems(), newItems);
//        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);
//        adapter.setItems(newItems);
//        diffResult.dispatchUpdatesTo(adapter);
        adapter.setItems(newItems);         //TODO fix diffutil
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @NonNull
    @Override
    public MvpPresenter<ItemsMvp.View> getPresenter() {
        return presenter;
    }

    @Override
    public void onItemClick(Item item) {
        presenter.itemClicked(item);
    }

    public interface Listener {
    }
}