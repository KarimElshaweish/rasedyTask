package com.example.karim.rasedytask.View;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.karim.rasedytask.R;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> activity=new ActivityTestRule<>(MainActivity.class);



    @Test
    public void testScroll() {
        Espresso.onView(ViewMatchers.withId(R.id.rv)).perform(RecyclerViewActions.scrollToPosition(activity.getActivity().rv.getAdapter().getItemCount()-1));
    }
    @Before

    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
 }