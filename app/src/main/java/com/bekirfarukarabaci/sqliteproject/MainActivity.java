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
            //Otomatik ID eklemek için name öncesine "id INTEGER PRIMARY KEY" eklenebilir
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY,name VARCHAR,age INT)");

            //Tabloya veri eklemek
            //Bir kere eklendikten sonra yorum satırına aldım ki bir daha aynı veriyi eklemesin
            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('James' , 50)");
            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('Lars' , 30)");
            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('Berkay' , 20)");

            //Güncelleme işlemleri
            //database.execSQL("UPDATE musicians SET age = 32 WHERE name = 'Lars'");
            //database.execSQL("UPDATE musicians SET name = 'Berkay Çakır' WHERE id = 3");

            //Silme işlemleri
            //database.execSQL("DELETE FROM musicians Where id = 2");

            //Cursor oluşturmak ve tablodaki her şeyi çekmek
            Cursor cursor = database.rawQuery("SELECT * FROM musicians",null);

            //Filtreleme işlemi "WHERE" ile yapılır
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name = 'James'",null);
            //Filtreleme sonu 's' ile bitenleri seçmek için "%s" LIKE ile kullanırız
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE '%s'",null);
            //Filtreleme 'B' ile başlayanları seçmek için "B%" LIKE ile kullanırız
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'B%'",null);





            //Cursorun hangi coloumn ve row a gideceğini söylemek
            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int IDIx = cursor.getColumnIndex("id");

            while (cursor.moveToNext()){
                System.out.println("Name : " + cursor.getString(nameIx));
                System.out.println("Age : " + cursor.getInt(ageIx));
                System.out.println("ID : " + cursor.getInt(IDIx));
            }

            cursor.close();






        }
        catch (Exception exception){
            exception.printStackTrace();
        }







    }
}