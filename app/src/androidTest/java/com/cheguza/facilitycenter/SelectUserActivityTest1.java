package com.cheguza.facilitycenter;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import com.cheguza.facilitycenter.Home.HomeActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.action.ViewActions.click;
import static org.junit.Assert.*;

public class SelectUserActivityTest1{

    @Rule
    public ActivityTestRule<SelectUserActivity> activityTestRule=new ActivityTestRule<>(SelectUserActivity.class);
    private SelectUserActivity selectUserActivity=null;
    Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(HomeActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {

        selectUserActivity=activityTestRule.getActivity();

    }
    @Test
    public void testLaunchofMusicActivity()
    {
        assertNotNull(selectUserActivity.findViewById(R.id.login_btn));
        Activity homeActivity= getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(homeActivity);
        homeActivity.finish();

    }

    @After
    public void tearDown() throws Exception {
        selectUserActivity=null;
    }
}