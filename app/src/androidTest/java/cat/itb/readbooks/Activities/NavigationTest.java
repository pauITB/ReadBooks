package cat.itb.readbooks.Activities;



import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cat.itb.readbooks.R;

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
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
public class NavigationTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void login_go_GridFragment_go_EditFragment(){
        loginFragment_to_GridFragment();
        onView(withId(R.id.floatingActionButton)).perform(click());
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rvBooks),
                        childAtPosition(
                                withId(R.id.booksGridFragment),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(1, click()));
        onView(withId(R.id.edit_book_fragment)).check(matches(isDisplayed()));
    }

    @Test
    public void login_go_GridFragment_go_AddFragment(){
        loginFragment_to_GridFragment();
        onView(withId(R.id.floatingActionButton)).perform(click());
        onView(withId(R.id.addBookFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void login_go_register_go_GridFragment(){
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
    public void loginFragment_to_GridFragment() {
        onView(withId(R.id.logInFragment)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.username_loginET)).perform(typeText("Pepe")).perform(closeSoftKeyboard());
        onView(withId(R.id.password_loginET)).perform(typeText("Passwordos")).perform(closeSoftKeyboard());
        onView(withId(R.id.login_button_login)).perform(click());
        onView(withId(R.id.booksGridFragment)).check(matches(isCompletelyDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}
