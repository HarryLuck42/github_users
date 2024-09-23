package com.corp.luqman.githubusers.utils

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun String.isMoreThan(updateAt: String): Boolean{
    try{
        if(this.isNotEmpty() && updateAt.isNotEmpty()){
            val format = SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ")
            val new = format.parse(this)
            val old = format.parse(updateAt)
            Log.d("Harry Test", "Result: ${new?.time} -> $this  && ${old?.time} -> $updateAt")
            return (new?.time ?: 0) > (old?.time ?:0)
        } else{
            return false
        }
    }catch (e: Exception){
        return false
    }
}