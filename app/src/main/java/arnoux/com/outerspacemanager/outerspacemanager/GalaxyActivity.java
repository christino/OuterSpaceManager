package arnoux.com.outerspacemanager.outerspacemanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import arnoux.com.outerspacemanager.outerspacemanager.Entity.BuildingResponse;
import arnoux.com.outerspacemanager.outerspacemanager.Entity.OtherUsersResponse;
import arnoux.com.outerspacemanager.outerspacemanager.retrofit.model.OuterSpaceManagerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static arnoux.com.outerspacemanager.outerspacemanager.Settings.Setting.KEY_TOKEN;
import static arnoux.com.outerspacemanager.outerspacemanager.Settings.Setting.SHARED_PREFERENCES_FILENAME;

/**
 * Created by White on 14/03/2017.
 */

public class GalaxyActivity extends AppCompatActivity{

    private RecyclerView rvGalaxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galaxy_activity);
        this.rvGalaxy = (RecyclerView) findViewById(R.id.rvGalaxy);

        rvGalaxy.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCES_FILENAME, 0);
        String currentToken = settings.getString(KEY_TOKEN, null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);

        Call<OtherUsersResponse> call = service.getOtherUsers(currentToken);
        call.enqueue(new Callback<OtherUsersResponse>() {
            @Override
            public void onResponse(Call<OtherUsersResponse> call, Response<OtherUsersResponse> response) {
                rvGalaxy.setAdapter(new GalaxyAdapter(response.body().users, GalaxyActivity.this));
            }

            @Override
            public void onFailure(Call<OtherUsersResponse> call, Throwable t) {
            }
        });
    }
}
