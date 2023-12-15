package com.bekirfarukarabaci.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            //Database oluşturmak
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            //Tablo oluşturmak
            //Otomatik ID eklemek için name öncesine "id INT PRIMARY KEY," eklenebilir
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (name VARCHAR,age INT)");
            //Tabloya veri eklemek
            //Bir kere eklendikten sonra yorum satırına aldım ki bir daha aynı veriyi eklemesin
            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('James' , 50)");
            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('Lars' , 30)");
            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('Berkay' , 20)");
            //Cursor oluşturmak
            Cursor cursor = database.rawQuery("SELECT * FROM musicians",null);
            //Cursorun hangi coloumn ve row a gideceğini söylemek
            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");

            while (cursor.moveToNext()){
                System.out.println("Name : " + cursor.getString(nameIx));
                System.out.println("Age : " + cursor.getString(ageIx));
            }

            cursor.close();






        }
        catch (Exception exception){
            exception.printStackTrace();
        }







    }
}