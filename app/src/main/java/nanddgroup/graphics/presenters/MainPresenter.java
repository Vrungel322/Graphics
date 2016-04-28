package nanddgroup.graphics.presenters;

import android.util.Log;

import nanddgroup.graphics.model.DataResponse;
import nanddgroup.graphics.remote.IGetData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nikita on 28.04.2016.
 */
public class MainPresenter {

    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(IGetData.BASE_URL)
            .build();
    private IGetData intf = retrofit.create(IGetData.class);

    public void makeCall(){
        Call<DataResponse> call = intf.getData();
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                Log.e("resp", String.valueOf(response.code() + " " + response.body().getTotal()));
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {

            }
        });
    }
}
