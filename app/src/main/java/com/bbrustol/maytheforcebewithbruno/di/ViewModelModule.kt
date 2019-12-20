package com.bbrustol.maytheforcebewithbruno.di

import com.bbrustol.maytheforcebewithbruno.data.infraestructure.UI
import com.bbrustol.maytheforcebewithbruno.presentation.list_navigation.detail.StarwarsPeopleDetailViewModel
import com.bbrustol.maytheforcebewithbruno.presentation.list_navigation.starwarsPeopleList.StarwarsPeopleListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { StarwarsPeopleListViewModel(get(), get(), UI) }

    single { StarwarsPeopleDetailViewModel(get(),get(), UI) }
}
