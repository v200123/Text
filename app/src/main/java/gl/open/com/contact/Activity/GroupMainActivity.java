package gl.open.com.contact.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gl.open.com.contact.Bean.Contact;
import gl.open.com.contact.Bean.ContactLab;
import gl.open.com.contact.Bean.Group;
import gl.open.com.contact.Bean.GroupLab;
import gl.open.com.contact.MainActivity;
import gl.open.com.contact.R;

/**
 * Created by Zhu TingYu on 2019/4/1.
 */

public class GroupMainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private String TAG = "dfsdfsdfsdf";
    private RecyclerView contactRecycleView;
    private Button addContactBtn;
    private RecyclerView.Adapter adapter;
    private int postion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_main_recycle_view);
        postion = getIntent().getIntExtra(MainActivity.EXTRA_TITLE,0);
        toolbar = findViewById(R.id.groupToolBar);
        setSupportActionBar(toolbar);
        addContactBtn = findViewById(R.id.addContactBtn);

        addContactBtn.setOnClickListener((v)->{
            Intent i = new Intent(this,addContactActivity.class);
            startActivity(i);
        });

        Group group = GroupLab.getGroupLab().getContactArrayList().get(postion);
        toolbar.setTitle(group.getGroupName());
        contactRecycleView = findViewById(R.id.Group_Contact_Recycle);
        adapter = new ViewGroupHolder(ContactLab.getContactLab().getContactArrayList());
        contactRecycleView.setAdapter(adapter);
        contactRecycleView.setLayoutManager(new LinearLayoutManager(this));
        requestAllPower();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "视图更新", Toast.LENGTH_SHORT).show();
    }

    //菜单点击事件
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.group_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.inputContact)
        {

            Intent i = new Intent(GroupMainActivity.this,InputContactActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    class ViewGroupHolder extends RecyclerView.Adapter<ViewGroupHolder.ViewHolder>
    {
        private ArrayList<Contact> mContacts = new ArrayList<>();

        ViewGroupHolder(ArrayList<Contact> contacts) {
            for(int i = 0;i<contacts.size();i++)
            {
                if(contacts.get(i).getId()==postion)
                {
                    mContacts.add(contacts.get(i));
                }
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = View.inflate(parent.getContext(),R.layout.group_main_recycle_item,null);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.contactTelNum.setText( mContacts.get(position).getTelNumbel());
            holder.contactName.setText(mContacts.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return mContacts.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView contactName,contactTelNum;
            public ViewHolder(View itemView) {
                super(itemView);
                contactName = itemView.findViewById(R.id.ContactName);
                contactTelNum = itemView.findViewById(R.id.ContactTelNum);
            }
        }
    }

    public void requestAllPower() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS}, 1);
            }
        }
    }
}
