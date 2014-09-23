package de.hwr_berlin.lifepartner;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hwr_berlin.rp5000.R;


public class SmsActivity extends LifePartnerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        ArrayAdapter<Sms> adapter = new SmsAdapter(this, dummySmsList());
        ListView smsList = (ListView) findViewById(R.id.sms_list);
        smsList.setAdapter(adapter);
    }

    private ArrayList<Sms> dummySmsList() {
        ArrayList<Sms> items = new ArrayList<Sms>();
        items.add(new Sms("Charlotte Krüger", "Alles klar, dann treffen wir uns einfach um 16 Uhr ..."));
        items.add(new Sms("Gabriela von Lüdecke", "Hier ist das Rezept, das du haben wolltest: 500g ..."));
        items.add(new Sms("Michael Lock", "Was ich dir noch unbedingt zeigen wollte, ist das ..."));
        items.add(new Sms("Beate Frühling", "Es freut mich, dass die Karte angekommen ist ..."));
        items.add(new Sms("Christian Sauer", "Viel Spaß dann noch!"));
        return items;
    }
}

class Sms {
    private String contact;
    private String message;

    public Sms(String contact, String message) {
        super();
        this.contact = contact;
        this.message = message;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

class SmsAdapter extends ArrayAdapter<Sms> {

    private final Context context;
    private final ArrayList<Sms> itemsArrayList;

    public SmsAdapter(Context context, ArrayList<Sms> itemsArrayList) {
        super(context, R.layout.sms_list_row, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View SmsListRow = inflater.inflate(R.layout.sms_list_row, parent, false);
        TextView contactView = (TextView) SmsListRow.findViewById(R.id.sms_contact);
        TextView messageView = (TextView) SmsListRow.findViewById(R.id.sms_message);

        contactView.setText(itemsArrayList.get(position).getContact());
        messageView.setText(itemsArrayList.get(position).getMessage());

        return SmsListRow;
    }
}
