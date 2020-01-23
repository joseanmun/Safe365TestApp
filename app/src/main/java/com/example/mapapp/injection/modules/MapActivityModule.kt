/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 5:19 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 12/11/18 12:10 PM
 *
 */

package com.example.mapapp.injection.modules

import com.example.mapapp.presentation.map.MapView
import com.example.mapapp.presentation.map.MapsActivity
import dagger.Module
import dagger.Provides

@Module
class MapActivityModule {

    @Provides
    internal fun provideMapsActivity(mapsActivity: MapsActivity): MapView = mapsActivity
}