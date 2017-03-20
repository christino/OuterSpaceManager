package arnoux.com.outerspacemanager.outerspacemanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import arnoux.com.outerspacemanager.outerspacemanager.Entity.BuildingResponse;
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
        setContentView(R.layout.activity_building_activity);
        this.rvGalaxy = (RecyclerView) findViewById(R.id.rvBuildings);
        // Permet de lier la recycler view avec l'élément recycler view dans le layout
        rvGalaxy.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCES_FILENAME, 0);
        String currentToken = settings.getString(KEY_TOKEN, null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);

        Call<BuildingResponse> call = service.getBuildings(currentToken);
        call.enqueue(new Callback<BuildingResponse>() {
            @Override
            public void onResponse(Call<BuildingResponse> call, Response<BuildingResponse> response) {
                rvGalaxy.setAdapter(new BuildingsAdapter(response.body().buildings, GalaxyActivity.this));
            }

            @Override
            public void onFailure(Call<BuildingResponse> call, Throwable t) {

            }
        });
    }
}
