package sadba.lab.com.senprof.Remote;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import sadba.lab.com.senprof.Model.UserResponse;
import sadba.lab.com.senprof.Model.Users;

public interface IMyAPI {

    @Headers("Content-type: application/json")
    @POST("connexion_rnpt")
    Call<UserResponse> verifUser(@Body Users users);
}
