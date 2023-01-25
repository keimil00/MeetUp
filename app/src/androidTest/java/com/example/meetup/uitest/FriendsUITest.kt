package com.example.meetup.uitest

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.meetup.api.MockEventsApi
import com.example.meetup.api.MockFriendsApi
import com.example.meetup.component.AddFriendDialog
import com.example.meetup.event.dto.NewEventRequestBody
import com.example.meetup.model.Event
import com.example.meetup.screen.Friends
import com.example.meetup.screen.Home
import com.example.meetup.user_api.MockUserApi
import com.example.meetup.view_model.EventViewModel
import com.example.meetup.view_model.FriendsViewModel
import com.example.meetup.view_model.UserViewModel
import org.junit.Rule
import org.junit.Test
import java.lang.Boolean

class FriendsUITest {
    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    private val viewModel = FriendsViewModel(MockFriendsApi(initialList))

    @Test
    fun friendsUI_CorrectDefaultLayoutDisplay() {
        composeTestRule.setContent {
            Friends(navController = rememberNavController(), viewModel)
        }
        composeTestRule.onNode(hasTestTag("Friends FAB")).assertIsDisplayed().assertHasClickAction()
        composeTestRule.onNode(hasTestTag("Menu icon button")).assertIsDisplayed().assertHasClickAction()
    }

    @Test
    fun friendsUI_AbilityToLogout() {

        composeTestRule.setContent { Friends(navController = rememberNavController(), viewModel) }

        composeTestRule.onNode(hasTestTag("Menu icon button")).assertHasClickAction()
        composeTestRule.onNode(hasTestTag("Menu icon button")).assertIsDisplayed()
        composeTestRule.onNode(hasTestTag("Menu icon button")).performClick()

        composeTestRule.onNodeWithText("Logout").assertHasClickAction()
        composeTestRule.onNodeWithText("Logout").assertIsDisplayed()
    }

    @Test
    fun friendsUI_CheckDisplayedFriendsCount() {
        composeTestRule.setContent { Friends(navController = rememberNavController(), viewModel) }

        composeTestRule.onNode(hasTestTag("Friends list column")).onChildren().assertCountEquals(3)
    }


    @Test
    fun friendsUI_TryAddingNonExistingFriend() {
        composeTestRule.setContent {
            AddFriendDialog("Add friend", mutableStateOf(true), viewModel)
        }

        composeTestRule.onNode(hasTestTag("Add friend TextField")).assertExists()
            .performTextInput("TestFriend")

        composeTestRule.onNodeWithText("Add").assertExists().performClick()
    }


    @Test
    fun friendsUI_AbilityToSeeProfile() {
        composeTestRule.setContent {
            Friends(navController = rememberNavController(), viewModel)
        }

        composeTestRule.onNode(hasTestTag("Menu icon button")).assertIsDisplayed()
        composeTestRule.onNode(hasTestTag("Menu icon button")).assertHasClickAction()
        composeTestRule.onNode(hasTestTag("Menu icon button")).performClick()
        composeTestRule.onNodeWithText("Profile").assertIsDisplayed()
        composeTestRule.onNodeWithText("Profile").assertHasClickAction()
    }

    @Test
    fun friendsUI_TryAddingNewFriendLayoutDisplay() {
        composeTestRule.setContent {
            AddFriendDialog("Add friend", mutableStateOf(true), viewModel)
        }

        composeTestRule.onNodeWithText("Add friend").assertExists()
        composeTestRule.onNodeWithText("Enter users email").assertExists()
        composeTestRule.onNodeWithText("Cancel").assertExists().assertHasClickAction()
        composeTestRule.onNodeWithText("Add").assertExists().assertHasClickAction()
    }

    @Test
    fun friendsUI_AbilityToSeeAbout() {
        composeTestRule.setContent {
            Friends(navController = rememberNavController(), viewModel)
        }

        composeTestRule.onNode(hasTestTag("Menu icon button")).assertIsDisplayed()
        composeTestRule.onNode(hasTestTag("Menu icon button")).assertHasClickAction().performClick()
        composeTestRule.onNodeWithText("About").assertHasClickAction()
        composeTestRule.onNodeWithText("About").assertIsDisplayed()

    }

    companion object {
        val initialList = mutableListOf(mockFriend1, mockFriend2, mockFriend3)
    }
}