package arnoux.com.outerspacemanager.outerspacemanager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
 * Created by White on 06/03/2017.
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signUpValidate;
    private EditText inputIdentifiant;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCES_FILENAME, 0);
        String currentToken = settings.getString(KEY_TOKEN, null);

        if (currentToken == null){
            setContentView(R.layout.activity_signup);
            signUpValidate = (Button) findViewById(R.id.signUpValidate);
            inputIdentifiant = (EditText) findViewById(R.id.inputIdentifiant);
            inputPassword = (EditText) findViewById(R.id.inputPassword);
            signUpValidate.setOnClickListener(this);
        } else {
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.signUpValidate) {
            User user = new User(inputIdentifiant.getText().toString(), inputPassword.getText().toString());

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://outer-space-manager.herokuapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);

            Call<User> call = service.createUser(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User user = response.body();
                        SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES_FILENAME, 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(KEY_TOKEN, user.getToken());
                        editor.commit();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        //TODO: Handle errors here
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    //TODO: Handle errors here too
                }
            });
        }
    }
}
