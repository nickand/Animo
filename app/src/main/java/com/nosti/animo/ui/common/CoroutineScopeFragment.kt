package com.nosti.animo.ui.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class CoroutineScopeFragment : Fragment(), CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = SupervisorJob()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel() // Cancel job on activity/fragment destroy. After destroy all children jobs will be cancelled automatically
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job.cancel() // Cancel job on activity/fragment destroy. After destroy all children jobs will be cancelled automatically
    }
}