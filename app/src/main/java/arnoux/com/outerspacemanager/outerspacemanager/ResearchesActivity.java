package arnoux.com.outerspacemanager.outerspacemanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import arnoux.com.outerspacemanager.outerspacemanager.Entity.ResearchResponse;
import arnoux.com.outerspacemanager.outerspacemanager.retrofit.model.OuterSpaceManagerService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static arnoux.com.outerspacemanager.outerspacemanager.Settings.Setting.KEY_TOKEN;
import static arnoux.com.outerspacemanager.outerspacemanager.Settings.Setting.SHARED_PREFERENCES_FILENAME;

/**
 * Created by White on 20/03/2017.
 */

public class ResearchesActivity extends AppCompatActivity {

    private RecyclerView rvResearches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_researche_activity);
        this.rvResearches = (RecyclerView) findViewById(R.id.rvResearches);

        rvResearches.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCES_FILENAME, 0);
        String currentToken = settings.getString(KEY_TOKEN, null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);

        Call<ResearchResponse> call = service.getResearches(currentToken);

        call.enqueue(new Callback<ResearchResponse>() {
            @Override
            public void onResponse(Call<ResearchResponse> call, Response<ResearchResponse> response) {
                rvResearches.setAdapter(new ResearchesAdapter(response.body().searches, ResearchesActivity.this));
            }

            @Override
            public void onFailure(Call<ResearchResponse> call, Throwable t) {

            }
        });
    }
}
