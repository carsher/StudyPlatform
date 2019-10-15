package com.example.administrator.learning.common.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;



/**
 * 数据库相关操作，用于存取轨迹记录
 *
 */
public class DbAdapter {
	public static Context context;
	public static final String KEY_ROWID = "id";
	public static final String USER = "user";
	public static final String VIDEOPATH = "videopath";//小节对应的视频路径 例如：/videopath/xxxxx.mp4
	public static final String TITLE = "title";//小节对应的标题
	public static final String COURSENAME = "coursename";//课程名称
//	public static final String LEARNING = "learning";//学习该门课程的人数
	public static final String SECITON = "seciton";//课程id
    public static final String COURSETIME = "coursetime";//课程时长
    public static final String COURRENTTIME = "courrenttime";//观看时长
    public static final String IMGPATH = "imgpath";//该课程预览图片路径
    public static final String DATA = "data";//系统时间

	//private final static String DATABASE_PATH = android.os.Environment
	//		.getExternalStorageDirectory().getAbsolutePath() + "/recordPath";
	//static final String DATABASE_NAME = context.getFilesDir() + "/" + "run_record_userhave_.db";
	private static final int DATABASE_VERSION = 1;
	private static final String RECORD_TABLE = "record_userhave_2";
	private static final String RECORD_CREATE = "create table if not exists record_userhave_2("
			+ KEY_ROWID
			+ " integer primary key autoincrement,"
			+ "user STRING,"
			+ "videopath STRING,"
			+ "title STRING,"
			+ "coursename STRING,"
			+ "seciton STRING,"
			+ "coursetime STRING,"
			+ "courrenttime STRING,"
			+ "imgpath STRING,"
			+ "data STRING" + ");";

	public static class DatabaseHelper extends SQLiteOpenHelper {
		public DatabaseHelper(Context context) {
			super(context, context.getFilesDir().getAbsolutePath() + "/" + "record_userhave_2.db", null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(RECORD_CREATE);
			Log.e("我是数据库","我被调用了");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}

	private Context mCtx = null;
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;

	// constructor
	public DbAdapter(Context ctx) {
		this.mCtx = ctx;
		context = ctx;
		dbHelper = new DatabaseHelper(mCtx);
	}

	public DbAdapter open() throws SQLException {

		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	public Cursor getall() {
		return db.rawQuery("SELECT * FROM record_userhave_2", null);
	}

	// remove an entry
	public boolean delete(long rowId) {

		return db.delete(RECORD_TABLE, "id=" + rowId, null) > 0;
	}

	/**
	 * 数据库存入一条观看记录
	 *
	 * @return
	 */
	public long createrecord(String videopath, String user,
							 String title, String coursename,
							 String seciton, String coursetime,String courrenttime,String imgpath,String data) {
		ContentValues args = new ContentValues();
		args.put("videopath", videopath);
		args.put("title", title);
		args.put("coursename", coursename);
		args.put("seciton", seciton);
		args.put("coursetime", coursetime);
		args.put("user", user);
		args.put("courrenttime", courrenttime);
		args.put("imgpath", imgpath);
		args.put("data", data);
		List<HashMap> list = queryRecordAll(user);
		for (int i=1;i<list.size();i++){
			if (title.equals(list.get(i-1).get("title"))){
				SQLiteDatabase dber =  dbHelper.getWritableDatabase();
				dber.execSQL("update record_userhave_2 set user=?,videopath=?," +
						"title=?,coursename=?,seciton=?,coursetime=?,courrenttime=?," +
						"imgpath=?,data=? where title=?",
						new Object[]{user,videopath,title,coursename,seciton,coursetime,courrenttime,imgpath,data,title});
				Log.e("更新成功", "createrecord: "+imgpath);
				return 1000;
			}
		}
        Log.e("插入成功", "createrecord: "+user);
		return db.insert(RECORD_TABLE, null, args);

    }

	/**
	 * 查询所有观看记录
	 *
	 * @return
	 */
	public List<HashMap> queryRecordAll(String user) {

		List<HashMap> list = new ArrayList<HashMap>();
		Log.e("QUERY", "queryRecordAll: "+user);
		Cursor allRecordCursor = db.query(RECORD_TABLE, getColumns(), "user = ?",
				new String[]{user}, null, null, null);
		while (allRecordCursor.moveToNext()) {
            HashMap map = new HashMap();
            map.put(USER,allRecordCursor.getString(allRecordCursor.getColumnIndex(USER)));
            map.put(VIDEOPATH,allRecordCursor.getString(allRecordCursor.getColumnIndex(VIDEOPATH)));
            map.put(TITLE,allRecordCursor.getString(allRecordCursor.getColumnIndex(TITLE)));
            map.put(COURSENAME,allRecordCursor.getString(allRecordCursor.getColumnIndex(COURSENAME)));
            map.put(SECITON,allRecordCursor.getString(allRecordCursor.getColumnIndex(SECITON)));
            map.put(COURSETIME,allRecordCursor.getString(allRecordCursor.getColumnIndex(COURSETIME)));
            map.put(COURRENTTIME,allRecordCursor.getString(allRecordCursor.getColumnIndex(COURRENTTIME)));
            map.put(IMGPATH,allRecordCursor.getString(allRecordCursor.getColumnIndex(IMGPATH)));
            map.put(DATA,allRecordCursor.getString(allRecordCursor.getColumnIndex(DATA)));

            list.add(map);
		}
        Log.e("查询成功", "createrecord: ");
        Collections.reverse(list);
		return list;
	}

	private String[] getColumns() {
		return new String[] { KEY_ROWID, USER, VIDEOPATH, TITLE,
                COURSENAME, SECITON, COURSETIME,COURRENTTIME,IMGPATH,DATA };
	}
	/**
	 *  @创建用户 somafish
	 *  @创建时间 2018/11/2  11:16
	 *  @方法说明：执行任意一条数据库操作语句
	 */
	public void queryBySql(String sql){
		db.execSQL(sql);
	}

}
