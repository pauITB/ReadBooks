package cat.itb.readbooks;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cat.itb.readbooks.Activities.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
public class LoginRegisterTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void login_go_GridFragment(){
        onView(withId(R.id.logInFragment)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.username_loginET)).perform(typeText("Pepe")).perform(closeSoftKeyboard());
        onView(withId(R.id.password_loginET)).perform(typeText("Passwordos")).perform(closeSoftKeyboard());
        onView(withId(R.id.login_button_login)).perform(click());
        onView(withId(R.id.booksGridFragment)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void register_go_GridFragment(){
        onView(withId(R.id.logInFragment)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.login_register_button)).perform(click());
        onView(withId(R.id.registerFragment)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.username_registerET)).perform(typeText("Pepe")).perform(closeSoftKeyboard());
        onView(withId(R.id.password_register2ET)).perform(typeText("Passwordos")).perform(closeSoftKeyboard());
        onView(withId(R.id.password_registerET)).perform(typeText("Passwordos")).perform(closeSoftKeyboard());
        onView(withId(R.id.emailET)).perform(typeText("Email")).perform(closeSoftKeyboard());
        onView(withId(R.id.contract)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.register_button)).perform(click());
        onView(withId(R.id.booksGridFragment)).check(matches(isCompletelyDisplayed()));
    }


}
