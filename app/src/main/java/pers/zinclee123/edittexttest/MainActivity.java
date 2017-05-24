package pers.zinclee123.edittexttest;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pers.zinclee123.edittext.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2, et3, et4;

    Button setColorBtn, setResBtn, setDrawableBtn, setShowBtn;

    boolean show = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et_test1);
//        et2 = (EditText) findViewById(R.id.et_test2);
//        et3 = (EditText) findViewById(R.id.et_test3);
//        et4 = (EditText) findViewById(R.id.et_test4);

        setColorBtn = (Button) findViewById(R.id.btn_set_color);
        setColorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setClearIconColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
            }
        });

        setResBtn = (Button) findViewById(R.id.btn_set_res);
        setResBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setClearIcon(R.mipmap.custom_clear_text_icon);
            }
        });

        setDrawableBtn = (Button) findViewById(R.id.btn_set_drawable);
        setDrawableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable d = ContextCompat.getDrawable(MainActivity.this, R.mipmap.custom_clear_text_icon);
                DrawableCompat.setTint(d, Color.parseColor("#ff85a1"));
                et1.setClearIcon(d);
            }
        });


        setShowBtn = (Button) findViewById(R.id.btn_set_show);
        setShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show = !show;
                et1.setShowClearIcon(show);
            }
        });

    }
}
