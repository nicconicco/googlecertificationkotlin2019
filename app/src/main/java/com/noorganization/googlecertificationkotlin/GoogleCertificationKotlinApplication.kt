package com.noorganization.googlecertificationkotlin

import android.app.Application
import androidx.work.*
import com.noorganization.googlecertificationkotlin.codelab_workmanager_mvvm_repository.devbyteviewer.work.RefreshDataWorker
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di.AppComponent
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di.DaggerAppComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

open class GoogleCertificationKotlinApplication : Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent =
        DaggerAppComponent.factory().create(applicationContext)

    override fun onCreate() {
        super.onCreate()

        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            if(BuildConfig.DEBUG) {
                Timber.plant(Timber.DebugTree())
            }
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {
        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }
}