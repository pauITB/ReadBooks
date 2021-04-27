package cat.itb.readbooks;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cat.itb.readbooks.Activities.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class UseCaseTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void login_Check(){
        onView(withId(R.id.logInFragment)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.username_loginET)).perform(typeText("Pepe")).perform(closeSoftKeyboard());
        onView(withId(R.id.password_loginET)).perform(typeText("Passwordos")).perform(closeSoftKeyboard());
        onView(withId(R.id.login_button_login)).perform(click());
        onView(withId(R.id.booksGridFragment)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void register_check(){
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

    @Test
    public void addBooks_check(){
        login_Check();
        onView(withId(R.id.floatingActionButton)).perform(click());
        onView(withId(R.id.addBookFragment)).check(matches(isDisplayed()));
        onView(withId(R.id.titulo_edit_text)).perform(typeText("Pinocho"));
        onView(withId(R.id.autor_edit_text)).perform(typeText("Gepeto"));
        onView(withId(R.id.add_button)).perform(click());
        onView(withId(R.id.booksGridFragment)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.rvBooks)).perform(RecyclerViewActions.actionOnItemAtPosition(2,click()));
    }

}
