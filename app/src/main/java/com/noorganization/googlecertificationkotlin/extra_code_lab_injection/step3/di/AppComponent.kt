package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di

import android.content.Context
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di.subcomponent.LoginComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
// Definition of a Dagger component that adds info from the different modules to the graph
@Component()
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun loginComponent(): LoginComponent.Factory
}