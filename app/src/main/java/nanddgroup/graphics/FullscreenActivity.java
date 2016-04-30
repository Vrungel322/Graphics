package nanddgroup.graphics;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nanddgroup.graphics.presenters.FullscreenPresenter;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity implements IDialogHelper {
    @Bind(R.id.etLogin) EditText etLogin;
    @Bind(R.id.etPassword) EditText etPassword;
    @Bind(R.id.bLogIn) Button bLogIn;
    private FullscreenPresenter p;
    private String login = "vlad.palamarchuk";
    private String password = "1234.1234";
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fullscreen);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {actionBar.hide();}
        p = new FullscreenPresenter(getApplicationContext());
        p.setView(this);

        etLogin.setText(login);
        etPassword.setText(password);
    }

    @OnClick(R.id.bLogIn)
    public void bLogInClicked(){
        p.login(etLogin.getText().toString(),
                etPassword.getText().toString());
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
