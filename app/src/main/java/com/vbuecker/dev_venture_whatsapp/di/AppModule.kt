package com.vbuecker.dev_venture_whatsapp.di

import com.vbuecker.dev_venture_whatsapp.data.repository.UserRepository
import com.vbuecker.dev_venture_whatsapp.fragments.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val module = module {
        factory {
            UserRepository
        }
        viewModel {
            ContactsViewModel(get())
        }
    }
}