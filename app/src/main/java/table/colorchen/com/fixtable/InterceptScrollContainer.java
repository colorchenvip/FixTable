package table.colorchen.com.fixtable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * 视图容器
 * 阻止拦截 事件分发
 * Created by color on 15/12/2016 00:29.
 */

public class InterceptScrollContainer extends LinearLayout {
    public InterceptScrollContainer(Context context) {
        super(context);
    }

    public InterceptScrollContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptScrollContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
