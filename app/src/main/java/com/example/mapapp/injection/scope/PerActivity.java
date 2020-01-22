/*
 * Created by Anselmo Jose Munoz Medina on 1/22/20 5:04 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 12/1/17 1:54 PM
 *
 */

package com.example.mapapp.injection.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
