package com.krayushkindev.allsounds.di

import android.content.Context
import com.krayushkindev.allsounds.common.resource.StringResourceImpl
import com.krayushkindev.allsounds.core.resource.StringResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
class ResourceModule {

    @Provides
    fun provideStringResource(@ApplicationContext context: Context): StringResource {
        return StringResourceImpl().setupContext(context)
    }
}