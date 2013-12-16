package com.dev.uchesmsresponse;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

//All available column names in SMS table
// [_id, thread_id, address, 
// person, date, protocol, read, 
// status, type, reply_path_present, 
// subject, body, service_center, 
// locked, error_code, seen]

public class SmsReceiver extends BroadcastReceiver
{
	@Override
	 public void onReceive(Context context, Intent intent)
	{    
	     Bundle pudsBundle = intent.getExtras();
	     Object[] pdus = (Object[]) pudsBundle.get("pdus");
	     SmsMessage messages =SmsMessage.createFromPdu((byte[]) pdus[0]); 
	     String body = messages.getMessageBody().toString();
     	 String address = messages.getOriginatingAddress();
	     Toast.makeText(context, "SMS Received : "+body+" from: "+address,
	     Toast.LENGTH_LONG).show();
	 }
}