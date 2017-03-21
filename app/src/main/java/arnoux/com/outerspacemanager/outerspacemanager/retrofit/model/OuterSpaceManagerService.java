package arnoux.com.outerspacemanager.outerspacemanager.retrofit.model;

import arnoux.com.outerspacemanager.outerspacemanager.Entity.Building;
import arnoux.com.outerspacemanager.outerspacemanager.Entity.BuildingResponse;
import arnoux.com.outerspacemanager.outerspacemanager.Entity.OtherUsersResponse;
import arnoux.com.outerspacemanager.outerspacemanager.Entity.Research;
import arnoux.com.outerspacemanager.outerspacemanager.Entity.ResearchResponse;
import arnoux.com.outerspacemanager.outerspacemanager.Entity.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by White on 07/03/2017.
 */

public interface OuterSpaceManagerService {
    @POST("/api/v1/auth/create")
    Call<User> createUser(@Body User user);

    @GET("/api/v1/users/get")
    Call<User> getUser(@Header("x-access-token") String token);

    @POST("/api/v1/auth/login")
    Call<User> loginUser(@Body User user);

    @GET("/api/v1/buildings/list")
    Call<BuildingResponse> getBuildings(@Header("x-access-token") String accessToken);

    @GET("/api/v1/searches/list")
    Call<ResearchResponse> getResearches(@Header("x-access-token") String accessToken);

    @POST("/api/v1/buildings/create/{buildingId}")
    Call<Building> upgradeBuilding(@Path("buildingId") Integer buildingId, @Header("x-access-token") String token);

    @POST("/api/v1/searches/create/{searchId}")
    Call<Research> upgradeResearch(@Path("searchId") Integer searchId, @Header("x-access-token") String token);

    @GET("/api/v1/users/0/19")
    Call<OtherUsersResponse> getOtherUsers(@Header("x-access-token") String accessToken);
}
