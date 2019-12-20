package by.slowar.rentchecker.ui.check.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import by.slowar.rentchecker.R;
import by.slowar.rentchecker.ui.check.CheckActivity;
import by.slowar.rentchecker.ui.items.ItemsFragment;
import by.slowar.rentchecker.ui.map.MapFragment;

/**
 * Created by SlowAR on 03.12.2019.
 */

public class CheckFragmentPagerAdapter extends FragmentPagerAdapter {

    public static final int PAGE_COUNT = 2;

    private String[] titles;
    private SparseArray<Fragment> registeredFragments;

    public CheckFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior, Context context) {
        super(fm, behavior);
        titles = new String[]{context.getString(R.string.items), context.getString(R.string.map)};
        registeredFragments = new SparseArray<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case CheckActivity.ITEMS_FRAGMENT:
                return ItemsFragment.newInstance();
            case CheckActivity.MAP_FRAGMENT:
                return MapFragment.newInstance();
            default:
                throw new IllegalArgumentException("No such fragment!");
        }
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}