package com.example.meetup.uitest

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.example.meetup.api.MockFriendsApi
import com.example.meetup.screen.NewMeeting
import com.example.meetup.screen.Profile
import com.example.meetup.user_api.MockUserApi
import com.example.meetup.view_model.FriendsViewModel
import com.example.meetup.view_model.UserViewModel
import org.junit.Rule
import org.junit.Test

class NewMeetingUITest {
    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    //private val friendsViewModel = FriendsViewModel(MockFriendsApi())


    @Test
    fun newMeetingUI_ColorPickerResponse() {
        // Start the app
        composeTestRule.setContent {
            NewMeeting(navController = rememberNavController() )
        }

        composeTestRule.onNodeWithText("Choose pin color").assertHasClickAction()

        //composeTestRule.onAllNodes()
    }

}