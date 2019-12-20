package by.slowar.rentchecker.ui.check;

import android.content.Context;
import android.content.Intent;

import by.slowar.rentchecker.common.BaseRouter;
import by.slowar.rentchecker.ui.parameters.ParametersActivity;

/**
 * Created by SlowAR on 04.12.2019.
 */

public class CheckRouter extends BaseRouter implements CheckMvp.Router {

    public CheckRouter(Context context) {
        super(context);
    }

    @Override
    public void showParametersScreen() {
        Intent intent = ParametersActivity.newIntent(getContext());
        getContext().startActivity(intent);
    }
}