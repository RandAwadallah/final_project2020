package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InsertActivity extends AppCompatActivity {
    String txtName, txtprice,txtstorage,txtspecs;
    String ServerURL;
    EditText editText, storageText, priceText, specsText;
    Button InsertData, choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        ServerURL = "https://www.palpharmacy.com/insert_mobile.php";

        // ServerURL = "http://172.20.10.3/mobiles/insert_mobiles.php";

        editText = findViewById(R.id.NameEditTex);
        priceText = findViewById(R.id.priceEditTex);
        storageText = findViewById(R.id.storageEditTex);
        specsText = findViewById(R.id.specsEditTex);

        InsertData =  findViewById(R.id.InsertButton);

        InsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetData();
                InsertData(txtName,txtspecs,txtprice,txtstorage);
            }
        });
    }

    public void GetData(){
        txtName = editText.getText().toString();
        txtprice= priceText.getText().toString();
        txtstorage= storageText.getText().toString();
        txtspecs= specsText.getText().toString();

    }

    public void InsertData(final String name, final String specs, final String price, final String storage){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("specs", specs));
                nameValuePairs.add(new BasicNameValuePair("price", price));
                nameValuePairs.add(new BasicNameValuePair("storage", storage));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(ServerURL);
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    HttpEntity httpEntity = httpResponse.getEntity();
                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data inserted successfully";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(InsertActivity.this, "Data inserted successfully", Toast.LENGTH_LONG).show();
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name, price, storage, specs);
    }
}
