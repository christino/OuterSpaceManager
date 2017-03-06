package arnoux.com.outerspacemanager.outerspacemanager;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by White on 06/03/2017.
 */

public class SignUpActivity extends AppCompatActivity {

    private TextView labelIdentifiant;
    private EditText inputIdentifiant;
    private TextView labelPassword;
    private EditText inputPassword;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

    }
}
