package nanddgroup.graphics.remote;

import nanddgroup.graphics.model.DataResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nikita on 28.04.2016.
 */
public interface IGetData {

    public static String BASE_URL = "https://service.4invest.net";

    @GET("/en/test/trust_graphic/")
    Call<DataResponse> getData();
}
