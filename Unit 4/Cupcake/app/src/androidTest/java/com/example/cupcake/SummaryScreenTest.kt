package com.example.cupcake

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import org.junit.Rule
import org.junit.Test

class SummaryScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun summaryScreen_verifyContent() {
        // Given a OrderUiState that contains some order data
        val orderUiState = OrderUiState(
            quantity = 3,
            flavor = "Vanilla",
            date = "Wed Jul 21",
            price = "$100.00"
        )

        // When OrderSummaryScreen is loaded
        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = orderUiState,
                onCancelButtonClicked = {},
                onSendButtonClicked = { subject: String, summary: String -> },
                modifier = Modifier.fillMaxHeight()
            )
        }

        // Then the order data is displayed correctly
        //composeTestRule.onNodeWithText("3").assertIsDisplayed()
        composeTestRule.onNodeWithText("Vanilla").assertIsDisplayed()
        composeTestRule.onNodeWithText("Wed Jul 21").assertIsDisplayed()
        composeTestRule.onNodeWithText("\$100.00").assertIsDisplayed()
    }
}