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
        items.add(new Sms("Charlotte Krüger",
                "Alles klar, dann treffen wir uns einfach um 16 Uhr ...", 0));
        items.add(new Sms("Gabriela von Lüdecke",
                "Hier ist das Rezept, das du haben wolltest: 500g ...", 2));
        items.add(new Sms("Michael Lock",
                "Was ich dir noch unbedingt zeigen wollte, " + "ist das ...", 1));
        items.add(new Sms("Beate Frühling", "Es freut mich, dass die Karte angekommen ist ...", 1));
        items.add(new Sms("Christian Sauer", "Viel Spaß dann noch!", 2));
        return items;
    }
}

class Sms {
    private String contact;
    private String message;
    // 0 = new, 1 = in, 2 = out
    private int state;

    public Sms(String contact, String message, int state) {
        super();
        this.contact = contact;
        this.message = message;
        this.state = state;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        View SmsListRow = inflater.inflate(R.layout.sms_list_row, parent, false);
        TextView contactView = (TextView) SmsListRow.findViewById(R.id.sms_contact);
        TextView messageView = (TextView) SmsListRow.findViewById(R.id.sms_message);

        contactView.setText(itemsArrayList.get(position).getContact());
        messageView.setText(itemsArrayList.get(position).getMessage());

        switch (itemsArrayList.get(position).getState()) {
            case 0:
                messageView.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(
                        R.drawable.arrow_right_small), null, null, null);
                break;
            case 1:
                messageView.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(
                        R.drawable.arrow_right_small), null, null, null);
                break;
            case 2:
                messageView.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(
                        R.drawable.arrow_left_small), null, null, null);
                break;
            default:
                messageView.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(
                        R.drawable.arrow_right_small), null, null, null);
        }

        return SmsListRow;
    }
}
