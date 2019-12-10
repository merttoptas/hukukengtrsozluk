package com.merttoptas.hukukengtrsozluk.utilities

import android.content.Context
import android.content.Intent
import androidx.annotation.Nullable
import androidx.room.Room
import com.merttoptas.hukukengtrsozluk.Activity.WordsActivity
import com.merttoptas.hukukengtrsozluk.db.AppDatabase
import com.merttoptas.hukukengtrsozluk.db.FavDao
import com.nicolettilu.hiddensearchwithrecyclerview.HiddenSearchWithRecyclerView

abstract class Utils {

    companion object{

        var context = Global.applicationContext()

       fun startIntent(context: Context, className: Class<WordsActivity>, @Nullable intentName: String?, @Nullable fragmentId:String?)
        {
            val intent =Intent(context, className)
            intent.putExtra(intentName, fragmentId )
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.flags =Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            return context.startActivity(intent)

        }

        fun buildDatabase():AppDatabase{

            val db: AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "favorites")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

            return db
        }

        fun getDAO():FavDao{
            val DAO =buildDatabase().favdao()
            return DAO
        }

    }



}