package com.example.meetup.vmtests

import com.example.meetup.view_model.PermissionTestViewModel
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class PermissionTestViewModelTest {
    // Based on official tutorial:
    // https://developer.android.com/codelabs/basic-android-kotlin-compose-test-viewmodel

    private val viewModel = PermissionTestViewModel()

    @Test
    fun permissionViewModel_Initialization_PermissionFalse() {
        // Boundary case

        assertFalse(viewModel.performLocationAction.value)
    }

    @Test
    fun permissionViewModel_Requesting_PermissionTrue() {
        viewModel.setPerformLocationAction(true)
        assertTrue(viewModel.performLocationAction.value)
    }
}