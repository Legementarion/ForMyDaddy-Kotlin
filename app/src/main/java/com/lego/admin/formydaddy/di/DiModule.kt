package com.lego.admin.formydaddy.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.lego.admin.formydaddy.logic.Game
import com.lego.admin.formydaddy.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModule = module {
    viewModel { MainViewModel(get(), get()) }
}

private val coreModule = module {
    single { FirebaseAnalytics.getInstance(get()) }
    single { Game() }
}

val baseModule = coreModule +
        viewModule