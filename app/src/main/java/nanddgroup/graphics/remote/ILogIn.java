package nanddgroup.graphics.remote;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Nikita on 27.04.2016.
 */
public interface ILogIn {
    public static String BASE_URL = "https://service.4invest.net";

    @FormUrlEncoded
    @POST("/en/auth/login/")
    Call<LogInResponse> loginUser(@FieldMap Map<String, String> map);
}
