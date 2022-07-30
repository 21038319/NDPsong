package sg.edu.rp.c346.id21038319.ndpsong;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    TextView id;
    EditText etId, etSongTitle, etSinger, etYear;
    RadioGroup radioGroup;
    RadioButton rating, star1, star2, star3, star4, star5;
    Button btnUpdate, btnDelete, btnCancel;
    Songs data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        id = findViewById(R.id.textView);
        etId = findViewById(R.id.etId);
        etSongTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYears);
        radioGroup = findViewById(R.id.radioRating);
        star1 = findViewById(R.id.radioButtonStar1);
        star2 = findViewById(R.id.radioButtonStar2);
        star3 = findViewById(R.id.radioButtonStar3);
        star4 = findViewById(R.id.radioButtonStar4);
        star5 = findViewById(R.id.radioButtonStar5);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Songs) i.getSerializableExtra("data");


        etId.setText("ID: " + data.get_id());
        etSongTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        String editYear = Integer.toString(data.getYear());
        etYear.setText(editYear);
        int stars = data.getStars();
        if(stars == 1){
            star1.setChecked(true);
        }else if(stars == 2) {
            star2.setChecked(true);
        }else if(stars == 3) {
            star3.setChecked(true);
        }else if(stars == 4) {
            star4.setChecked(true);
        }else if(stars == 5) {
            star5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setTitle(etSongTitle.getText().toString());
                dbh.updateSongs(data);

                data.setSingers(etSinger.getText().toString());
                dbh.updateSongs(data);

                int conetYear = parseInt(etYear.getText().toString());
                data.setYear(conetYear);
                dbh.updateSongs(data);

                int radioId = radioGroup.getCheckedRadioButtonId();
                rating = findViewById(radioId);
                int conrating = parseInt(rating.getText().toString());
                data.setStars(conrating);
                dbh.updateSongs(data);

                dbh.close();

                Intent i = new Intent(EditActivity.this,
                        SongListActivity.class);
                startActivity(i);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteNote(data.get_id());

                Intent i = new Intent(EditActivity.this,
                        SongListActivity.class);
                startActivity(i);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditActivity.this,
                        SongListActivity.class);
                startActivity(i);
            }
        });

    }


}