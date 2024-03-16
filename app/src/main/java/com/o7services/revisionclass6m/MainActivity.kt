package com.o7services.revisionclass6m

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.o7services.revisionclass6m.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var activityInterface: ActivityInterface
    lateinit var valuesLiveData: ValuesLiveData
    private val TAG = "MainActivity"
//    val valuesLiveData : ValuesLiveData by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        valuesLiveData = ViewModelProvider(this)[ValuesLiveData::class.java]
        binding.btnChangeFragmentText.setOnClickListener {
           // activityInterface.changeNameRollNo("O7 Services", 1)
            valuesLiveData.name.setValue("Testing View Model Value")
            valuesLiveData.rollNo.setValue(90)
            Log.e(TAG, " in button click")
        }



    }
}