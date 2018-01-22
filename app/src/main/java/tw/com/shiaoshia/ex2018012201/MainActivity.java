package tw.com.shiaoshia.ex2018012201;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click01(View v) {
        File dbFile = new File(getFilesDir(),"student.db");
        InputStream is = getResources().openRawResource(R.raw.student);
        try {
            OutputStream os = new FileOutputStream(dbFile);
            int r;
            while((r = is.read()) != -1) {
                os.write(r);
            }
            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void click02(View v) {
        File dbFile = new File(getFilesDir(),"student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        Cursor c = db.rawQuery("Select * from students",null);  //設定指標位置
        c.moveToFirst();    //將指標移至第一筆資料
        Log.d("DB",c.getString(1) + "," + c.getInt(2));
        c.moveToNext();     //將指標移至下一筆資料
        Log.d("DB",c.getString(1) + "," + c.getInt(2));
    }
}
