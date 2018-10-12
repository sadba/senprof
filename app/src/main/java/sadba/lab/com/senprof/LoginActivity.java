package sadba.lab.com.senprof;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import dmax.dialog.SpotsDialog;
import io.realm.Realm;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sadba.lab.com.senprof.Model.UserResponse;
import sadba.lab.com.senprof.Model.Users;
import sadba.lab.com.senprof.Remote.Common;
import sadba.lab.com.senprof.Remote.IMyAPI;

public class LoginActivity extends AppCompatActivity {

    EditText edt_ien;
    Button btnLogin;
    SharedPreferences sp;
    IMyAPI mService;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Init Service
        mService = Common.getAPI();
        //Init View
        edt_ien = findViewById(R.id.edt_ien);
        btnLogin = findViewById(R.id.btn_login);



        sp = getSharedPreferences("btn_login", MODE_PRIVATE);

        if (sp.getBoolean("logged", false)){
            gotToHomeActivity();
        }



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ien =  edt_ien.getText().toString();
                if (TextUtils.isEmpty(ien)){
                    edt_ien.setError("Veuillez renseigner votre IEN");
                } else if (ien.length() < 8){
                    edt_ien.setError("Cet IEn n'est pas valide");
                } else {
                    connectUser(ien);
                }

            }
        });

        //sadTest(edt_ien.getText().toString());
    }

   /* private void sadTest(String ien) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("blabla")
                .build();

        IMyAPI api = retrofit.create(IMyAPI.class);
        Users users = new Users();
        users.setIen(ien);
        api.verifUser(users).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    } */

    private void connectUser(String ien) {
        final android.app.AlertDialog watingDialog = new SpotsDialog(LoginActivity.this);
        watingDialog.show();
        watingDialog.setTitle("Connection en cours...");
        Users users = new Users();
        users.setIen(ien);
        mService.verifUser(users)
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                        final UserResponse result = response.body();
                        assert result != null;
                        if (result.getCode().equals("1")) {
                            watingDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Paramétres de connection incorrectes", Toast.LENGTH_LONG).show();
                        } else {
                            gotToHomeActivity();
                            sp.edit().putBoolean("logged", true).apply();
                            Realm mRealm = null;
                            try {
                                mRealm = Realm.getDefaultInstance();
                                final Realm finalMRealm = mRealm;
                                mRealm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(@NonNull Realm realm) {
                                        try {
                                            UserResponse userResponse = new UserResponse();
                                            userResponse.setCode(result.getCode());
                                            userResponse.setMessage(result.getMessage());
                                            userResponse.setIen(result.getIen());
                                            userResponse.setPrenom(result.getPrenom());
                                            userResponse.setNom(result.getNom());
                                            userResponse.setType_ien(result.getType_ien());
                                            userResponse.setAvatar(result.getAvatar());

                                            finalMRealm.copyToRealm(userResponse);

                                        } catch (RealmPrimaryKeyConstraintException e) {
                                            Toast.makeText(getApplicationContext(), "Primary Key exists, Press Update instead", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });



                            } finally {
                                if (mRealm != null) {
                                    mRealm.close();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        watingDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Erreur lors de la connection", Toast.LENGTH_SHORT).show();

                    }
                });
    }


    private void gotToHomeActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
        }
    }



}
