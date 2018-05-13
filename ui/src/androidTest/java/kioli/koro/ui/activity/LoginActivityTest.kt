package kioli.koro.ui.activity

import android.arch.lifecycle.ViewModel
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.microsoft.appcenter.espresso.Factory
import com.microsoft.appcenter.espresso.ReportHelper
import kioli.koro.presentation.viewmodel.LoginViewModel
import kioli.koro.presentation.viewmodel.ViewModelFactory
import kioli.koro.ui.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest {

    @get:Rule
    val rule = espressoDaggerMockRule()

    @get:Rule
    var activityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java, false, false)

    @get:Rule
    var reportHelper: ReportHelper = Factory.getReportHelper()

    private val mockViewModelFactory = mock(ViewModelFactory::class.java)
    private val mockLoginViewModel = mock(LoginViewModel::class.java)

    @Before
    fun setup() {
        Mockito.reset(mockLoginViewModel)
    }

    @Test
    fun checkInputFields() {
        `when`(mockViewModelFactory.create(any<Class<ViewModel>>())).thenReturn(mockLoginViewModel)
        doNothing().`when`(mockLoginViewModel).automaticLogin()
        activityRule.launchActivity(null)
        onView(withId(R.id.email_input_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.password_input_layout)).check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        reportHelper.label("Stopping App")
    }
}
