package gl.open.com.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gl.open.com.contact.Activity.GroupMainActivity;
import gl.open.com.contact.Bean.Group;
import gl.open.com.contact.Bean.GroupLab;

public class MainActivity extends AppCompatActivity {
    private RecyclerView groupRecycle;
    private RecyclerView.Adapter adapter;
    public static final String EXTRA_TITLE = "com.lc.GroupTitle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        GroupLab.getGroupLab().addGroup("好友",1);
         adapter= new ViewAdapter(GroupLab.getGroupLab().getContactArrayList());
        groupRecycle = (RecyclerView) findViewById(R.id.GroupRecycle);
        groupRecycle.setAdapter(adapter);
        groupRecycle.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.addContanct)
        {
            GroupLab.getGroupLab().addGroup("分组"+(GroupLab.getGroupLab().getContactArrayList().size()+1),
                    GroupLab.getGroupLab().getContactArrayList().size()+1);

            Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }

    class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {
        private int position;
        public ViewAdapter(ArrayList<Group> contacts) {
            mGroups = contacts;
        }

        private ArrayList<Group> mGroups;//分组的列表集合

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = View.inflate(parent.getContext(),R.layout.group_recycle_item,null);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.groupNumTextView.setText(mGroups.get(position).getGroupName());
            holder.groupNumTextView.setOnClickListener((view)->{
                    Intent i = new Intent(MainActivity.this, GroupMainActivity.class);
                    i.putExtra(EXTRA_TITLE,position);
                    Toast.makeText(getApplication(),position+"",Toast.LENGTH_SHORT).show();
                    startActivity(i);//跳转到分组的详细列表
            });
        }

        @Override
        public int getItemCount() {
            return mGroups.size();
        }



        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView groupNumTextView;
            public ViewHolder(View itemView) {
                super(itemView);
                groupNumTextView = itemView.findViewById(R.id.GroupNumText);

            }


        }
    }
}
