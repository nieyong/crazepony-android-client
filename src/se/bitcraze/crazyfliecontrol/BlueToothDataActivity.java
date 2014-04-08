package se.bitcraze.crazyfliecontrol;


import java.util.ArrayList;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class BlueToothDataActivity extends Activity {
	
	private static final String TAG = "BlueToothDataActivity";
	
	private ToggleButton togglebutton;
	private TextView mDataTextView;
	private MsgReceiver msgReceiver;
	
	private ArrayList<String> btDataArrayList;
	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluetooth_data);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
		
		
        //动态注册广播接收器  
        msgReceiver = new MsgReceiver();  
        IntentFilter intentFilter = new IntentFilter();  
        intentFilter.addAction("com.crazepony.communication.RECEIVER");  
        registerReceiver(msgReceiver, intentFilter);  
        
        btDataArrayList = new ArrayList<String>();
        mDataTextView = (TextView) findViewById(R.id.dataTextView);
        mDataTextView.setText("");

		togglebutton = (ToggleButton) findViewById(R.id.bluetoothButton);
		togglebutton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 当按钮第一次被点击时候响应的事件
				if (togglebutton.isChecked()) {
				}
				// 当按钮再次被点击时候响应的事件
				else {
				}
			}
		});

	}

    @SuppressWarnings("deprecation")
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            break;
        }
        return true;
    }
    
    /** 
     * 广播接收器 
     */  
    public class MsgReceiver extends BroadcastReceiver{  
  
        @Override  
        public void onReceive(Context context, Intent intent) {  
            //拿到进度，更新UI  
            String data = intent.getStringExtra("data");
            Log.v(TAG,data);
            
            if (btDataArrayList.size() > 8) {
				btDataArrayList.remove(0);
			}
            btDataArrayList.add(data);
            
            String dataString = "";
            for(int i=0;i<btDataArrayList.size();i++)
            {
            	dataString = dataString + "\n" + (String)btDataArrayList.get(i);
            }
            
            mDataTextView.setText(dataString);
        }  
          
    }  
}


