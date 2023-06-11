package com.vaibhavmojidra.androidkotlindemo2workmanagerchainingworkersoneafteranother

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.vaibhavmojidra.androidkotlindemo2workmanagerchainingworkersoneafteranother.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        val oneTimeRequestForWork1=OneTimeWorkRequest.Builder(MyWork1::class.java).build()
        val oneTimeRequestForWork2=OneTimeWorkRequest.Builder(MyWork2::class.java).build()
        val oneTimeRequestForWork3=OneTimeWorkRequest.Builder(MyWork3::class.java).build()

        val workManager=WorkManager.getInstance(this)

        workManager.getWorkInfoByIdLiveData(oneTimeRequestForWork1.id).observe(this) {
            binding.work1TextView.text = it.state.name
        }

        workManager.getWorkInfoByIdLiveData(oneTimeRequestForWork2.id).observe(this) {
            binding.work2TextView.text = it.state.name
        }

        workManager.getWorkInfoByIdLiveData(oneTimeRequestForWork3.id).observe(this) {
            binding.work3TextView.text = it.state.name
        }


        binding.startWorksButton.setOnClickListener{
            workManager
                .beginWith(oneTimeRequestForWork1)
                .then(oneTimeRequestForWork2)
                .then(oneTimeRequestForWork3).enqueue()
        }
    }
}