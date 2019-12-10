package com.merttoptas.hukukengtrsozluk.utilities

import android.content.Context
import android.content.Intent
import androidx.annotation.Nullable
import com.merttoptas.hukukengtrsozluk.Activity.WordsActivity
import com.nicolettilu.hiddensearchwithrecyclerview.HiddenSearchWithRecyclerView

abstract class Utils {

    companion object{

       fun startIntent(context:Context, className: Class<WordsActivity>, @Nullable intentName: String?, @Nullable fragmentId:String?)
        {
            val intent =Intent(context, className)
            intent.putExtra(intentName, fragmentId )
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.flags =Intent.FLAG_ACTIVITY_CLEAR_TOP

            return context.startActivity(intent)

        }


    }
}