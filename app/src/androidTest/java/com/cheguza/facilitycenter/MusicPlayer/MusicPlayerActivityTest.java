package com.cheguza.facilitycenter.MusicPlayer;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.cheguza.facilitycenter.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MusicPlayerActivityTest {

    @Rule
    public ActivityTestRule<MusicPlayerActivity> activityTestRule=new ActivityTestRule<>(MusicPlayerActivity.class);
    private MusicPlayerActivity activity=null;

    @Before
    public void setUp() throws Exception {
        activity=activityTestRule.getActivity();
    }

    @Test
    public void testLaunch()
    {
        View view=activity.findViewById(R.id.play_selected_song);
        View view1=activity.findViewById(R.id.skip_next);
        assertNotNull(view);
        assertNotNull(view1);

    }
    @After
    public void tearDown() throws Exception {
        activity=null;
    }
}