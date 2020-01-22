/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 5:02 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 12/13/18 5:38 PM
 *
 */

package com.example.mapapp.injection

import com.example.mapapp.MapsActivity
import com.example.mapapp.injection.modules.MapActivityModule
import com.example.mapapp.injection.scope.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjector {


    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(MapActivityModule::class))
    abstract fun contributeMainActivityInjector(): MapsActivity

}