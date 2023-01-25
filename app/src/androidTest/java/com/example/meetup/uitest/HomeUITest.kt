package com.example.meetup.uitest

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.meetup.api.MockEventsApi
import com.example.meetup.event.dto.NewEventRequestBody
import com.example.meetup.model.Event
import com.example.meetup.screen.Home
import com.example.meetup.user_api.MockUserApi
import com.example.meetup.view_model.EventViewModel
import com.example.meetup.view_model.UserViewModel
import org.junit.Rule
import org.junit.Test

class HomeUITest {
    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    private val viewModel = EventViewModel(MockEventsApi(initialList))

    @Test
    fun homeUI_CorrectDefaultLayoutDisplay() {
        composeTestRule.setContent {
            Home(navController = rememberNavController(), viewModel)
        }
        composeTestRule.onNodeWithText("Map").assertIsDisplayed()
        composeTestRule.onNode(hasTestTag("Main home FAB")).assertIsDisplayed().assertHasClickAction()
        composeTestRule.onNode(hasTestTag("Menu icon button")).assertIsDisplayed()
    }

    @Test
    fun homeUI_AbilityToLogout(){
        composeTestRule.setContent {
            Home(navController = rememberNavController(), viewModel)
        }

        composeTestRule.onNode(hasTestTag("Menu icon button")).assertHasClickAction()
        composeTestRule.onNode(hasTestTag("Menu icon button")).assertIsDisplayed()
        composeTestRule.onNode(hasTestTag("Menu icon button")).performClick()

        composeTestRule.onNodeWithText("Logout").assertHasClickAction()
        composeTestRule.onNodeWithText("Logout").assertIsDisplayed()

    }

    @Test
    fun homeUI_AbilityToSeeProfile(){
        composeTestRule.setContent { Home(navController = rememberNavController(), viewModel) }

        composeTestRule.onNode(hasTestTag("Menu icon button")).assertIsDisplayed()
        composeTestRule.onNode(hasTestTag("Menu icon button")).assertHasClickAction()
        composeTestRule.onNode(hasTestTag("Menu icon button")).performClick()
        composeTestRule.onNodeWithText("Profile").assertIsDisplayed()
        composeTestRule.onNodeWithText("Profile").assertHasClickAction()
    }

    @Test
    fun homeUI_AbilityToSeeFriends(){
        composeTestRule.setContent {
            Home(navController = rememberNavController(), viewModel)
        }
        composeTestRule.onNode(hasTestTag("Menu icon button")).assertHasClickAction()
        composeTestRule.onNode(hasTestTag("Menu icon button")).assertIsDisplayed()
        composeTestRule.onNode(hasTestTag("Menu icon button")).performClick()

        composeTestRule.onNodeWithText("Friends").assertIsDisplayed()
        composeTestRule.onNodeWithText("Friends").assertHasClickAction()
    }

    @Test
    fun homeUI_AbilityToSeeAbout(){
        composeTestRule.setContent {
            Home(navController = rememberNavController(), viewModel)}

        composeTestRule.onNode(hasTestTag("Menu icon button")).assertIsDisplayed()
        composeTestRule.onNode(hasTestTag("Menu icon button")).assertHasClickAction().performClick()
        composeTestRule.onNodeWithText("About").assertHasClickAction()
        composeTestRule.onNodeWithText("About").assertIsDisplayed()

    }
    companion object {
        val initialList = mutableListOf(
            mockEvent1, mockEvent2
        )
    }
}
