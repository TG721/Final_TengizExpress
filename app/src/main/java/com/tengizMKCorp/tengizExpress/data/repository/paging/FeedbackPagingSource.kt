package com.tengizMKCorp.tengizExpress.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tengizMKCorp.tengizExpress.data.remote.StoreApi
import com.tengizMKCorp.tengizExpress.data.remote.model.feedback.FeedbackDoc

class FeedbackPagingSource(
    private val storeApi: StoreApi,
    private val productID: Long,
) : PagingSource<Int, FeedbackDoc>() {
    override fun getRefreshKey(state: PagingState<Int, FeedbackDoc>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FeedbackDoc> {
        return try {
            val currentPage = params.key ?: 1
            val response = storeApi.getProductFeedbacksByID(productID, currentPage)
            val data = response.body()?.docs ?: emptyList()
            val responseData = mutableListOf<FeedbackDoc>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}