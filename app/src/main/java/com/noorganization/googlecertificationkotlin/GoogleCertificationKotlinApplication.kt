package com.noorganization.googlecertificationkotlin

import android.app.Application
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di.AppComponent
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di.DaggerAppComponent

open class GoogleCertificationKotlinApplication : Application() {
    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.factory().create(applicationContext)
    }
}