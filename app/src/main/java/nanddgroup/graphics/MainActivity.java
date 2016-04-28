package nanddgroup.graphics;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nanddgroup.graphics.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    private MainPresenter mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
        mp = new MainPresenter();
        mp.makeCall();
        
        drawGraphics();
    }

    private void drawGraphics() {
        ListView lv = (ListView) findViewById(R.id.listView1);
        ArrayList<BarData> list = new ArrayList<BarData>();
        for (int i = 0; i < 4; i++) {
            list.add(generateData(i + 1));
        }
        ChartDataAdapter cda = new ChartDataAdapter(getApplicationContext(), list);
        lv.setAdapter(cda);
    }

    private BarData generateData(int cnt) {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry((int) (Math.random() * 70) + 30, i));
        }

        BarDataSet d = new BarDataSet(entries, "New DataSet " + cnt);
        d.setBarSpacePercent(20f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setBarShadowColor(Color.rgb(203, 203, 203));

        ArrayList<IBarDataSet> sets = new ArrayList<IBarDataSet>();
        sets.add(d);

        BarData cd = new BarData(getMonths(), sets);
        return cd;
    }

    private ArrayList<String> getMonths() {

        ArrayList<String> m = new ArrayList<String>();
        m.add("Jan");
        m.add("Feb");
        m.add("Mar");
        m.add("Apr");
        m.add("May");
        m.add("Jun");
        m.add("Jul");
        m.add("Aug");
        m.add("Sep");
        m.add("Okt");
        m.add("Nov");
        m.add("Dec");

        return m;
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.mipmap.ic_action_menu);
        getSupportActionBar().setTitle(R.string.title);
        Drawer drawer = new Drawer();
        drawer.withActivity(this)
                .withToolbar(mToolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Some Item")
                                .withIcon(FontAwesome.Icon.faw_github).withIdentifier(1),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.LogOut)
                                .withIcon(FontAwesome.Icon.faw_arrow_circle_left)
                )
                .build();
        drawer.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id, IDrawerItem drawerItem) {
                if (position == 1){
                    Toast.makeText(getApplicationContext(), "Some Item Clicked", Toast.LENGTH_SHORT).show();
                }
                if (position == 3){
                    Intent intent = new Intent(MainActivity.this, FullscreenActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private class ChartDataAdapter extends ArrayAdapter<BarData> {

        private Typeface mTf;

        public ChartDataAdapter(Context context, List<BarData> objects) {
            super(context, 0, objects);

            mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
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

            // do not forget to refresh the chart
//            holder.chart.invalidate();
            holder.chart.animateY(700, Easing.EasingOption.EaseInCubic);

            return convertView;
        }

        private class ViewHolder {

            BarChart chart;
        }
    }

}
