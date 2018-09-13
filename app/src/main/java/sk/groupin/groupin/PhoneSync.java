package sk.groupin.groupin;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class PhoneSync extends AppCompatActivity {

    public TextView usernameView;
    public TextView passwrodView;
    public Button syncPhoneButton;
    public static String username = "";
    public static String password = "";
    private ApiInterface apiInterface;
    private ApiInterfaceCreate apiInterfaceCreate;
    private GCMDevices devices;
    private String credentials;
    private String registration_id = FirebaseInstanceId.getInstance().getToken();
    private String cloud_message_type = "FCM";

    public String getByte64(String username, String password){
        return this.credentials = Credentials.basic(username,password);
    }








    public void syncPhone(View view) {

        username = usernameView.getText().toString();
        password = passwrodView.getText().toString();
        if (username == "" && password == ""){

            Log.i("XXXXX",username + " " + password);
            Toast.makeText(this, "Meno a heslo nesmu byt prazdne", Toast.LENGTH_LONG).show();

        } else {

            SharedPreferences sPref = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor sPrefEdit = sPref.edit();
            sPrefEdit.putString("username", username);
            sPrefEdit.putString("password", password);
            sPrefEdit.commit();

            getByte64(username, password);
            Log.i("RESULT: ", getByte64(username,password));

            apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
            Call<GCMDevices> call = apiInterface.getGCMDevices(this.credentials, username);
            call.enqueue(new Callback<GCMDevices>() {
                @Override
                public void onResponse(Call<GCMDevices> call, Response<GCMDevices> response) {
                    devices = response.body();
                    if (response.code() == 403){
                        Toast.makeText(PhoneSync.this, "Nespravne udaje", Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 404) {
                        apiInterfaceCreate = ApiClient.getRetrofitInstance().create(ApiInterfaceCreate.class);
                        Call<GCMDevices> callcreate = apiInterfaceCreate.putGCMDevices(credentials, registration_id, username,cloud_message_type );
                        Log.i("REQUEST:", callcreate.request().body().contentType().toString());
                        Log.i("REQUEST:", callcreate.request().headers().toString());
                        Log.i("REQUEST:", callcreate.request().method());

                        callcreate.enqueue(new Callback<GCMDevices>() {
                            @Override
                            public void onResponse(Call<GCMDevices> call, Response<GCMDevices> response) {
                                if (response.code() == 201){
                                    Toast.makeText(PhoneSync.this, "Zariadenie pridane USPESNE", Toast.LENGTH_LONG).show();
                                    finish();
                                }

                            }

                            @Override
                            public void onFailure(Call<GCMDevices> call, Throwable t) {
                                Toast.makeText(PhoneSync.this, "CHYBA: Nie je mozne sa pripojit na INTERNET", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else if (response.code() == 200){
                        Toast.makeText(PhoneSync.this, "Zariadenie je uz sparovane", Toast.LENGTH_LONG).show();
                        finish();
                    }

                    Log.i("RESPONSE", response.message());
//                    Log.i("RESPONSE BODY", String.valueOf(response.body().getUser()));
                    Log.i("RESPONSE HEADER", String.valueOf(response.headers()));
                    Log.i("RESPONSE RAW:", String.valueOf(response.raw()));
                    Log.i("RESPONSE CODE:", String.valueOf(response.code()));
                    Log.i("RESPO", String.valueOf(response.message()));


                }

                @Override
                public void onFailure(Call<GCMDevices> call, Throwable t) {
                    Toast.makeText(PhoneSync.this, "CHYBA: Nie je mozne sa pripojit na INTERNET", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_sync);


        passwrodView = findViewById(R.id.passwordView);
        usernameView = findViewById(R.id.usernameView);
        Button syncPhoneButton = findViewById(R.id.syncPhoneButton);

        SharedPreferences sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor sPrefEdit = sPref.edit();
        usernameView.setText(sPref.getString("username", "default"));
        passwrodView.setText(sPref.getString("password", "default"));

        Log.i("STORED USERNAME: ", this.username);
        Log.i("STORED PASSWORD: ", this.password);





    }
}
