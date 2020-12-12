package com.example.personalrestaurantguide.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.personalrestaurantguide.model.DbHandler;
import com.example.personalrestaurantguide.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantController extends DbHandler {
    public RestaurantController(Context context) {
        super(context);
    }

    final String TABLE_RESTAURANT=" tblRestaurant ";

    public boolean manageRestaurant(Restaurant restaurant)
    {
        ContentValues cv = new ContentValues();
        cv.put("name", restaurant.getName());
        cv.put("tag", restaurant.getTag());
        cv.put("address", restaurant.getAddress());
        cv.put("city", restaurant.getCity());
        cv.put("state", restaurant.getState());
        cv.put("zip", restaurant.getZip());
        cv.put("description", restaurant.getDescription());
        cv.put("phone", restaurant.getPhone());
        cv.put("country", restaurant.getCountry());
        cv.put("active", restaurant.getActive());
        cv.put("rate",restaurant.getRate());
        SQLiteDatabase db = this.getWritableDatabase();
        boolean isSuccessful;
        if(restaurant.getRestid()==0) {
            isSuccessful= db.insert(TABLE_RESTAURANT, null, cv) > 0;
        }
        else
        {
            isSuccessful=db.update(TABLE_RESTAURANT, cv, "restid = ?", new String[]{Integer.toString(restaurant.getRestid())})>0;
        }
        db.close();
        return  isSuccessful;
    }

    public Restaurant getRestaurantById(int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String[] columns = {
                "restid",
                "name",
                "tag",
                "address",
                "description",
                "city",
                "state",
                "zip",
                "country",
                "phone",
                "active",
                "rate"
        };

        String selection=" restid = ? ";
        String [] selectionArgs= {Integer.toString(id)};
        Cursor cursor= db.query(TABLE_RESTAURANT,columns,selection,selectionArgs,null,null,null);
        Restaurant restaurant = new Restaurant();
        if (cursor.moveToFirst()) {
            do {
                restaurant.setRestid(Integer.parseInt(cursor.getString(0)));
                restaurant.setName(cursor.getString(1));
                restaurant.setTag(cursor.getString(2));
                restaurant.setAddress(cursor.getString(3));
                restaurant.setDescription(cursor.getString(4));
                restaurant.setCity(cursor.getString(5));
                restaurant.setState(cursor.getString(6));
                restaurant.setZip(cursor.getString(7));
                restaurant.setCountry(cursor.getString(8));
                restaurant.setPhone(cursor.getString(9));
                restaurant.setActive(Integer.parseInt(cursor.getString(10)));
                restaurant.setRate(Float.parseFloat(cursor.getString(11)));
               // restaurant.setRate((float)1.5);

            } while (cursor.moveToNext());
        }
        db.close();
        return restaurant;
    }
    public List<Restaurant> getAllRestaurant()
    {
        String countQuery = "SELECT  * FROM " + TABLE_RESTAURANT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        List<Restaurant> restaurantList=new ArrayList<Restaurant>();

        if (cursor.moveToFirst()) {
            do {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestid(Integer.parseInt(cursor.getString(0)));
                restaurant.setName(cursor.getString(1));
                restaurant.setTag(cursor.getString(2));
                restaurant.setAddress(cursor.getString(3));
                restaurant.setDescription(cursor.getString(4));
                restaurant.setCity(cursor.getString(5));
                restaurant.setState(cursor.getString(6));
                restaurant.setZip(cursor.getString(7));
                restaurant.setCountry(cursor.getString(8));
                restaurant.setPhone(cursor.getString(9));
                restaurant.setActive(Integer.parseInt(cursor.getString(10)));
                restaurant.setRate(Float.parseFloat(cursor.getString(11)));
                //  restaurant.setRate((float) 1.5);
                restaurantList.add(restaurant);
            } while (cursor.moveToNext());
        }
        db.close();
        return restaurantList;
    }

    public List<Restaurant> getAllRestaurantByQuery(String txt)
    {
        String[] columns = {
                "restid",
                "name",
                "tag",
                "address",
                "description",
                "city",
                "state",
                "zip",
                "country",
                "phone",
                "active",
                "rate"
        };
        String[] selectionArgs =null;
        String selection =null;
        if(txt!="") {
             selectionArgs = new String[]{txt, txt};
            selection = " tag LIKE ? OR name LIKE ?";
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM tblRestaurant WHERE tag = ? OR name = ? OR ''=?",new String[]{txt,txt,txt});
       // Cursor cursor =db.query(TABLE_RESTAURANT,columns,selection,selectionArgs,null,null,null) ;
        List<Restaurant> restaurantList=new ArrayList<Restaurant>();

        if (cursor.moveToFirst()) {
            do {
                Log.d("in","yes");
                Restaurant restaurant = new Restaurant();
                restaurant.setRestid(Integer.parseInt(cursor.getString(0)));
                restaurant.setName(cursor.getString(1));
                restaurant.setTag(cursor.getString(2));
                restaurant.setAddress(cursor.getString(3));
                restaurant.setDescription(cursor.getString(4));
                restaurant.setCity(cursor.getString(5));
                restaurant.setState(cursor.getString(6));
                restaurant.setZip(cursor.getString(7));
                restaurant.setCountry(cursor.getString(8));
                restaurant.setPhone(cursor.getString(9));
                restaurant.setActive(Integer.parseInt(cursor.getString(10)));
                restaurant.setRate(Float.parseFloat(cursor.getString(11)));
                //  restaurant.setRate((float) 1.5);
                restaurantList.add(restaurant);
            } while (cursor.moveToNext());
        }
        db.close();
        return restaurantList;
    }


    public int getRestaurantCount() {
        String countQuery = "SELECT  * FROM " + TABLE_RESTAURANT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt=cursor.getCount();
        cursor.close();
        db.close();
        return cnt;
    }
    public boolean deleteRestaurant(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "restid=?";
        String whereArgs[] = {Integer.toString(id)};
        boolean isSuccessful =db.delete(TABLE_RESTAURANT, whereClause, whereArgs)>0;
        db.close();
        return isSuccessful;

    }
}
