package com.noorganization.googlecertificationkotlin.codelab_workmanager_mvvm_repository.devbyteviewer.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.noorganization.googlecertificationkotlin.codelab_workmanager_mvvm_repository.devbyteviewer.database.getDatabase
import com.noorganization.googlecertificationkotlin.codelab_workmanager_mvvm_repository.devbyteviewer.repository.VideosRepository
import retrofit2.HttpException
import timber.log.Timber

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "com.example.android.devbyteviewer.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = VideosRepository(database)
        try {
            repository.refreshVideos()
            Timber.d("Work request for sync is run")
        } catch (e: HttpException) {
            return Result.retry()
        }

        return Result.success()
    }
}