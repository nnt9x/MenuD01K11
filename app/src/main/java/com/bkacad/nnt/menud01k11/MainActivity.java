package com.bkacad.nnt.menud01k11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnShowPopupMenu;
    private PopupMenu popupMenu;
    private TextView tvTitle;
    private ListView lvTodo;
    private List<String> dataTodo;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShowPopupMenu = findViewById(R.id.btnShowPopupMenu);
        tvTitle = findViewById(R.id.tvTitle);

        // Tạo sẵn popip menu trước
        popupMenu = new PopupMenu(this, tvTitle);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        // Sự kiện khi click vào button
        btnShowPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị popup lên
                popupMenu.show();
            }
        });
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.popup_menu_action1:
                        Toast.makeText(MainActivity.this,"Action 1",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.popup_menu_action2:
                        Toast.makeText(MainActivity.this,"Action 2",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        // Tạo listview
        lvTodo = findViewById(R.id.lvTodo);

        dataTodo = new ArrayList<>();
        dataTodo.add("1. Đi làm");
        dataTodo.add("2. Đi chợ");
        dataTodo.add("3. Kiểm tra");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dataTodo);

        lvTodo.setAdapter(adapter);

        registerForContextMenu(lvTodo);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.context_menu_add_new:
                // Hiển thị 1 dialog lên -> tự làm
                Toast.makeText(MainActivity.this,"Hiển thị dialog nhập",Toast.LENGTH_SHORT).show();
                break;
            case R.id.context_menu_count:
                Toast.makeText(MainActivity.this,"Số lượng: "+dataTodo.size(),Toast.LENGTH_SHORT).show();

                break;
        }
        return super.onContextItemSelected(item);
    }

    // Ctr + O

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Xử lý sự kiện khi click vào từng item menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_menu_settings:
                Toast.makeText(MainActivity.this,"Mở cài đặt", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option_menu_abouts:
                // Có thể thay bằng nhiều tác vụ khác
                Toast.makeText(MainActivity.this,"Abouts",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}