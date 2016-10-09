package pma.bankfinder.activities.decorators;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by rade on 24.9.16..
 */
public class ToolbarDecorator extends Decorator {

    private int toolbarViewId;
    private String toolbarTitle;

    public ToolbarDecorator(Context context, View view, int toolbarViewId, String toolbarTitle) {
        super(context, view);
        this.toolbarViewId = toolbarViewId;
        this.toolbarTitle = toolbarTitle;
    }

    @Override
    public void decorate() {
        Toolbar toolbar = (Toolbar) view.findViewById(toolbarViewId);
        toolbar.setTitle(toolbarTitle);
        ((AppCompatActivity) context).setSupportActionBar(toolbar);

    }

    public void decorate(boolean toolbarUpButtonEnabled) {
        decorate();
        ((AppCompatActivity) context).getSupportActionBar().setDisplayHomeAsUpEnabled(toolbarUpButtonEnabled);
    }
}
