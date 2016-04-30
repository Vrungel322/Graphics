package nanddgroup.graphics.presenters;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.IOException;
import java.util.ArrayList;

import nanddgroup.graphics.MainActivity;
import nanddgroup.graphics.model.DataResponse;
import nanddgroup.graphics.model.Item;
import nanddgroup.graphics.remote.IGetData;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nikita on 28.04.2016.
 */
public class MainPresenter {
    public int countOfDiscrets;
    public float[] profit_month;
    public float[] total_profit;
    public float[] profit_month_money;
    public float[] profit;
    public ArrayList<String> date;

    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(IGetData.BASE_URL)
            .build();
    private IGetData intf = retrofit.create(IGetData.class);
    private ArrayList<BarData> list;
    private ArrayList<Item> items;
    private MyAsyncTask at;
    private Call<DataResponse> call;

    public MainPresenter() {
        at = new MyAsyncTask();
        at.execute();
    }


    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            date = new ArrayList<String>();
            list = new ArrayList<BarData>();
            items = new ArrayList<Item>();
//            call = intf.getData();
        }

        @Override
        protected Void doInBackground(Void... params) {
            call = intf.getData();
            try {
                Response<DataResponse> dr = call.execute();
                countOfDiscrets = dr.body().getTotal();
                profit_month = new float[countOfDiscrets];
                total_profit = new float[countOfDiscrets];
                profit_month_money = new float[countOfDiscrets];
                profit = new float[countOfDiscrets];
//                Log.e("resp", String.valueOf(dr.code() + " " + countOfDiscrets));
                for (int i = 0; i < countOfDiscrets; i++) {
//                    items.add(new Item(dr.body().getItems().get(i).getProfit(),
//                            dr.body().getItems().get(i).getProfitMonthMoney(),
//                            dr.body().getItems().get(i).getTotalProfit(),
//                            dr.body().getItems().get(i).getProfitMonth(),
//                            dr.body().getItems().get(i).getDate()));

                    profit[i] = (Float.valueOf(dr.body().getItems().get(i).getProfit()));
                    profit_month_money[i] = (Float.valueOf(dr.body().getItems().get(i).getProfitMonthMoney()));
                    total_profit[i] = (Float.valueOf(dr.body().getItems().get(i).getTotalProfit()));
                    profit_month[i] = (Float.valueOf(dr.body().getItems().get(i).getProfitMonth()));
                    date.add(dr.body().getItems().get(i).getDate());
                    Log.e("TESTresponse", String.valueOf(countOfDiscrets));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for (int i = 0; i <= 3; i++) {
                list.add(generateData1(i, profit_month, total_profit, profit_month_money, profit, countOfDiscrets));
            }
            MainActivity.bus.post(list);
        }
    }


    private BarData generateData1(int cnt, float[] profit_month, float[] total_profit, float[] profit_month_money,
                                  float[] profit, int countOfDiscrets) {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        String chartName = "";

        switch (cnt) {
            case 0:
                for (int i = 0; i < countOfDiscrets; i++) {
                    entries.add(new BarEntry(profit_month[i], i));
                    chartName = "Profit per month";
                }
                break;
            case 1:
                for (int i = 0; i < countOfDiscrets; i++) {
                    entries.add(new BarEntry(total_profit[i], i));
                    chartName = "Total profit";
                }
                break;
            case 2:
                for (int i = 0; i < countOfDiscrets; i++) {
                    entries.add(new BarEntry(profit_month_money[i], i));
                    chartName = "profit_month_money";
                }
                break;
            case 3:
                for (int i = 0; i < countOfDiscrets; i++) {
                    entries.add(new BarEntry(profit[i], i));
                    chartName = "Profit";
                }
                break;
        }


        BarDataSet d = new BarDataSet(entries, chartName);
        d.setBarSpacePercent(20f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setBarShadowColor(Color.rgb(203, 203, 203));

        ArrayList<IBarDataSet> sets = new ArrayList<IBarDataSet>();
        sets.add(d);

        BarData cd = new BarData(date, sets);
        return cd;
    }


    public float[] getProfit_month() {
        return profit_month;
    }

    public int getCountOfDiscrets() {
        return countOfDiscrets;
    }
}
