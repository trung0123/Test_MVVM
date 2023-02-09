package jilnesta.com.testmvvm.ui.base

import androidx.lifecycle.ViewModel
import jilnesta.com.testmvvm.usecase.errors.ErrorManager
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    @Inject
    lateinit var errorManager: ErrorManager
}