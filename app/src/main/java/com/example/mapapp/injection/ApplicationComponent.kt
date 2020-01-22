/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 4:47 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/22/20 4:47 PM
 *
 */

package com.example.mapapp.injection

import android.app.Application
import com.example.mapapp.AndroidApplication
import com.example.mapapp.injection.modules.ApplicationModule
import com.example.mapapp.injection.modules.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        ApplicationModule::class,
        AndroidInjectionModule::class,
        DataModule::class,
        ActivityInjector::class
    )
)
interface ApplicationComponent {

    fun inject(application: AndroidApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}