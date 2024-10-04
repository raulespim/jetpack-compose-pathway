package com.raulespim.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.raulespim.dessertclicker.data.Datasource
import com.raulespim.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertClickerViewModel(
    private val datasource: Datasource = Datasource
) : ViewModel() {

    private var _uiState = MutableStateFlow(DessertClickerUiState())
    val uiState: StateFlow<DessertClickerUiState> get() = _uiState.asStateFlow()

    val desserts get() = datasource.dessertList

    init {
        _uiState.update { it.copy(
            currentDessertPrice = desserts[it.currentDessertIndex].price,
            currentDessertImageId = desserts[it.currentDessertIndex].imageId
        ) }
    }

    fun onDessertClicked() {
        _uiState.update {
            val nextDessert = determineDessertToShow(desserts, it.dessertsSold)
            it.copy(
                revenue = it.revenue + it.currentDessertPrice,
                dessertsSold = it.dessertsSold.inc(),
                currentDessertPrice = nextDessert.price,
                currentDessertImageId = nextDessert.imageId
            )
        }
    }

    /**
     * Determine which dessert to show.
     */
    fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }
}