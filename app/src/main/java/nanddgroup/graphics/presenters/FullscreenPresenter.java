package nanddgroup.graphics.presenters;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import nanddgroup.graphics.IDialogHelper;
import nanddgroup.graphics.MainActivity;
import nanddgroup.graphics.model.LogInResponse;
import nanddgroup.graphics.remote.ILogIn;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nikita on 27.04.2016.
 */
public class FullscreenPresenter {

    private Context context;
    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ILogIn.BASE_URL)
            .build();
    private ILogIn intf = retrofit.create(ILogIn.class);
    private IDialogHelper mView;

    public FullscreenPresenter(Context context) {
        this.context = context;
    }

    public void setView(IDialogHelper view) {
        mView = view;
    }

    public void login(String login, String password) {
        makeCall(login, password);
        mView.showLoginProgressDialog();
    }

    public void makeCall(String login, String pass){
        Map<String, String> mapJson = new HashMap<String, String>();
        mapJson.put("login", login);
        mapJson.put("password", pass);
        Call<LogInResponse> call = intf.loginUser(mapJson);
        call.enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                    LogInResponse lir = new LogInResponse(response.body().getLang(),
                            response.body().getSuccess());
                    if (lir.getSuccess()){
                        context.startActivity(new Intent(context, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                    else {
                        Toast.makeText(context, "FAIL. WRONG LOGIN OR PAS", Toast.LENGTH_SHORT).show();
                    }
                mView.dismissProgressDialog();
            }
            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                Toast.makeText(context, "FAIL. NO INTERNET", Toast.LENGTH_SHORT).show();
                mView.dismissProgressDialog();
            }
        });
    }
}
