package com.example.meetup.uitest

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meetup.api.MockFriendsApi
import com.example.meetup.screen.Profile
import com.example.meetup.user_api.MockUserApi
import com.example.meetup.view_model.FriendsViewModel
import com.example.meetup.view_model.UserViewModel
import kotlinx.coroutines.currentCoroutineContext
import org.junit.Rule
import org.junit.Test
import org.w3c.dom.Text

class ProfileUITest {
    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity


    private val viewModel = UserViewModel(MockUserApi())


    @Test
    fun profileUI_CheckDisplayingContent_ClickableOptions() {
        // Start the app
        composeTestRule.setContent {
            Profile(navController = rememberNavController(), userViewModel = viewModel)
        }

        composeTestRule.onNodeWithText("Profil").assertIsDisplayed()

        //composeTestRule.onAllNodes()

        composeTestRule.onNodeWithText("See friends").assertHasClickAction()

        composeTestRule.onNodeWithText("See friends").performClick().assertExists()

        //composeTestRule.onAllNodes()
    }
    @Test
    fun profileUI_CheckDisplaying() {
        // Start the app
        composeTestRule.setContent {
            Profile(navController = rememberNavController(), userViewModel = viewModel)
        }
        //composeTestRule.onAllNodes()

        composeTestRule.onNodeWithText("See friends").assertHasClickAction()

        //composeTestRule.onAllNodes()
    }
    @Test
    fun profileUI_ClickableOptions() {
        // Start the app
        composeTestRule.setContent {
            Profile(navController = rememberNavController(), userViewModel = viewModel)
        }
        composeTestRule.onNodeWithText("See friends").performClick().assertExists()

        //composeTestRule.onAllNodes()
    }
}
