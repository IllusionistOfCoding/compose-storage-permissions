package com.startup.compose.template.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.startup.compose.template.datastore.SharedPrefDataStore.PreferenceKeys.USER_KEY
import com.startup.compose.template.model.LocalUser
import com.startup.compose.template.util.loge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ISharedPrefDataStore {
    suspend fun setUserToDataStore(user: LocalUser)
    val userDataStore: Flow<LocalUser>
    suspend fun deleteUser()
}

class SharedPrefDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ISharedPrefDataStore {

    private val gson: Gson = Gson()

    override suspend fun setUserToDataStore(user: LocalUser) {
        dataStore.setValue {
            it[USER_KEY] = gson.toJson(user)
        }
    }

    override val userDataStore: Flow<LocalUser> = dataStore.getValue {
        val userString: String = it[USER_KEY] ?: ""
        return@getValue gson.fromJson(userString, LocalUser::class.java)
    }

    override suspend fun deleteUser() {
        withContext(Dispatchers.IO) {
            try {
                dataStore.edit {
                    it.clear()
                }
            } catch (e: Exception) {
                loge(e.message ?: "", e)
            }
        }
    }

    object PreferenceKeys {
        val USER_KEY = stringPreferencesKey("USER_KEY")
    }

    companion object {
        const val USER_PREFS = "user_prefs"
    }
}