package com.example.shopaholic.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.shopaholic.Models.Categories.prot

class DBHelper(
    context: Context?,
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "Shopaholic"
        private val TABLE_CART = "CART"
        private val TABLE_BRANDS = "BRANDS"
        private val TABLE_ESSENTIALS = "ESSENTIALS"
        private val TABLE_DISCOUNT = "DISCOUNT"
        private val TABLE_BANNER = "BANNER"
        private val TABLE_CATEGORIES = "CATEGORIES"
        private val TABLE_SUB_CAT = "SUBCATEGORIES"
        private val TABLE_PRODUCTS = "PRODUCTS"
        private val TABLE_DEALS = "DEALS"
        //private val TABLE_

        //Product
        private val PRODUCT_ID = "product_Id"
        private val PRODUCT_NAME = "product_Name"
        private val PRODUCT_PRICE = "product_Price"
        private val PRODUCT_QUANTITY = "product_Quantity"
        private val PRODUCT_IMAGE = "product_Image"

    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE " + TABLE_CART + " (" + PRODUCT_ID + " TEXT PRIMARY KEY ," +
                    PRODUCT_NAME + " TEXT ," +
                    PRODUCT_QUANTITY + " INTEGER ," +
                    PRODUCT_IMAGE + " TEXT ," +
                    PRODUCT_PRICE + " TEXT )"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CART)
        onCreate(db)
    }

    fun addProductToCart(_prot: prot) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(PRODUCT_ID, _prot.product_ID)
        contentValues.put(PRODUCT_NAME, _prot.name)
        contentValues.put(PRODUCT_PRICE, _prot.price)
        contentValues.put(PRODUCT_QUANTITY, _prot.quantitiy)
        contentValues.put(PRODUCT_IMAGE, _prot.image)

        val success = db.insert(TABLE_CART, null, contentValues)
        Log.d("Database", "Add Successfull")
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection

    }

    fun update(_prot: prot) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(PRODUCT_ID, _prot.product_ID)
        contentValues.put(PRODUCT_NAME, _prot.name)
        contentValues.put(PRODUCT_PRICE, _prot.price)
        contentValues.put(PRODUCT_QUANTITY, _prot.quantitiy)
        contentValues.put(PRODUCT_IMAGE, _prot.image)

        val success =
            db.update(TABLE_CART, contentValues, PRODUCT_ID + "=" + _prot.product_ID, null)
        //2nd argument is String containing nullColumnHack
        Log.d("Database", "Update Successfull")
        db.close() // Closing database connection
    }

    fun getProt(): ArrayList<prot> {
        val empList: ArrayList<prot> = ArrayList<prot>()
        val selectQuery = "SELECT  * FROM $TABLE_CART"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)

        }
        var product_ID: String
        var product_Name: String
        var product_Price: String
        var product_Quantity: Int
        var product_Image: String


        if (cursor?.moveToFirst()!!) {
            do {
                product_ID = cursor.getString(cursor.getColumnIndex(PRODUCT_ID))
                product_Name = cursor.getString(cursor.getColumnIndex(PRODUCT_NAME))
                product_Price = cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE))
                product_Quantity = cursor.getInt(cursor.getColumnIndex(PRODUCT_QUANTITY))
                product_Image = cursor.getString(cursor.getColumnIndex(PRODUCT_IMAGE))

                val prot = prot(
                    product_ID,
                    product_Name,
                    product_Price,
                    product_Image,
                    product_Quantity,
                    0
                )
                empList.add(prot)

                Log.d("Database", "View Successfull")
            } while (cursor.moveToNext())
        }
        return empList
    }

}