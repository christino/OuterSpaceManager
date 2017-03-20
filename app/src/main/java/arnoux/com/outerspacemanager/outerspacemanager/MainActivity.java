package arnoux.com.outerspacemanager.outerspacemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import arnoux.com.outerspacemanager.outerspacemanager.Entity.Building;
import arnoux.com.outerspacemanager.outerspacemanager.Entity.Research;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView userUsername;
    private TextView userPoints;
    private Button deconnexion;
    private Button batiments;
    private TextView userGas;
    private TextView userMineral;
    private Button research;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);

        userUsername = (TextView) findViewById(R.id.userUsername);
        userPoints = (TextView) findViewById(R.id.userPoints);
        userGas = (TextView) findViewById(R.id.userGas);
        userMineral = (TextView) findViewById(R.id.userMineral);
        deconnexion = (Button) findViewById(R.id.deconnexion);
        batiments = (Button) findViewById(R.id.menu_batiments);
        research = (Button) findViewById(R.id.menu_recherches);
        deconnexion.setOnClickListener(this);
        batiments.setOnClickListener(this);
        research.setOnClickListener(this);

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
                userPoints.setText(response.body().getPoints().toString());
                userGas.setText(response.body().getGas().toString());
                userMineral.setText(response.body().getMinerals().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view){
        if (view.getId() == R.id.deconnexion) {
            SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES_FILENAME, 0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(KEY_TOKEN);
            editor.commit();

            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.menu_batiments){
            Intent intent = new Intent(MainActivity.this, BuildingActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.menu_recherches){
            Intent intent = new Intent(MainActivity.this, ResearchesActivity.class);
            startActivity(intent);
        }


    }
}
