package sk.groupin.groupin;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ProgressBar superProgressBar;
    WebView superWebView;
    LinearLayout myLinLayout;
    SwipeRefreshLayout superSwipeLayout;
    private BroadcastReceiver broadcastReceiver;

    public void toSync(View view) {
        Intent intent = new Intent(this, PhoneSync.class );
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superSwipeLayout = findViewById(R.id.mySwipeLayout);
        myLinLayout = findViewById(R.id.myLinLayout);
        superProgressBar = findViewById(R.id.myProgressBar);
        superWebView = findViewById(R.id.myWebView);


        superProgressBar.setMax(100);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter(MyFirebaseInstanceIdService.TOKEN_BROADCAST));

        superWebView.loadUrl("https://groupin.sk");

        superWebView.getSettings().setJavaScriptEnabled(true);
        superWebView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                myLinLayout.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                myLinLayout.setVisibility(View.GONE);
                superSwipeLayout.setRefreshing(false);
                super.onPageFinished(view, url);
            }
        });
        superWebView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                superProgressBar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getSupportActionBar().setTitle(title);
            }
        });

        superSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                superWebView.reload();
            }
        });


        if(getIntent().getExtras() != null){
            for(String key: getIntent().getExtras().keySet()){
                if(key.equals(("link"))){
                    superWebView.loadUrl(getIntent().getExtras().getString(key));
                    Toast.makeText(getApplicationContext(),"Stranka sa nacitava",Toast.LENGTH_LONG).show();
                }

            }

        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){


            NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            NotificationChannel mChannel =
                new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

                mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.BLUE);
                mChannel.enableVibration(true);

                mNotificationManager.createNotificationChannel(mChannel);
        }



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_back:
                onBackPressed();
                break;

            case R.id.menu_forward:
                onForwardPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void menuSync(){
        Intent intent = new Intent(this, PhoneSync.class );
        startActivity(intent);
    }

    private void onForwardPressed(){
        if (superWebView.canGoForward()){
            superWebView.goForward();
        }
        else {
            Toast.makeText(this,"Vpred nic nie je.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (superWebView.canGoBack()) {
            superWebView.goBack();
        }
        else {
            finish();
        }
    }
}
