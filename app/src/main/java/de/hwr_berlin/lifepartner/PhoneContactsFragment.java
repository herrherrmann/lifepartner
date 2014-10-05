package de.hwr_berlin.lifepartner;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hwr_berlin.rp5000.R;

public class PhoneContactsFragment extends ListFragment {

    private OnFragmentInteractionListener mListener;

    public static PhoneContactsFragment newInstance() {
        return new PhoneContactsFragment();
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PhoneContactsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new PhoneContactsAdapter(getActivity(), dummyPhoneList()));
    }

    private ArrayList<PhoneContact> dummyPhoneList() {
        ArrayList<PhoneContact> items = new ArrayList<PhoneContact>();
        items.add(new PhoneContact("Alfred Kowalke", "0130 3355631"));
        items.add(new PhoneContact("Beate Frühling", "0130 3355631"));
        items.add(new PhoneContact("Charlotte Krüger", "0130 3355631"));
        items.add(new PhoneContact("Christian Sauer", "0130 3355631"));
        items.add(new PhoneContact("Gabriela von Lüdecke", "0130 3355631"));
        items.add(new PhoneContact("Michael Lock", "0130 3355631"));
        items.add(new PhoneContact("Nathan Weiß", "0130 3355631"));
        items.add(new PhoneContact("Süheyla Sühü", "0130 3355631"));
        items.add(new PhoneContact("Thomas Falk", "0130 3355631"));
        items.add(new PhoneContact("Zander Fisch", "0130 3355631"));
        return items;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}

class PhoneContact {
    private String contact;
    private String number;

    public PhoneContact(String contact, String number) {
        super();
        this.contact = contact;
        this.number = number;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

class PhoneContactsAdapter extends ArrayAdapter<PhoneContact> {

    private final Context context;
    private final ArrayList<PhoneContact> itemsArrayList;

    public PhoneContactsAdapter(Context context, ArrayList<PhoneContact> itemsArrayList) {
        super(context, R.layout.phone_list_row, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View PhoneListRow = inflater.inflate(R.layout.phone_list_row, parent, false);
        TextView contactView = (TextView) PhoneListRow.findViewById(R.id.phone_contact);
        TextView numberView = (TextView) PhoneListRow.findViewById(R.id.phone_number);

        contactView.setText(itemsArrayList.get(position).getContact());
        numberView.setText(itemsArrayList.get(position).getNumber());

        return PhoneListRow;
    }
}