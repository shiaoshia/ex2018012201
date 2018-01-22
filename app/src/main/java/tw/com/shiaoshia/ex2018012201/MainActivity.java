package tw.com.shiaoshia.ex2018012201;

import android.content.ContentValues;
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

    //需將res裡的資料庫傳到File資料夾裡才可以使用
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

    //將資料顯示出來
    public void click02(View v) {
        File dbFile = new File(getFilesDir(),"student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        Cursor c = db.rawQuery("Select * from students",null);  //設定指標位置
        c.moveToFirst();    //將指標移至第一筆資料
        Log.d("DB",c.getString(1) + "," + c.getInt(2));
        while (c.moveToNext()) {
            Log.d("DB",c.getString(1) + "," + c.getInt(2));
        }
        c.close();
        db.close();
    }

    //各種select的用法
    public void click03(View v) {
        File dbFile = new File(getFilesDir(),"student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        String strSql = "Select * from students where _id=?";
        Cursor c = db.rawQuery(strSql,new String[] {"2"});  //Select id=2的資料
        c.moveToFirst();
        Log.d("DB",c.getString(1) + "," + c.getInt(2));
        c.close();
        db.close();
    }

    //各種select的用法
    public void click04(View v) {
        File dbFile = new File(getFilesDir(),"student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        Cursor c = db.query("students",new String[]{"_id","name","score"},null,null,null,null,null);
        c.moveToFirst();
        Log.d("DB",c.getString(1) + "," + c.getInt(2));
        c.close();
        db.close();
    }

    //各種select的用法
    public void click05(View v) {
        File dbFile = new File(getFilesDir(),"student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        Cursor c = db.query("students",new String[]{"_id","name","score"},"_id=?",new String[]{"2"},null,null,null);
        c.moveToFirst();
        Log.d("DB",c.getString(1) + "," + c.getInt(2));
        c.close();
        db.close();
    }

    //各種Insert的用法
    public void click06(View v) {
        File dbFile = new File(getFilesDir(),"student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("Insert into students(_id,name,score) values(4,'Bob',96)");
        db.close();
    }

    //各種Insert的用法
    public void click07(View v) {
        File dbFile = new File(getFilesDir(),"student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues cv = new ContentValues();
        cv.put("_id",5);
        cv.put("name","Mary");
        cv.put("score",95);
        db.insert("students",null,cv);
        db.close();
    }

    //修改資料
    public void click08(View v) {
        File dbFile = new File(getFilesDir(),"student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues cv = new ContentValues();
        cv.put("score",100);
        db.update("students",cv,"_id=?",new String[]{"2"}); //修改id=2的資料
        db.close();
    }

    public void click09(View v) {
        File dbFile = new File(getFilesDir(),"student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        db.delete("students","_id=?",new String[]{"2"});  //刪除id=2的資料

    }

}
