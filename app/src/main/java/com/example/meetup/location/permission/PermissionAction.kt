package com.example.meetup.location.permission

sealed class PermissionAction {

    object OnPermissionGranted : PermissionAction()

    object OnPermissionDenied : PermissionAction()
}