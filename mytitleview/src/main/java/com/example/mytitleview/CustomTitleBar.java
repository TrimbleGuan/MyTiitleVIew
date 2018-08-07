package com.example.mytitleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomTitleBar extends LinearLayout {

    private Context mContext;
    private int back_imgeview;
    private int menu_imgeview;
    private String title_text;
    private int text_color;
    private float title_size;
    private boolean leftVisibility;
    private boolean textVisibility;
    private boolean rightVisibility;

    public CustomTitleBar(Context context) {
        super(context);
        mContext = context;
        initCustomTitleBar();
    }

    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        //获取自定义attrs属性
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        back_imgeview = ta.getResourceId(R.styleable.CustomTitleBar_back_imgeview, -1);
        menu_imgeview = ta.getResourceId(R.styleable.CustomTitleBar_menu_imgeview, -1);
        title_text = ta.getString(R.styleable.CustomTitleBar_title_text);
        text_color = ta.getColor(R.styleable.CustomTitleBar_title_text_color, -1);
        title_size = ta.getDimension(R.styleable.CustomTitleBar_title_size, -1);

        //
        leftVisibility = ta.getBoolean(R.styleable.CustomTitleBar_left_visibility, true);
        textVisibility = ta.getBoolean(R.styleable.CustomTitleBar_text_visibility, true);
        rightVisibility = ta.getBoolean(R.styleable.CustomTitleBar_right_visibility, true);
        //优化
        ta.recycle();
        initCustomTitleBar();
    }

    public CustomTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initCustomTitleBar();
    }

    private void initCustomTitleBar() {
        this.setOrientation(LinearLayout.HORIZONTAL);
        this.setGravity(Gravity.CENTER_VERTICAL);
        //左边图片
        LayoutParams mLayoutParams1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        ImageView mImageView = new ImageView(mContext);
        mImageView.setImageResource(back_imgeview);
        mImageView.setVisibility(leftVisibility ? VISIBLE : INVISIBLE);
        addView(mImageView, mLayoutParams1);
        //标题
        LayoutParams mLayoutParams2 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
        TextView mTextView = new TextView(mContext);
        mTextView.setVisibility(textVisibility ? VISIBLE : INVISIBLE);
        mTextView.setText(title_text);
        mTextView.setTextColor(text_color);
        mTextView.setTextSize(title_size);
        mTextView.setGravity(Gravity.CENTER);
        addView(mTextView, mLayoutParams2);
        //右边图片
        LayoutParams mLayoutParams3 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        ImageView mImageView2 = new ImageView(mContext);
        mImageView2.setImageResource(menu_imgeview);
        mImageView2.setVisibility(rightVisibility ? VISIBLE : INVISIBLE);
        addView(mImageView2, mLayoutParams3);


        mImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
              if(onClickListener!=null){
                  onClickListener.onLeftClick();
              }
            }
        });
        mImageView2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener!=null){
                    onClickListener.onRightClick();
                }
            }
        });
    }
    //接口回调方法
    onClickListener onClickListener;

    public void setOnClickListener(CustomTitleBar.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    //接口
    public interface onClickListener {
        void onLeftClick();
        void onRightClick();
    }

}