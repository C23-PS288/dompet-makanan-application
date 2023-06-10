package com.rozi.dompetmakanan.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rozi.dompetmakanan.di.Injection
import com.rozi.dompetmakanan.ui.screen.home.HomeViewModel
import com.rozi.dompetmakanan.ui.screen.login.LoginViewModel
import com.rozi.dompetmakanan.ui.screen.register.RegisterViewModel

class ViewModelFactory (private val maApplication: Application) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(maApplication) as T
        }else if(modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel() as T
        }else if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(Injection.provideUserRepository(maApplication)) as T
        }
        throw IllegalArgumentException("Uknown ViewModel class: ${modelClass.name}")
    }
}