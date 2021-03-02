package com.neolab.mvvm_architecture.common.base

import com.neolab.mvvm_architecture.utils.DataResult
import com.neolab.mvvm_architecture.utils.test.wrapEspressoIdlingResource
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {

    /**
     * Make template code to get DataResult return to ViewModel
     * Support for call api, get data from database
     * Handle exceptions: Convert exception to Result.Error
     * Avoid duplicate code
     *
     * Default CoroutineContext is IO for repository
     */
    protected suspend fun <R> withResultContext(
        context: CoroutineContext = Dispatchers.IO,
        errorBlock: (suspend CoroutineScope.(Exception) -> DataResult.Error)? = null,
        requestBlock: suspend CoroutineScope.() -> R
    ): DataResult<R> = wrapEspressoIdlingResource {
        withContext(context) {
            return@withContext try {
                val response = requestBlock()
                DataResult.Success(response)
            } catch (e: Exception) {
                e.printStackTrace()
                errorBlock?.invoke(this, e) ?: DataResult.Error(e)
            }
        }
    }

    protected suspend fun <R> withRepositoryContext(
        context: CoroutineContext = Dispatchers.IO,
        block: suspend CoroutineScope.() -> R
    ) = wrapEspressoIdlingResource { withContext(context, block) }
}
