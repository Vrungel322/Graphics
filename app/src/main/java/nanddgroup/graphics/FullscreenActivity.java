package nanddgroup.graphics;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    private EditText etLogin;
    private EditText etPassword;
    private Button bLogIn;
    private ProgressBar spinner;
    private Presenter p;
    private String login = "vlad.palamarchuk";
    private String password = "1234.1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fullscreen);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        p = new Presenter(getApplicationContext());
        spinner = (ProgressBar)findViewById(R.id.pbLogin);
        etLogin = (EditText) findViewById(R.id.etLogin);
        etLogin.setText(login);
        etPassword = (EditText) findViewById(R.id.etPassword);;
        etPassword.setText(password);
        bLogIn = (Button) findViewById(R.id.bLogIn);
        bLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               p.makeCall(etLogin.getText().toString(),
                       etPassword.getText().toString());
            }
        });
    }

}
