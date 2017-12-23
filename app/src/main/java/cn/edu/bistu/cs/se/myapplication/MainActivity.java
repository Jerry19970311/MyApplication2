package cn.edu.bistu.cs.se.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.bistu.cs.se.myapplication.words.WordsContent;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener,DetailFragment.OnFragmentInteractionListener,ItemFragment.OnListFragmentInteractionListener{
    MyService myService=null;
    Context context;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0&&resultCode==0){
            String str=data.getStringExtra("result");
            Toast.makeText(this,str,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*TextView textView=(TextView)findViewById(R.id.textview);
        registerForContextMenu(textView);*/
        context = this;

        final Button btnON=(Button)findViewById(R.id.ButtonOK);
        btnON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txt=(EditText)findViewById(R.id.eText);
                txt.setText("Sharon");
            }
        });
        /*final TextView txtResult=(TextView)findViewById*/
        //btnON.setClickable(false);
        Button btnTe=(Button)findViewById(R.id.ButtonTest);
        btnTe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                /*AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Login");
                alertDialog.setContentView(R.layout.login_dialog);
                alertDialog.show();*/

                //Button button = (Button) alertDialog.findViewById();

                final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);

                final LayoutInflater inflater=getLayoutInflater();
                final View view1=inflater.inflate(R.layout.login_dialog,null);
                view1.dispatchWindowFocusChanged(true);
                builder.setView(view1).setTitle("Login");
                builder.setPositiveButton(R.string.button_login, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText text1= (EditText) view1.findViewById(R.id.editTextUserId);
                        //String str1=text1.getText().toString();
                        EditText text2=(EditText)view1.findViewById(R.id.editTextPwd);
                        //String str2=text2.getText().toString();
                        /*boolean result1=(str1.equals("abc"));
                        boolean result2=(str2.equals("123"));
                        boolean result=result1&&result2;*/
                        if(text1.getText().toString().equals("abc")&&text2.getText().toString().equals("123")){
                            Toast.makeText(builder.getContext(),"Successful Logining!",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(builder.getContext(),"Fail Logining!",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                        .setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                builder.setCancelable(false);
                builder.show();
            }
        });
        Button buttonStart=(Button)findViewById(R.id.ButtonStartService);
        Button buttonStop=(Button)findViewById(R.id.ButtonStopService);
        buttonStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MyService.class);
                intent.putExtra("num",10);
                startService(intent);
            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MyService.class);
                stopService(intent);
            }
        });
        Button buttonAnotherActivity=(Button)findViewById(R.id.ButtonAnotherActivity);
        buttonAnotherActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction("anotherActivity");
                intent.putExtra("name","Zhangsan");
                intent.putExtra("age",20);
                startActivityForResult(intent,0);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        switch (id){
            case R.id.action_settings:
                Toast.makeText(this,"settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_another:
                Toast.makeText(this,"another",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void showPopup(View v){
        PopupMenu popup=new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu_popup);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this,"Settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_another:
                Toast.makeText(this,"another",Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    @Override
    public void onListFragmentInteraction(WordsContent.WordItem item) {
        Bundle arguments=new Bundle();
        arguments.putString("id",item.id);
        DetailFragment fragment=new DetailFragment();
        fragment.setArguments(arguments);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.wordmeaning,fragment)
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
