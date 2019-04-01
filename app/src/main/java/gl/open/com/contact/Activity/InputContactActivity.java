package gl.open.com.contact.Activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import gl.open.com.contact.Bean.Contact;
import gl.open.com.contact.Bean.ContactLab;
import gl.open.com.contact.R;
import gl.open.com.contact.utils.GetPhone;

public class InputContactActivity extends AppCompatActivity {
    private RecyclerView inputRecycle;
    private ArrayList<Contact> mContacts,mInputContacts = new ArrayList<>();
    private Button ensureInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_input_contact_recycle);
        inputRecycle = findViewById(R.id.inputContactRecycle);
        inputRecycle = findViewById(R.id.inputContactRecycle);
        RecyclerView.Adapter adapter = new Adapter();
        inputRecycle.setAdapter(adapter);
        inputRecycle.setLayoutManager(new LinearLayoutManager(this));
        mContacts = GetPhone.getPhone(this);
        adapter.notifyDataSetChanged();

        ensureInput =findViewById(R.id.ensureInput);
        ensureInput.setOnClickListener((v)->{
            for (int i = 0 ;i<mContacts.size();i++) {
                for (int j = 0; j < ContactLab.getContactLab().getContactArrayList().size(); j++) {
                    if (Objects.equals(mContacts.get(i).getName(), ContactLab.getContactLab().getContactArrayList().get(j).getName())) {
                        if (mContacts.get(i).getTelNumbel().equals(ContactLab.getContactLab().getContactArrayList().get(j).getTelNumbel())) {
                            mContacts.remove(i);
                            break;
                        }
                    }
                }
            }
            for(int i = 0;i<mContacts.size();i++)
            ContactLab.getContactLab().getContactArrayList().add(mContacts.get(i));
            finish();
        });

    }



    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
    {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View v = View.inflate(parent.getContext(),R.layout.activity_input_contact_recycle_item,null);
           return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.TelName.setText(mContacts.get(position).getName());
            holder.TelNum.setText(mContacts.get(position).getTelNumbel());
            holder.isSelected.setOnCheckedChangeListener((buttonView,isChecked)->{
               if(isChecked)
               {
                   mInputContacts.add(mContacts.get(position));
               }
               else
               {
                   mInputContacts.remove(mContacts.get(position));
               }
            });
        }

        @Override
        public int getItemCount() {
            return mContacts.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView TelName,TelNum;
            private CheckBox isSelected;

            public ViewHolder(View itemView) {
                super(itemView);
                TelName = itemView.findViewById(R.id.TelContactName);
                TelNum = itemView.findViewById(R.id.TelContactNum);
                isSelected = itemView.findViewById(R.id.isSelected);
            }
        }
    }


    }


