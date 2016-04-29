package nanddgroup.graphics.presenters;

import android.graphics.Color;
import android.os.AsyncTask;

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
    public ArrayList<Float> total_profit;
    public ArrayList<Float> profit_month_money;
    public ArrayList<Float> profit;
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

//    public ArrayList<BarData> makeCall(){
//        total_profit = new ArrayList<Float>();
//        profit_month_money = new ArrayList<Float>();
//        profit = new ArrayList<Float>();
//        date = new ArrayList<String>();
//        list = new ArrayList<BarData>();
//        items = new ArrayList<Item>();
//        call = intf.getData();
////        call.enqueue(new Callback<DataResponse>() {
////            @Override
////            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
////                Log.e("resp", String.valueOf(response.code() + " " + response.body().getTotal()));
////                countOfDiscrets = response.body().getTotal();
////                profit_month = new float[countOfDiscrets];
////                for (int i = 0; i < countOfDiscrets; i++){
////                    items.add(new Item(response.body().getItems().get(i).getProfit(),
////                            response.body().getItems().get(i).getProfitMonthMoney(),
////                            response.body().getItems().get(i).getTotalProfit(),
////                            response.body().getItems().get(i).getProfitMonth(),
////                            response.body().getItems().get(i).getDate()));
////
////                    profit.add(Float.valueOf(items.get(i).getProfit()));
////                    profit_month_money.add(Float.valueOf(items.get(i).getProfitMonthMoney()));
////                    total_profit.add(Float.valueOf(items.get(i).getTotalProfit()));
////                    profit_month[i] = (Float.valueOf(items.get(i).getProfitMonth()));
////                    date.add(String.valueOf(Float.valueOf(items.get(i).getDate())));
////                }
////            }
////
////            @Override
////            public void onFailure(Call<DataResponse> call, Throwable t) {
////
////            }
////        });
//        return list;
//    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            total_profit = new ArrayList<Float>();
            profit_month_money = new ArrayList<Float>();
            profit = new ArrayList<Float>();
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
//                Log.e("resp", String.valueOf(dr.code() + " " + countOfDiscrets));
                for (int i = 0; i < countOfDiscrets; i++){
//                    items.add(new Item(dr.body().getItems().get(i).getProfit(),
//                            dr.body().getItems().get(i).getProfitMonthMoney(),
//                            dr.body().getItems().get(i).getTotalProfit(),
//                            dr.body().getItems().get(i).getProfitMonth(),
//                            dr.body().getItems().get(i).getDate()));

                    profit.add(Float.valueOf(dr.body().getItems().get(i).getProfit()));
                    profit_month_money.add(Float.valueOf(dr.body().getItems().get(i).getProfitMonthMoney()));
                    total_profit.add(Float.valueOf(dr.body().getItems().get(i).getTotalProfit()));
                    profit_month[i] = (Float.valueOf(dr.body().getItems().get(i).getProfitMonth()));
                    date.add(dr.body().getItems().get(i).getDate());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            list.add(generateData1(1, profit_month, countOfDiscrets));
            MainActivity.bus.post(list);
        }
    }



    private BarData generateData1(int cnt, float[] profit_month, int countOfDiscrets) {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < countOfDiscrets; i++) {
            entries.add(new BarEntry(profit_month, i));
        }

        BarDataSet d = new BarDataSet(entries, "New DataSet " + cnt);
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
