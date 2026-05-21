package com.example.cupcake.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cupcake.CupcakeApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            OrderViewModel(
                cupcakeApplication().container.ordersRepository
            )
        }
    }
}

fun CreationExtras.cupcakeApplication(): CupcakeApplication =
    (this[APPLICATION_KEY] as CupcakeApplication)