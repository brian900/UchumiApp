package com.wex.uchumiapp;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class UchumiMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uchumi_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.uchumi_main, menu);
        return true;
    }
    public void pull(View v){
    	String url="http://10.0.2.2/uchumi/stock.php";
    	
    	AsyncHttpClient client= new AsyncHttpClient();
    	client.get(url, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				// TODO Auto-generated method stub
				String data=new String();
				try {
					JSONArray array=new JSONArray(data);
				for(int x=0;x<array.length();x++){
					
					JSONObject obj=array.getJSONObject(x);
					String Id=obj.getString("Id");
				    String Name=obj.getString("Name");
				    String Price=obj.getString("Price");
				    String Quantity=obj.getString("Quantity");
				    String Expiry=obj.getString("expiry");
				    Log.d("DATA",Id+":"+Name+":"+Price+":"+Quantity+":"+Expiry);	
			}
			
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Log.e("DATA","Error with JSON");
				}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				Log.e("DATA","Failed To fetch");
			}
		});
    } 
}
