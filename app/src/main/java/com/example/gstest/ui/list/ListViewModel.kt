package com.example.gstest.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gstest.domain.CharacterUseCase
import com.example.gstest.domain.DeleteDataUseCase
import com.example.gstest.domain.GetDataUseCase
import com.example.gstest.domain.SaveDataUseCase
import com.example.masterdetail.dbroom.dbmodel.GSTest
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val characterUseCase: CharacterUseCase,
                                        private val getDataUseCase: GetDataUseCase,
                                        private val saveDataUseCase: SaveDataUseCase,
                                        private val deleteDataUseCase: DeleteDataUseCase): ViewModel() {
    var getList = MutableLiveData<JsonObject>()
    var getDataList = MutableLiveData<List<GSTest>>()
    fun onCreateCharacter(){
        viewModelScope.launch {
          val res =  characterUseCase.invoke()
            getList.postValue(res as JsonObject)
        }
    }

    fun getData(){
        viewModelScope.launch {
            getDataList.postValue(getDataUseCase.getData())
        }
    }

    fun saveData(gsTest: GSTest){
        viewModelScope.launch {
            saveDataUseCase.saveData(gsTest)
        }
    }

    fun deleteData(){
        viewModelScope.launch {
            deleteDataUseCase.deleteData()
        }
    }


}