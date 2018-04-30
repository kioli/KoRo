package kioli.koro.ui.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.microsoft.appcenter.espresso.Factory
import com.microsoft.appcenter.espresso.ReportHelper
import kioli.koro.ui.R
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest{

    @Rule @JvmField
    var activityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    @Rule @JvmField
    var reportHelper: ReportHelper = Factory.getReportHelper()

    @Test
    fun checkInputFields() {
        onView(withId(R.id.email_input_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.password_input_layout)).check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        reportHelper.label("Stopping App")
    }
}
