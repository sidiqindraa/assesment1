package org.d3if0108.piramidcount.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0108.piramidcount.db.LimasDao

class HistoriViewModel(private val db: LimasDao) : ViewModel() {
    val data = db.getLastLimas()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO){
            db.clearData()
        }
    }
}
