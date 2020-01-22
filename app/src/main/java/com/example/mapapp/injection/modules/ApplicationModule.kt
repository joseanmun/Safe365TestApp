/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 4:49 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/22/20 4:49 PM
 *
 */

package com.example.mapapp.injection.modules

import android.app.Application
import android.content.Context
import com.example.data.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    @ApplicationContext
    internal fun provideContext(application: Application): Context {
        return application
    }

}