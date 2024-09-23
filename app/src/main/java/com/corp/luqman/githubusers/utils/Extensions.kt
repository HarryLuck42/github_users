package com.corp.luqman.githubusers.utils

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("SimpleDateFormat")
fun String.isMoreThan(updateAt: String): Boolean{
    try{
        if(this.isNotEmpty() && updateAt.isNotEmpty()){
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
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

@SuppressLint("SimpleDateFormat")
fun String.reformatDate(oldFormat: String, newFormat: String) : String{
    try{

        val old = SimpleDateFormat(oldFormat)
        val new = SimpleDateFormat(newFormat, Locale.US)
        val date = old.parse(this)
        val result = new.format(date ?: Date())
        return result
    }catch (e: Exception){
        return this
    }
}