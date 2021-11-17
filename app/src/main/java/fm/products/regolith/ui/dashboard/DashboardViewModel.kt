package fm.products.regolith.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fm.products.regolith.domain.convertToBonusData
import fm.products.regolith.domain.convertToGradeData
import fm.products.regolith.domain.convertToProfitData
import fm.products.regolith.domain.convertToRefillData
import fm.products.regolith.domain.networking.NetworkManager
import fm.products.regolith.ui.recyclerAdapters.CardItemData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar: LiveData<Boolean>
        get() = _showProgressBar

    private val _items = MutableLiveData<List<CardItemData>>()
    val items: LiveData<List<CardItemData>>
        get() = _items

    private val _showMessage = MutableLiveData<String>()
    val showMessage: LiveData<String>
        get() = _showMessage

    private val networkManager = NetworkManager.createClient()

    fun showAlert(it: String) {
        _showMessage.value = it
    }

    fun loadItems() {
        _showProgressBar.value = true
        viewModelScope.launch(Dispatchers.IO){
            val bonus = networkManager.loadBonus()
            val profit = networkManager.loadProfit()
            val refill = networkManager.loadRefill()
            val grade = networkManager.loadGrade()

            val items = mutableListOf<CardItemData>()

            if (grade != null) items.add(grade.convertToGradeData())
            if (refill != null) items.add(refill.convertToRefillData())
            if (profit != null) items.add(profit.convertToProfitData())
            if (bonus != null) items.add(bonus.convertToBonusData())

            viewModelScope.launch(Dispatchers.Main){
                _items.value = items
                _showProgressBar.value = false
            }
        }


    }

}

