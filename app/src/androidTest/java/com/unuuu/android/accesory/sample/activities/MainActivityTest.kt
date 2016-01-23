package com.unuuu.android.accesory.sample.activities

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName
import android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasMyPackageName
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import android.test.ActivityInstrumentationTestCase2
import android.view.WindowManager
import com.unuuu.android.accesory.R
import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest : ActivityInstrumentationTestCase2<MainActivity> {
    constructor() : super(MainActivity::class.java) {
    }

    private var windowManager : WindowManager? = null

    @Before
    override public fun setUp() {
        super.setUp()
        injectInstrumentation(InstrumentationRegistry.getInstrumentation())
        windowManager = activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    @Test
    public fun testAccesoryIsDisplayed() {
        onView(withId(R.id.overlay_image))
            .inRoot(withDecorView(`not`(`is`(activity.window.decorView))))
            .check(matches(isDisplayed()))
    }

    @Test
    public fun testCallbackIsCalledOnTouch() {
        Intents.init()

        onView(withId(R.id.overlay_image))
                .inRoot(withDecorView(`not`(`is`(activity.window.decorView))))
                .perform(click())

        intended(allOf(
                hasComponent(hasMyPackageName()),
                hasComponent(hasClassName("com.unuuu.android.accesory.sample.activities.SecondActivity"))
        ))

        Intents.release()
    }

    @After
    override public fun tearDown() {
        super.tearDown()
    }
}