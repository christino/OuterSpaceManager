package arnoux.com.outerspacemanager.outerspacemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import arnoux.com.outerspacemanager.outerspacemanager.Entity.User;
import arnoux.com.outerspacemanager.outerspacemanager.retrofit.model.OuterSpaceManagerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static arnoux.com.outerspacemanager.outerspacemanager.Settings.Setting.KEY_TOKEN;
import static arnoux.com.outerspacemanager.outerspacemanager.Settings.Setting.SHARED_PREFERENCES_FILENAME;

/**
 * Created by White on 07/03/2017.
 */

public class MainActivity extends AppCompatActivity {

    private TextView userUsername;
    private TextView userPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);

        userUsername = (TextView) findViewById(R.id.userUsername);
        userPoints = (TextView) findViewById(R.id.userPoints);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);

        SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCES_FILENAME, 0);
        String currentToken = settings.getString(KEY_TOKEN, null);

        Call<User> call = service.getUser(currentToken);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userUsername.setText(response.body().getUsername());
                //userPoints.setText(response.body().getPoints());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
