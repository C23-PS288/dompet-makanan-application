package com.rozi.dompetmakanan.ui.screen.login

import com.rozi.dompetmakanan.utils.UiState

sealed class AuthUIState {


    object OnLogin : AuthUIState()

    object OnNotLogin : AuthUIState()
}