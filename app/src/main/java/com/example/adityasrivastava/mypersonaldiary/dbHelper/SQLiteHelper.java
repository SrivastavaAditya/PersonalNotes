package com.example.adityasrivastava.mypersonaldiary.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.adityasrivastava.mypersonaldiary.models.CupBoardModel;
import com.example.adityasrivastava.mypersonaldiary.models.LoginUser;
import com.example.adityasrivastava.mypersonaldiary.models.UserCredentials;
import com.example.adityasrivastava.mypersonaldiary.models.UserProfile;

import nl.qbusict.cupboard.CupboardBuilder;
import nl.qbusict.cupboard.CupboardFactory;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;


/**
 * The type Sq lite helper.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "personal_notes.db";
    private final static int DATABASE_VERSION = 1;
    private static SQLiteHelper sqLiteHelper;
    private static SQLiteDatabase db;

    /**
     * Instantiates a new Sq lite helper.
     *
     * @param context the context
     */
    public SQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        cupboard().withDatabase(sqLiteDatabase).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        cupboard().withDatabase(sqLiteDatabase).upgradeTables();
    }

    static {
        CupboardFactory.setCupboard(new CupboardBuilder().useAnnotations().build());

        cupboard().register(UserCredentials.class);
        cupboard().register(UserProfile.class);
        cupboard().register(LoginUser.class);
    }

    /**
     * Get instance sq lite helper.
     *
     * @param context the context
     * @return the sq lite helper
     */
    public static SQLiteHelper getInstance(Context context){
        if(sqLiteHelper == null){
            sqLiteHelper = new SQLiteHelper(context);
            openConnection();
        }
        return sqLiteHelper;
    }

    /**
     * Open connection.
     */
    public static void openConnection(){
        if(db == null){
            db = sqLiteHelper.getWritableDatabase();
        }
    }

    /**
     * Close connection.
     */
    public synchronized void closeConnection(){
        if (sqLiteHelper != null) {
            sqLiteHelper.close();
            db.close();
            sqLiteHelper = null;
            db = null;
        }
    }

    /**
     * Insert.
     *
     * @param data the data
     */
    public void insert(CupBoardModel data){
        cupboard().withDatabase(db).put(data);
    }

    /**
     * Delete.
     *
     * @param data the data
     */
    public void delete(CupBoardModel data){
        cupboard().withDatabase(db).delete(data);
    }

    /**
     * Check user credentials boolean.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    public boolean checkUserCredentials(String username, String password){
        if(cupboard().withDatabase(db).query(UserCredentials.class)
                .withSelection("userName=? AND userPassword=?", username, password) != null){
            return true;
        }
        return false;
    }

    /**
     * Get user id long.
     *
     * @param username the username
     * @return the long
     */
    public long getUserId(String username){
        return cupboard().withDatabase(db).query(UserCredentials.class)
                .withSelection("userName=?", username)
                .get().userId;
    }

    /**
     * Update user profile int.
     *
     * @param userid    the userid
     * @param firstname the firstname
     * @param lastname  the lastname
     * @return the int
     */
    public int updateUserProfile(long userid, String firstname, String lastname){
        ContentValues contentValues = new ContentValues();
        if(firstname.isEmpty() && !lastname.isEmpty()){
            contentValues.put("lastName", lastname);
        }else if(!firstname.isEmpty() && lastname.isEmpty()){
            contentValues.put("firstName", firstname);
        }else{
            contentValues.put("firstName", firstname);
            contentValues.put("lastName", lastname);
        }

        return cupboard().withDatabase(db)
                .update(UserProfile.class, contentValues, "userId=?",
                        String.valueOf(userid));
    }

    /**
     * Change credentials int.
     *
     * @param userid      the userid
     * @param username    the username
     * @param newpassword the newpassword
     * @return the int
     */
    public int changeCredentials(long userid, String username, String newpassword){
        ContentValues contentValues = new ContentValues();
        if(username.isEmpty() && !newpassword.isEmpty()){
            contentValues.put("userPassword", newpassword);
        }else if(!username.isEmpty() && newpassword.isEmpty()){
            contentValues.put("userName", username);
        }else{
            contentValues.put("userName", username);
            contentValues.put("userPassword", newpassword);
        }

        return cupboard().withDatabase(db)
                .update(UserCredentials.class, contentValues, "userId=?",
                        String.valueOf(userid));
    }

    /**
     * Is user name unique boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean isUserNameUnique(String username){
        if(cupboard().withDatabase(db).query(UserCredentials.class)
                .withSelection("userName=?", username) != null){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Get login user login user.
     *
     * @return the login user
     */
    public LoginUser getLoginUser(){
        return cupboard().withDatabase(db).query(LoginUser.class).get();
    }
}