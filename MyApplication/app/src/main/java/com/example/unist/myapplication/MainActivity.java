package com.example.unist.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Res_menu_item> items;
    int price;
    String name;
    ListView mlist;
    Res_menu_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mlist = (ListView) findViewById(R.id.list);
        items = new ArrayList<Res_menu_item>();
        adapter = new Res_menu_adapter(this,R.layout.res_menu_item,items);
        mlist.setEnabled(false);
        new HttpPostRequest().execute("");


    }

    public class HttpPostRequest extends AsyncTask<String,Void,String> {
        String sResult="error";
        @Override
        protected String doInBackground(String... info) {
            URL url = null;
            try {
                url = new URL("http://52.69.163.43/queuing/get_all_rest_menu.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                Log.e("Http connection","완료");
                conn.setRequestMethod("POST");
                String post_value = "";

                Log.e("Http connection2","완료");
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuilder builder = new StringBuilder();
                Log.e("InputStreamReader","완료");

                String str;
                while ((str = reader.readLine()) != null) {
                    builder.append(str);
                }
                sResult = builder.toString();
                Log.e("sResult","완료");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(e.toString(),sResult);
            }


            return sResult;
        }
        @Override
        protected void onPostExecute(String result){
            Log.e("RESULT", result);
            String jsonall = result;
            JSONArray jArray = null;

            try{
                jArray = new JSONArray(jsonall);
                JSONObject json_data = null;

                for (int i = 0; i < jArray.length(); i++) {
                    json_data = jArray.getJSONObject(i);
                    name = json_data.getString("food_name");
                    price = json_data.getInt("price");

                    items.add(new Res_menu_item(name,price));
                    Log.e("PROFILE",":"+i);

                }
            }catch(Exception e){
                e.printStackTrace();
            }
            mlist.setAdapter(adapter);





        }
    }
}
