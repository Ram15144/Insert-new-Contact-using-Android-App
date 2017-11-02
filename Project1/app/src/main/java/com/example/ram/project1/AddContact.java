package com.example.ram.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ram_n on 9/23/2017.
 */

public class AddContact extends Activity implements OnClickListener {

    Button b;
    private static final String TAG = AddContact.class.getSimpleName();
    private EditText name;
    private TextView result;
    private String first_name,last_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        b=(Button)findViewById(R.id.addC);
        b.setOnClickListener(AddContact.this);

        name = (EditText) findViewById(R.id.first_name);
        result=(TextView) findViewById(R.id.result_text);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        name.setText("");
    }
    @Override
    public void onClick(View v) {

        if(validate())
        {
            /*Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

            intent.putExtra(ContactsContract.Intents.Insert.NAME, first_name + " " + last_name);
            intent.putExtra("finishActivityOnSaveCompleted", true);
            startActivityForResult(intent,0); */
            Intent i=new Intent();
            i.setAction(Intent.ACTION_INSERT);
            i.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            //i.putExtra(ContactsContract.Intents.Insert.NAME,first_name+" "+last_name);
            i.putExtra(ContactsContract.Intents.Insert.NAME,name.getText().toString());
            startActivityForResult(i,0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 0) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                result.setText("The contact was successfully added");
            }
            else if(resultCode == RESULT_CANCELED)
            {
                result.setText("The contact was not added");
            }
        }
    }

    public boolean validate() {
        String[] tokens = name.getText().toString().split(" ");
        if (name.getText().length() == 0) {

            String str = "Please enter First name and Last name";
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(tokens.length>=2)
        {
            return true;
        }
        else
        {
            Toast.makeText(getApplicationContext(), " Please enter at least the first and last names separated by a space ", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
