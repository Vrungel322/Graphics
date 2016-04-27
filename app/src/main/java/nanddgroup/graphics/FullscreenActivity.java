package nanddgroup.graphics;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity implements Presenter.IView{
    private EditText etLogin;
    private EditText etPassword;
    private Button bLogIn;
    private Presenter p;
    private String login = "vlad.palamarchuk";
    private String password = "1234.1234";
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fullscreen);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {actionBar.hide();}
        p = new Presenter(getApplicationContext());
        p.setView(this);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etLogin.setText(login);
        etPassword = (EditText) findViewById(R.id.etPassword);;
        etPassword.setText(password);
        bLogIn = (Button) findViewById(R.id.bLogIn);
        bLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               p.login(etLogin.getText().toString(),
                       etPassword.getText().toString());
            }
        });
    }

    @Override
    public void showLoginProgressDialog() {
        mProgressDialog = new ProgressDialog(FullscreenActivity.this, R.style
                .AppTheme_Dark_Dialog);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(getString(R.string.progress_authenticate));
        mProgressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        mProgressDialog.dismiss();
    }
}
