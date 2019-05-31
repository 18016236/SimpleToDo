package sg.edu.rp.c346.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText editName;;
Button btnAdd,btndestroy,btnclear;
ListView lvstuff;
ArrayAdapter adapter;
Spinner spnAddRemove;
ArrayList<String>al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnAddRemove=findViewById(R.id.spinner);
        lvstuff=findViewById(R.id.listStuff);
        btnAdd=findViewById(R.id.button);
        btndestroy=findViewById(R.id.button2);
        editName=findViewById(R.id.editName);
        btnclear=findViewById(R.id.button3);

        al=new ArrayList<String>();

        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,al);
        lvstuff.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=editName.getText().toString();
                al.add(text);
                adapter.notifyDataSetChanged();
                editName.setText(" ");


            }

        });
        btnclear.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (editName.getText().toString().equals("")) {
                                                Toast.makeText(MainActivity.this,"You don't have any tasks to remove",Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                            int pos = Integer.parseInt(editName.getText().toString());
                                            if (pos > al.size()) {
                                                Toast.makeText(MainActivity.this,"Wrong index number",Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                            al.remove(pos);
                                            adapter.notifyDataSetChanged();
                                            editName.setText("");
                                        }
                                    });

        btndestroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                al.clear();
                adapter.notifyDataSetChanged();
                editName.setText("");
            }
        });
        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnAdd.setEnabled(true);
                        btndestroy.setEnabled(false);
                        editName.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btndestroy.setEnabled(true);
                        editName.setHint("Type in the index of the task to be removed");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?>parent){
            }
        });


    }
}
