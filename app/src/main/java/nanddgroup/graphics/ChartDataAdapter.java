package nanddgroup.graphics;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;

import java.util.List;

/**
 * Created by Nikita on 30.04.2016.
 */
class ChartDataAdapter extends ArrayAdapter<BarData> {

    private MainActivity mainActivity;
    private Typeface mTf;

    public ChartDataAdapter(MainActivity mainActivity, Context context, List<BarData> objects) {
        super(context, 0, objects);
        this.mainActivity = mainActivity;

        mTf = Typeface.createFromAsset(mainActivity.getAssets(), "OpenSans-Light.ttf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BarData data = getItem(position);

        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_barchart, null);
            holder.chart = (BarChart) convertView.findViewById(R.id.chart);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // apply styling
        data.setValueTypeface(mTf);
        data.setValueTextColor(Color.BLACK);
        holder.chart.setDescription("");
        holder.chart.setDrawGridBackground(false);

        XAxis xAxis = holder.chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = holder.chart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(10, false);
        leftAxis.setSpaceTop(15f);

        YAxis rightAxis = holder.chart.getAxisRight();
        rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(10, true);
        rightAxis.setSpaceTop(15f);

        // set data
        holder.chart.setData(data);

        //set Marker view
        holder.chart.setMarkerView(new MyMarkerView(getContext(), R.layout.my_marker_view));

        // do not forget to refresh the chart
        holder.chart.animateY(700, Easing.EasingOption.EaseInCubic);

        return convertView;
    }

    private class ViewHolder {

        BarChart chart;
    }
}
