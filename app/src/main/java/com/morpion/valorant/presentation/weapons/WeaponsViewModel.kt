package com.morpion.valorant.presentation.weapons

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.morpion.valorant.data.remote.request.LanguageRequest
import com.morpion.valorant.domain.model.AgentModel
import com.morpion.valorant.domain.model.WeaponModel
import com.morpion.valorant.domain.usecase.GetWeaponsUseCase
import com.morpion.valorant.presentation.agents.AgentsState
import com.morpion.valorant.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeaponsViewModel @Inject constructor(private val getWeaponsUseCase: GetWeaponsUseCase) : BaseViewModel() {

    private var allWeapons = listOf<WeaponModel>()

    private val _state = mutableStateOf(WeaponsState())
    val state: State<WeaponsState> = _state

    private var _searchWeapon = mutableStateOf("")
    val searchWeapon: State<String> = _searchWeapon

    private val lang = if(Locale.getDefault().displayLanguage == "Türkçe") {
        "tr-TR"
    } else {
        "en-US"
    }

    init {
        getWeapons()
    }
    
    private fun getWeapons() {
        viewModelScope.launch {
            request(
                flow = getWeaponsUseCase(input = LanguageRequest(lang)),
                onLoading = { _state.value = WeaponsState(isLoading = true) },
                onSuccess = { itWeapons ->
                    itWeapons.let {
                        _state.value = WeaponsState(weapons = it)
                        allWeapons = it
                    }
                },
                onError = {
                    _state.value = WeaponsState(error = it.toString())
                }
            )
        }
    }

    fun searchWeapon(weapon: String) {
        _searchWeapon.value = weapon
        _state.value = WeaponsState(isLoading = true)

        val foundWeapons = allWeapons.filter { itWeapon ->
                (itWeapon.displayName?:"").contains(weapon, true)
        }

        if (foundWeapons.isEmpty()) {
            _state.value = WeaponsState(error = "No weapons")
        }   else {
            _state.value = WeaponsState(weapons = foundWeapons)
        }
    }

    fun clearSearch() {
        _state.value = WeaponsState(weapons = allWeapons)
        _searchWeapon.value = ""
    }
}