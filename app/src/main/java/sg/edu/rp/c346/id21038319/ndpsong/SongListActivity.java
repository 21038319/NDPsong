package sg.edu.rp.c346.id21038319.ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SongListActivity extends AppCompatActivity {


    ArrayList<Songs> al;
    ArrayList<Songs> alYear;
    Set<Songs> alYearWithoutDuplicates;
    ArrayList<String> alYearString;
    // ArrayAdapter<Songs> aa;
    ArrayAdapter<Songs> aaYear;
    Spinner year;
    ListView lv;
    ToggleButton tbStar;
    CustomAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbh = new DBHelper(SongListActivity.this);
        al.clear();
        al.addAll(dbh.getAllSongs());

        adapter.notifyDataSetChanged();

        year = findViewById(R.id.yearFilter);

        alYear = dbh.getAllYears();
//        alYearWithoutDuplicates = new LinkedHashSet<>(alYear);
//        alYearWithoutDuplicates.addAll(alYear);
//        alYear.clear();
//        alYear.addAll(alYearWithoutDuplicates);
        aaYear = new ArrayAdapter(this,android.R.layout.simple_spinner_item,alYear);
        aaYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(aaYear);

        aaYear.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        lv = findViewById(R.id.lv);
        year = findViewById(R.id.yearFilter);
        tbStar = findViewById(R.id.tbFiveStars);

        al = new ArrayList<Songs>();
        // aa = new ArrayAdapter<Songs>(this,
               // android.R.layout.simple_list_item_1, al);
        adapter = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(adapter);
        // lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Songs data = al.get(position);
                Intent i = new Intent(SongListActivity.this,
                        EditActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String filterText = year.getSelectedItem().toString();

                al.clear();
                DBHelper dbh = new DBHelper(SongListActivity.this);
                al.addAll(dbh.getAllSongs(filterText));

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                al.clear();
                DBHelper dbh = new DBHelper(SongListActivity.this);
                al.addAll(dbh.getAllSongs());

                adapter.notifyDataSetChanged();
            }
        });

        tbStar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                String filterText = "5";

                DBHelper dbh = new DBHelper(SongListActivity.this);

                al.clear();
                if(checked){
                    al.addAll(dbh.getAllFiveStar(filterText));
                }else{
                    al.addAll(dbh.getAllSongs());
                }

                adapter.notifyDataSetChanged();
            }
        });
    }
}