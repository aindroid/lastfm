package ainhoamoreno.com.lastfm.common.ui;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;

public class SquareAppBarLayout extends AppBarLayout {

    public SquareAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int newMeasureSpec = MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY);
        super.onMeasure(newMeasureSpec, newMeasureSpec);
    }
}
