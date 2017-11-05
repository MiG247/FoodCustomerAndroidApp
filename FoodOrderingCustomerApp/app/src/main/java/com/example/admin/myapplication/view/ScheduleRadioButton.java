package com.example.admin.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.admin.myapplication.utils.ScreenUtils;

/**
 * Created by GogHox on 2017/10/30.
 */

public class ScheduleRadioButton extends RadioButton {
    public ScheduleRadioButton(Context context) {

        super(context, null);
    }

    public ScheduleRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public ScheduleRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.setPadding(ScreenUtils.dp2px(getContext(),10f), ScreenUtils.dp2px(getContext(),10f),
                ScreenUtils.dp2px(getContext(),10f), ScreenUtils.dp2px(getContext(),10f));
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(layoutParams);
    }

}
