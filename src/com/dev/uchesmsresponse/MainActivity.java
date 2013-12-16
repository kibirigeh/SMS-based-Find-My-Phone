package com.dev.uchesmsresponse;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity
{
	
	ArrayList<Receipient> receipients;
	ArrayList<String> commands;
	 Dialog dialog;
	 Button dialogButton,quitButton;
	 Typeface font;
	 EditText NameR,PhoneR;
	 ListView receipientList;
	 Context handle;
	 MyAdapter adapter;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	/*data structures to hold the receipients and the list of accepted commands*/
    	receipients = new ArrayList<Receipient>();
    	commands=new ArrayList<String>();
    	
        super.onCreate(savedInstanceState);
        setTheme( android.R.style.Theme_Light );
        handle=this;
       
        /*Register intent for listening to SMS*/
        IntentFilter filter = new IntentFilter( "android.provider.Telephony.SMS_RECEIVED" );
        filter.setPriority( IntentFilter.SYSTEM_HIGH_PRIORITY );
        registerReceiver( new SmsReceiver(), filter );
        
        //init dialog
        dialog = new Dialog(this);
        initDialogs();
        
        
        adapter=new MyAdapter(this,R.layout.spinnerlayout);
        receipientList = (ListView) findViewById( R.id.ReceipientsList );
        receipientList.setAdapter(adapter);
        setContentView(R.layout.activity_main);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void getReceipient(View view)
    {
    	dialog.show();
    }
    
    public void removeReceipient(View view)
    {
    	
    }
    
    public void toggleApp(View view)
    {
    	
    }
    
    public void exit(View view)
    {
    	
    }
    
    public void addCommand(View view)
    {
    	
    }
    
    public void initDialogs()
    {
    	dialog.setContentView(R.layout.dialog_receipient);
		dialog.setTitle("Add Receipient");
		font = Typeface.createFromAsset(getAssets(),"fonts/textFont.ttf");
		dialogButton = (Button) dialog.findViewById(R.id.addD);
		quitButton = (Button) dialog.findViewById(R.id.cancelD);
		NameR=(EditText)dialog.findViewById(R.id.NameR);
		PhoneR=(EditText)dialog.findViewById(R.id.PhoneR);
		dialogButton.setTypeface(font);
		dialogButton.getBackground().setAlpha(50);//set opacity of background
		
		dialogButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				dialog.dismiss();
			}
		});
		
		dialogButton.setOnClickListener(new OnClickListener() 
		{
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) 
			{
				if((!NameR.getText().toString().isEmpty())&&(!NameR.getText().toString().isEmpty()))
				{
					receipients.add(new Receipient(NameR.getText().toString(),PhoneR.getText().toString()));
					 Toast.makeText(MainActivity.this,NameR.getText().toString()+" added to Receipients.", Toast.LENGTH_LONG ).show();
				}
				dialog.dismiss();
			}
		});
		
		quitButton.setTypeface(font);
		quitButton.getBackground().setAlpha(50);//set opacity of background
		quitButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				dialog.dismiss();
			}
		});
		dialog.setCancelable(false);
    }
    
    class CustomItemSelectedListener implements OnItemSelectedListener 
    {
     
      public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) 
      {
    	  Receipient temp=( Receipient) parent.getItemAtPosition(pos);
    	  Toast.makeText(MainActivity.this,temp.getName()+" selected..", Toast.LENGTH_LONG ).show();
    	  
      }
     
      public void onNothingSelected(AdapterView<?> arg0) 
      {
    	  Toast.makeText(MainActivity.this,"Nothing selected..", Toast.LENGTH_LONG ).show();
      } 
    }
}
