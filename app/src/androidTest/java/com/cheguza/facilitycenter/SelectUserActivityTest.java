package com.cheguza.facilitycenter;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SelectUserActivityTest {

    @Rule
    public ActivityTestRule<SelectUserActivity> mActivityTestRule=new ActivityTestRule<>(SelectUserActivity.class);

    private SelectUserActivity mActivity=null;
    @Before
    public void setUp() throws Exception {

        mActivity=mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch()
    {
        View view=mActivity.findViewById(R.id.AdminUser);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
    }
}