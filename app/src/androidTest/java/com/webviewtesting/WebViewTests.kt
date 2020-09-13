package com.webviewtesting

import androidx.test.espresso.web.assertion.WebViewAssertions.webMatches
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.*
import androidx.test.espresso.web.webdriver.Locator.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class WebViewTests {

    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<MainActivity> = object : ActivityTestRule<MainActivity>(
        MainActivity::class.java, false, false
    ) {
            override fun afterActivityLaunched() {
                // Other WebViews may have javascript turned off, however since the only way
                // to automate WebViews is through javascript, it must be enabled.
                onWebView().forceJavascriptEnabled()
            }
        }

    @Before
    fun setUp() {
        activityTestRule.launchActivity(null)
    }

    @Test
    fun testInsideDeepHierarchy() {
        onWebView()
            .withElement(findElement(ID, "testingh1-2"))
    }

    @Test
    fun setInuputAndChangeText() {
        val TEXT = "This is the new text"
        onWebView()
            .withElement(findElement(ID, "textInput"))
            .perform(clearElement())
            .perform(webKeys(TEXT))
            .withElement(findElement(ID, "changeTextBtn"))
            .perform(webClick())
            .withElement(findElement(ID, "testingPar"))
            .check(webMatches(getText(), containsString(TEXT)))
    }

    @Test
    fun byClassName() {
        onWebView()
            .withElement(findElement(CLASS_NAME, "a"))
    }

    @Test
    fun byCssSelector() {
        onWebView()
            .withElement(findElement(CSS_SELECTOR, "#cssSelector"))
    }

    @Test
    fun byLinkText() {
        //Only works with links
        onWebView()
            .withElement(findElement(LINK_TEXT, "disney.com"))
    }

    @Test
    fun byPartialLinkText() {
        //Only works with links
        onWebView()
            .withElement(findElement(PARTIAL_LINK_TEXT, "disney"))
    }

    @Test
    fun byTag() {
        onWebView()
            .withElement(findElement(TAG_NAME, "input"))
    }

    @Test
    fun byXPath() {
        onWebView()
            .withElement(findElement(XPATH, "/html/body/div/h2"))
    }

    @Test
    fun scrollToView() {
        onWebView()
            .withElement(findElement(ID, "textInput"))
            .perform(webScrollIntoView())
            .withElement(findElement(ID, "textInput2"))
            .perform(webScrollIntoView())
    }

    @Test
    fun androidDevCheckTitle() {
        onWebView()
            .withElement(findElement(ID, "main"))
    }
}
