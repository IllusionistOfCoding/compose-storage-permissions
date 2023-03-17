package com.startup.compose.template.di

import com.startup.compose.template.manager.INetworkManager
import com.startup.compose.template.manager.NetworkManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
@Module
@InstallIn(SingletonComponent::class)
abstract class ManagerModule {

    @Binds
    abstract fun bindNetworkManager(
        networkManager: NetworkManager
    ): INetworkManager

}