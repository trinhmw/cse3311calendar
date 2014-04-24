package com.example.cse3311_calendar;

import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import android.os.Bundle;

public class NewGroupBroadcast extends BroadcastReceiver {

	final SmsManager sms = SmsManager.getDefault();
	EventListManager elm;
	
    public void onReceive(Context context, Intent intent) {
     
       final Bundle bundle = intent.getExtras();
 
        try {
             
            if (bundle != null) {
                 
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                 
                for (int i = 0; i < pdusObj.length; i++) {
                     
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                     
                    
                    String message = currentMessage.getDisplayMessageBody();
                    

 
                    //Log.i("SmsReceiver", "senderNum: "+ phoneNumber + "; message: " + message);
                     
                    String newEvent = message.substring(0,1);//new group event or replying
	                if(newEvent.compareTo("0") == 0 || newEvent.compareTo("1") == 0 || newEvent.compareTo("2") == 0 )
	                {
	                	if(newEvent.compareTo("0") == 0)
	                	{
	                		String eventDate = message.substring(1,11);//date
		                    int year = Integer.parseInt(message.substring(7,11));
		                    int month =Integer.parseInt(message.substring(1,3));
		                    month --;
		                    int day = Integer.parseInt(message.substring(4,6));
		                    Date formattedDate = new Date(year, month, day);
		                    String eventName = message.substring(11);
		                    GroupEventController.replyToRequest(context,eventDate, formattedDate.toString(),eventName,phoneNumber);
		                    //Intent newIntent = new Intent(context, showDialog.class); 
		                    //newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		                    //context.startActivity(newIntent);
		                    
	                	}
	                	else if(newEvent.compareTo("1") == 0)
	                	{
	                		String eventDate = message.substring(1,11);//date
		                    int year = Integer.parseInt(message.substring(7,11));
		                    int month =Integer.parseInt(message.substring(1,3));
		                    month--;
		                    int day = Integer.parseInt(message.substring(4,6));
		                    Date formattedDate = new Date(year, month, day);
		                    String dayBinary = message.substring(11,35);
		                    String eventName = message.substring(35);
		                    phoneNumber = phoneNumber.substring(7);
	                		GroupEventController.repliedToRequest(context,eventDate,formattedDate.toString(), eventName, phoneNumber, dayBinary);
	                	}
	                	else if(newEvent.compareTo("2") == 0)
	                	{
	                		
	                	}
	                	
	                }
                } // end for loop
              } // bundle is null
 
        } catch (Exception e) { 
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
             
        }
    }    
	
}

