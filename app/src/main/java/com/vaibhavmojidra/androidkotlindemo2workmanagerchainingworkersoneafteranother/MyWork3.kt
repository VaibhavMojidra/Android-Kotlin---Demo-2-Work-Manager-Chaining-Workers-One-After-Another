package com.vaibhavmojidra.androidkotlindemo2workmanagerchainingworkersoneafteranother

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWork3(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        for (i in 1..6000000){
            Log.i("MyTag","Work 3: $i")
        }

        return Result.success()
    }
}