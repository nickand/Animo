package com.nosti.animo

import android.app.Application
import com.nosti.animo.data.database.AnimeDatabase
import com.nosti.animo.data.database.RoomDataSource
import com.nosti.animo.data.server.KitsuDataSource
import com.nosti.animo.ui.detail.DetailFragment
import com.nosti.animo.ui.detail.DetailViewModel
import com.nosti.animo.ui.favorite.FavoriteFragment
import com.nosti.animo.ui.favorite.FavoriteViewModel
import com.nosti.animo.ui.main.AnimoFragment
import com.nosti.animo.ui.main.AnimoViewModel
import com.nosti.animo.ui.search.SearchFragment
import com.nosti.animo.ui.search.SearchViewModel
import com.nosti.data.AnimeRepository
import com.nosti.data.LocalDataSource
import com.nosti.data.RemoteDataSource
import com.nosti.usecases.FindAnimeById
import com.nosti.usecases.GetPopularAnimes
import com.nosti.usecases.ToggleAnimeFavorite
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single { AnimeDatabase.build(get()) }
    factory<LocalDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { KitsuDataSource() }
    single<CoroutineDispatcher> { Dispatchers.Main }
}

private val dataModule = module {
    factory { AnimeRepository(get(), get()) }
}

private val scopesModule = module {
    scope(named<AnimoFragment>()) {
        viewModel { AnimoViewModel(get(), get()) }
        scoped { GetPopularAnimes(get()) }
    }

    scope(named<DetailFragment>()) {
        viewModel { (id: Int) -> DetailViewModel(id, get(), get(), get()) }
        scoped { FindAnimeById(get()) }
        scoped { ToggleAnimeFavorite(get()) }
    }

    scope(named<FavoriteFragment>()) {
        viewModel { FavoriteViewModel(get(), get()) }
        scoped { GetPopularAnimes(get()) }
    }

    scope(named<SearchFragment>()) {
        viewModel { SearchViewModel(get(), get()) }
        scoped { GetPopularAnimes(get()) }
    }
}