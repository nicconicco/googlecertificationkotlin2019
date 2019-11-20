package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di

import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di.subcomponent.LoginComponent
import dagger.Module

// This module tells a Component which are its subcomponents
@Module(
    subcomponents = [
        LoginComponent::class
    ]
)
class AppSubcomponents