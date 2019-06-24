package ca.jeonghoon.phonebook.sms_receiver;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.Optional;

import ca.jeonghoon.phonebook.R;
import ca.jeonghoon.phonebook.custom_toast.ToastBuilder;
import ca.jeonghoon.phonebook.model.Person;
import ca.jeonghoon.phonebook.model.PersonCollection;

public class MySmsReceiver extends BroadcastReceiver {

    private static final String TAG = MySmsReceiver.class.getSimpleName();

    public static final String pdu_type = "pdus";

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the SMS message.
        Bundle bundle = intent.getExtras();

        SmsMessage[] msgs;
        String strMessage = "";
        String format = bundle.getString("format");

        // Retrieve the SMS message received.
        Object[] pdus = (Object[]) bundle.get(pdu_type);
        if (pdus != null) {
            // Fill the msgs array.
            msgs = new SmsMessage[pdus.length];

            for (int i = 0; i < msgs.length; i++) {
                // Check Android version and use appropriate createFromPdu.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    // If Android version M or newer:
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    // If Android version L or older:
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }

                // searching received number is in the list
                String receivedNumber = msgs[i].getOriginatingAddress();
                Optional<Person> registeredPerson = PersonCollection.getPersonList().stream()
                        .filter(person -> person.getPhone().equals(receivedNumber))
                        .findFirst();

                if (registeredPerson.isPresent()) {
                    // receiving message from registered person in the Phone book list
                    Person person = registeredPerson.get();
                    ToastBuilder.displayImageToast(context,
                            "SMS from " + person.getFullName(),
                            person.getImageResourceId(),
                            Toast.LENGTH_LONG).show();
                } else {
                    // not registered person
                    ToastBuilder.displayImageToast(context,
                            "SMS from " + receivedNumber,
                            R.drawable.no_photo_available,
                            Toast.LENGTH_LONG).show();
                }

                // for testing
                // Build the message to show.
                strMessage += "SMS from " + receivedNumber;
                strMessage += " :" + msgs[i].getMessageBody() + "\n";

                // Log and display the SMS message.
                Log.d(TAG, "onReceive: " + strMessage);
            }
        }
    }
}
