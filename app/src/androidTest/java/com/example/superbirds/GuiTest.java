package com.example.superbirds;

import android.widget.ImageView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static android.app.PendingIntent.getActivity;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class GuiTest {

  @Rule
  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);


  @Test
  public void posYChangesToGreaterValueWhenClickTest() {
    GameObject bird = activityRule.getActivity().currentGame.bird;
    Float currentY = bird.getY();

    onView(withId(R.id.imageButton)).perform(click());

    Assert.assertTrue(activityRule.getActivity().currentGame.bird.getY() > currentY);
  }
}
