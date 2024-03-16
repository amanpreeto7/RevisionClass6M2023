package com.o7services.revisionclass6m

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.o7services.revisionclass6m.databinding.FragmentSharedPrefsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SharedPrefsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SharedPrefsFragment : Fragment(), ActivityInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentSharedPrefsBinding ?= null
    lateinit var sharedPrefs : SharedPreferences
    lateinit var editor : SharedPreferences.Editor
    lateinit var mainActivity: MainActivity
    var name: String = ""
    var rollNo: Int = 0
    lateinit var valuesLiveData: ValuesLiveData
    private val TAG = "SharedPrefsFragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        mainActivity.activityInterface = this
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        sharedPrefs = mainActivity.getSharedPreferences(mainActivity.resources.getString(R.string.app_name), Context.MODE_PRIVATE )
        editor = sharedPrefs.edit()
        name = sharedPrefs.getString("Name", "")?:""
        rollNo = sharedPrefs.getInt("RollNo", 0)
        valuesLiveData = ViewModelProvider(mainActivity)[ValuesLiveData::class.java]


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSharedPrefsBinding.inflate(layoutInflater)
        binding?.name = name
        binding?.rollNo = rollNo
        binding?.fragment = this
        return  binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnSave?.setOnClickListener {
            if(binding?.etName?.text.toString().isNullOrEmpty()){
                binding?.etName?.error = mainActivity.resources.getString(R.string.enter_your_name)
            } else if(binding?.etRollNo?.text.toString().isNullOrEmpty()){
                binding?.etRollNo?.error = mainActivity.resources.getString(R.string.enter_your_roll_no)
            }else{
                editor.putString("Name", binding?.etName?.text?.toString())
                editor.putInt("RollNo", (binding?.etRollNo?.text?.toString()?.toInt()?:0))
                editor.commit()
                editor.apply()
            }
        }

        valuesLiveData.name.observe(mainActivity){
            Log.e(TAG, "name $name")
            binding?.name = it
        }

        valuesLiveData.rollNo.observe(mainActivity){test->
            binding?.rollNo = test
        }
    }

    fun onClick(value: Int){
        when(value){
            1-> mainActivity.binding.clMain.setBackgroundColor(ContextCompat.getColor(mainActivity, R.color.red))
            2-> mainActivity.binding.clMain.setBackgroundColor(ContextCompat.getColor(mainActivity, R.color.blue))
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SharedPrefsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SharedPrefsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun changeNameRollNo(name: String, rollNo: Int) {
        binding?.name = name
        binding?.rollNo = rollNo
    }
}