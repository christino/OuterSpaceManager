package arnoux.com.outerspacemanager.outerspacemanager.retrofit.model;

import arnoux.com.outerspacemanager.outerspacemanager.Entity.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by White on 07/03/2017.
 */

public interface OuterSpaceManagerService {
    @POST("/api/v1/auth/create")
    Call<User> createUser(@Body User user);

    @GET("/api/v1/users/get")
    Call<User> getUser(@Header("x-access-token") String token);
}
