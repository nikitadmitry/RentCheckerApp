package by.slowar.rentchecker.ui.items.info.photo;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import by.slowar.rentchecker.R;

/**
 * Created by SlowAR on 11.12.2019.
 */

public class PhotoDialog extends DialogFragment {

    private PhotoDialogBinding binding;
    private Drawable photo;

    public static PhotoDialog newInstance() {
        PhotoDialog photoDialog = new PhotoDialog();
        return photoDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Activity activity = getActivity();
        if (activity != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = activity.getLayoutInflater();
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo_dialog, null, false);
            builder.setView(binding.getRoot());
            if (binding != null) {
                setPhotoIntoImage();
            }
            return builder.create();
        }
        return super.onCreateDialog(savedInstanceState);
    }

    public void setImage(Drawable drawable) {
        photo = drawable;
        if (binding != null) {
            setPhotoIntoImage();
        }
    }

    private void setPhotoIntoImage() {
        binding.photoItemLarge.setImageDrawable(photo);
        binding.executePendingBindings();
    }
}