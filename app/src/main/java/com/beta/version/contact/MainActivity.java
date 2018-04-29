package com.beta.version.contact;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends Activity implements OnItemClickListener {

    private DataBaseContacts dataBaseContacts;

    List<Contact> lists;
    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TypedArray profile_pics = getResources().obtainTypedArray(R.array.profile_pics);

        ContactController contactController = new ContactController(this);

        //Création d'un contact
        //Contact contact = new Contact("abcd55", "Programme", "05565");

        contactController.open();

            //contactController.insertContact(contact);
            lists = contactController.selectAllFromTable();

            if(lists == null) {
                Toast.makeText(this, "list des Contacts est vide ..!", Toast.LENGTH_LONG).show();
            } else {
                mylistview = (ListView) findViewById(R.id.list);
            for (Contact c : lists)
                Toast.makeText(this, "list"+c.toString() , Toast.LENGTH_LONG).show();

                CustomAdapter adapter = new CustomAdapter(this, lists);
                mylistview.setAdapter(adapter);
                mylistview.setOnItemClickListener(this);
            }
        contactController.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        String fullname = lists.get(position).getFullname();
        Toast.makeText(getApplicationContext(), "" + fullname,
                Toast.LENGTH_SHORT).show();
    }

}
