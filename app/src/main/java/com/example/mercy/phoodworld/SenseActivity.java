package com.example.mercy.phoodworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

/**
 * Created by mercy on 10/9/2017.
 */

public class SenseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tabsample);

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        final TabWidget tabWidget = tabHost.getTabWidget();
        final FrameLayout tabContent = tabHost.getTabContentView();

        // Get the original tab textviews and remove them from the viewgroup.
        TextView[] originalTextViews = new TextView[tabWidget.getTabCount()];
        for (int index = 0; index < tabWidget.getTabCount(); index++) {
            originalTextViews[index] = (TextView) tabWidget.getChildTabViewAt(index);
        }
        tabWidget.removeAllViews();

        // Ensure that all tab content childs are not visible at startup.
        for (int index = 0; index < tabContent.getChildCount(); index++) {
            tabContent.getChildAt(index).setVisibility(View.GONE);
        }

        // Create the tabspec based on the textview childs in the xml file.
        // Or create simple tabspec instances in any other way...
        for (int index = 0; index < originalTextViews.length; index++) {
            final TextView tabWidgetTextView = originalTextViews[index];
            final View tabContentView = tabContent.getChildAt(index);
            TabHost.TabSpec tabSpec = tabHost.newTabSpec((String) tabWidgetTextView.getTag());
            tabSpec.setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    return tabContentView;
                }
            });
            if (tabWidgetTextView.getBackground() == null) {
                tabSpec.setIndicator(tabWidgetTextView.getText());
            } else {
                tabSpec.setIndicator(tabWidgetTextView.getText(), tabWidgetTextView.getBackground());
            }
            tabHost.addTab(tabSpec);
        }

//		tabHost.setCurrentTab(0);
    }
}
