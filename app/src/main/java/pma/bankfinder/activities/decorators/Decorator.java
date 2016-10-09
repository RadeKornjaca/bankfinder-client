package pma.bankfinder.activities.decorators;

import android.content.Context;
import android.view.View;

/**
 * Created by rade on 24.9.16..
 */
public abstract class Decorator {
    protected Context context;
    protected View view;

    public Decorator(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    public abstract void decorate();
}
