package com.o7services.revisionclass6m

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @Author: 017
 * @Date: 16/03/24
 * @Time: 10:21 am
 */
class ValuesLiveData : ViewModel() {
    var name : MutableLiveData<String> = MutableLiveData("")
    var rollNo : MutableLiveData<Int> = MutableLiveData(0)
}