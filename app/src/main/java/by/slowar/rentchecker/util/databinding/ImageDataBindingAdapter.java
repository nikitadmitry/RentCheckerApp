package by.slowar.rentchecker.util.databinding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

import by.slowar.rentchecker.R;

/**
 * Created by SlowAR on 11.12.2019.
 */

public class ImageDataBindingAdapter {

    @BindingAdapter({"app:srcUrl"})
    public static void loadPhoto(ImageView imageView, String url) {
        Picasso.get().load(url).placeholder(R.drawable.triangle).into(imageView);
    }
}