package com.victorsdd.androidmaster.my_app.settings

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.victorsdd.androidmaster.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class SettingsActivity : AppCompatActivity() {

    private var firstTime : Boolean = true

    private lateinit var bindin : ActivitySettingsBinding
    companion object {
        const val VOLUME_LVL : String = "volume_lvl"
        const val DARK_MODE : String = "dark_mode"
        const val BLUETOOTH : String = "bluetooth"
        const val VIBRATION : String = "vibration"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindin = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(bindin.root)
        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter { firstTime }.collect{
                runOnUiThread {
                    bindin.swDarkMode.isChecked = it.darkMode
                    bindin.swBluetooth.isChecked = it.bluetooth
                    bindin.swVibration.isChecked = it.vibration
                    bindin.volumeSlider.setValues(it.volume.toFloat())
                    firstTime = !firstTime
                }
            }
        }

        initUI()
    }


    private fun initUI(){
        bindin.volumeSlider.addOnChangeListener { _, value, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
            }
        }

        bindin.swDarkMode.setOnCheckedChangeListener { _, value ->
            if(value) enableDarkMode() else disableDarkMode()

            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(DARK_MODE, value)
            }
        }

        bindin.swBluetooth.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(BLUETOOTH, value)
            }
        }

        bindin.swVibration.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(VIBRATION, value)
            }
        }
    }

    private suspend fun saveVolume(value:Int){
        dataStore.edit {
            preferences -> preferences[intPreferencesKey(VOLUME_LVL)] = value
        }
    }

    private suspend fun saveOptions(key: String,value: Boolean){
        dataStore.edit {
                preferences -> preferences[booleanPreferencesKey(key)] = value
        }
    }

    private fun getSettings() : Flow<SettingsModel>{
         return dataStore.data.map { preferences ->
             SettingsModel(
                 volume = preferences[intPreferencesKey(VOLUME_LVL)] ?: 10,
                 bluetooth = preferences[booleanPreferencesKey(BLUETOOTH)] ?: false,
                 darkMode = preferences[booleanPreferencesKey(DARK_MODE)] ?: false,
                 vibration = preferences[booleanPreferencesKey(VIBRATION)] ?: false
             )
        }
    }

    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}