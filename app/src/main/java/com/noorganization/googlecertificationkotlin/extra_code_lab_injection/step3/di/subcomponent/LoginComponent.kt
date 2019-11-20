package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di.subcomponent

import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.LoginDaggerActivity
import dagger.Subcomponent

// Definition of a Dagger subcomponent
@Subcomponent
interface LoginComponent {

    // Factory to create instances of LoginComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: LoginDaggerActivity)
}