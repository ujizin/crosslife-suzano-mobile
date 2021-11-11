package br.com.crosslife.commons.utils

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

object Saver {

    @ExperimentalPagerApi
    fun getPagerState(
        offScreenLimit: Int = 1,
        infiniteLoop: Boolean = false,
    ): Saver<PagerState, *> = listSaver(
        save = {
            listOf(it.pageCount, it.currentPage)
        },
        restore = {
            PagerState(
                pageCount = it[0],
                currentPage = it[1],
                offscreenLimit = offScreenLimit,
                infiniteLoop = infiniteLoop,
            )
        }
    )
}