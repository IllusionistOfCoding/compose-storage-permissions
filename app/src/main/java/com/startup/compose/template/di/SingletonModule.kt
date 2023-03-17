package com.startup.compose.template.di

import android.content.Context
import androidx.annotation.StringRes
import com.startup.compose.template.ui.route.EventManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {
    @Provides
    fun eventManager(): EventManager = EventManager

    @Singleton
    class ResourceProvider @Inject constructor(
        @ApplicationContext private val context: Context
    ) {
        fun getString(@StringRes stringResId: Int): String {
            return context.getString(stringResId)
        }
    }
}