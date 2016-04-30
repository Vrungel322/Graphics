package nanddgroup.graphics;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.data.BarData;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import nanddgroup.graphics.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity implements IDialogHelper{
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.listView1) ListView lv;
    private MainPresenter mp;
    private ChartDataAdapter cda;
    private ArrayList<BarData> list;
    public static Bus bus;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bus = new Bus();
        bus.register(this);
        setToolbar();
        mp = new MainPresenter(this);

    }

    @Subscribe
    public void showList(ArrayList<BarData> list) {
        cda = new ChartDataAdapter(this, getApplicationContext(), list);
        lv.setAdapter(cda);
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
                    finish();
                }
            }
        });
    }


    @Override
    public void showLoginProgressDialog() {
        mProgressDialog = new ProgressDialog(MainActivity.this, R.style
                .AppTheme_Dark_Dialog);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(getString(R.string.progress_load_carts));
        mProgressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        mProgressDialog.dismiss();
    }

}
