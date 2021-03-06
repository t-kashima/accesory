package com.unuuu.android.accesory.sample.activities

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import android.test.ActivityInstrumentationTestCase2
import android.view.WindowManager
import com.unuuu.android.accesory.R
import com.unuuu.android.accesory.Accesory
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SecondActivityTest : ActivityInstrumentationTestCase2<SecondActivity> {
    constructor() : super(SecondActivity::class.java) {
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
    public fun testAccesoryIsNotDisplayed() {
        // change the view in ui thread
        activity.runOnUiThread {
            Accesory.dismiss()
        }
        // wait for the ui thread to finish
        instrumentation.waitForIdleSync()
        onView(withId(R.id.overlay_image))
                .inRoot(withDecorView(`not`(`is`(activity.window.decorView))))
                .check(matches(not(isDisplayed())))
    }

    @After
    override public fun tearDown() {
        super.tearDown()
    }
}