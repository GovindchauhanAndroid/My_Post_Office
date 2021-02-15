package com.yandexindia.Yandex_Pincode_Finder;

import android.app.LauncherActivity.ListItem;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BankName extends AppCompatActivity {
    private final ArrayList<String> arrayName = new ArrayList<>();
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private List<ListItem> listItems;
    private String URL;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_name);
        listView = findViewById(R.id.list_view_result);
        progressDialog = new ProgressDialog(BankName.this);
        //Set the dialog message to 'Loading application View, please wait...'
        progressDialog.setMessage("Loading application Please wait...");
        //This dialog can't be canceled by pressing the back key
        progressDialog.setCancelable(false);
        //This dialog isn't indeterminate
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        if (getIntent().getExtras() != null) {
            URL = getIntent().getStringExtra("MyData");
        }
        getData();
    }

    public void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(BankName.this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.hide();
                JSONArray jsonArray = null;
                JSONObject jsonObject = null;
                try {
                    jsonObject = response.getJSONObject(0);
                    jsonArray = jsonObject.getJSONArray("PostOffice");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject postdata = jsonArray.getJSONObject(i);
                        String BranchName = postdata.getString("Name");
                        String BranchType = postdata.getString("BranchType");
                        String DeliveryStatus = postdata.getString("DeliveryStatus");
                        String District = postdata.getString("District");
                        String State = postdata.getString("State");
                        String Country = postdata.getString("Country");
                        String pincode = postdata.getString("Pincode");

                        arrayName.add(BranchName + "\n" + BranchType + "\n" + DeliveryStatus + "\n"
                                + District + "\n" + State + "\n" + Country + "\n" + pincode + "\n");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();


                }
                arrayAdapter = new ArrayAdapter<String>(BankName.this, android.R.layout.simple_list_item_1, arrayName);
                listView.setAdapter(arrayAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BankName.this, "Branch Not Found", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);

    }
}