package sk.groupin.groupin;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.Credentials;

public class PhoneSync extends AppCompatActivity {

    public TextView usernameView;
    public TextView passwrodView;
    public Button syncPhoneButton;
    public static String username = "";
    public static String password = "";

    public String getByte64(String username, String password){
        return Credentials.basic(username,password);
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
