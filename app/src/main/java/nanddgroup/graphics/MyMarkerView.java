package nanddgroup.graphics;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

/**
 * Created by Nikita on 30.04.2016.
 */
public class MyMarkerView extends MarkerView {
    private TextView tvValue;
    private TextView tvDate;
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public MyMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        tvValue = (TextView) findViewById(R.id.tvValue);
        tvDate = (TextView) findViewById(R.id.tvDate);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        this.tvValue.setText("" + e.getVal());
        this.tvDate.setText("" + highlight.getXIndex());

    }

    @Override
    public int getXOffset(float xpos) {
        return - (getWidth() / 2);
    }

    @Override
    public int getYOffset(float ypos) {
        return - getHeight();
    }
}
