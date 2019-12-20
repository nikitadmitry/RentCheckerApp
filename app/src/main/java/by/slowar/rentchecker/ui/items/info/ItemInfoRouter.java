package by.slowar.rentchecker.ui.items.info;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import by.slowar.rentchecker.R;
import by.slowar.rentchecker.common.BaseRouter;

/**
 * Created by SlowAR on 04.12.2019.
 */

public class ItemInfoRouter extends BaseRouter implements ItemInfoMvp.Router {

    public ItemInfoRouter(Context context) {
        super(context);
    }

    @Override
    public void showNumberDialScreen(String number) {
        Uri phoneNumber = Uri.parse("tel:" + number);
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, phoneNumber);
        try {
            getContext().startActivity(dialIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), getContext().getString(R.string.dial_error), Toast.LENGTH_SHORT).show();
        }
    }
}