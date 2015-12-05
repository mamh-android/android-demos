package demo.example.mamh.welcomedemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;

public class MainActivity extends Activity {

    public static MainActivity instance = null;

    private ViewPager mTabPager;//声明对象
    private ImageView mTabImg;// 动画图片
    private ImageView mTab1, mTab2, mTab3, mTab4;
    private int zero = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int one;// 单个水平动画位移
    private int two;
    private int three;
    private LinearLayout mClose;
    private LinearLayout mCloseBtn;
    private View layout;
    private boolean menu_display = false;
    private PopupWindow menuWindow;
    private LayoutInflater inflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);// 启动activity时不自动弹出软键盘
        instance = this;

        mTabPager = (ViewPager) findViewById(R.id.tabpager);
        mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());

        mTab1 = (ImageView) findViewById(R.id.img_weixin);
        mTab2 = (ImageView) findViewById(R.id.img_address);
        mTab3 = (ImageView) findViewById(R.id.img_friends);
        mTab4 = (ImageView) findViewById(R.id.img_settings);

        mTabImg = (ImageView) findViewById(R.id.img_tab_now);//动画图片

        mTab1.setOnClickListener(new MyOnClickListener(0));
        mTab2.setOnClickListener(new MyOnClickListener(1));
        mTab3.setOnClickListener(new MyOnClickListener(2));
        mTab4.setOnClickListener(new MyOnClickListener(3));

        Display currDisplay = getWindowManager().getDefaultDisplay();// 获取屏幕当前分辨率
        int displayWidth = currDisplay.getWidth();
        one = displayWidth / 4; // 设置水平动画平移大小
        two = one * 2;
        three = one * 3;

        // 将要分页显示的View装入数组中
        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.main_tab_weixin, null);
        View view2 = mLi.inflate(R.layout.main_tab_address, null);
        View view3 = mLi.inflate(R.layout.main_tab_friends, null);
        View view4 = mLi.inflate(R.layout.main_tab_settings, null);

        // 每个页面的view数据
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        // 填充ViewPager的数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public void destroyItem(View container, int position, Object object) {
                ((ViewPager) container).removeView(views.get(position));
            }


            @Override
            public Object instantiateItem(View container, int position) {
                ((ViewPager) container).addView(views.get(position));
                return views.get(position);
            }
        };

        mTabPager.setAdapter(mPagerAdapter);
    }

    /**
     * 头标点击监听
     */
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            mTabPager.setCurrentItem(index);
        }
    }

    ;

    /*
     * 页卡切换监听(原作者:D.Winter)
     */
    public class MyOnPageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
                case 0:
                    mTab1.setImageDrawable(getResources().getDrawable(
                            R.drawable.btn_green_normal));
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(one, 0, 0, 0);
                        mTab2.setImageDrawable(getResources().getDrawable(
                                R.drawable.btn_green_pressed));
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, 0, 0, 0);
                        mTab3.setImageDrawable(getResources().getDrawable(
                                R.drawable.btn_green_pressed));
                    } else if (currIndex == 3) {
                        animation = new TranslateAnimation(three, 0, 0, 0);
                        mTab4.setImageDrawable(getResources().getDrawable(
                                R.drawable.btn_green_pressed));
                    }
                    break;
                case 1:
                    mTab2.setImageDrawable(getResources().getDrawable(
                            R.drawable.btn_green_pressed));
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(zero, one, 0, 0);
                        mTab1.setImageDrawable(getResources().getDrawable(
                                R.drawable.btn_green_pressed));
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, one, 0, 0);
                        mTab3.setImageDrawable(getResources().getDrawable(
                                R.drawable.btn_green_pressed));
                    } else if (currIndex == 3) {
                        animation = new TranslateAnimation(three, one, 0, 0);
                        mTab4.setImageDrawable(getResources().getDrawable(
                                R.drawable.btn_green_pressed));
                    }
                    break;
                case 2:
                    mTab3.setImageDrawable(getResources().getDrawable(
                            R.drawable.btn_green_pressed));
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(zero, two, 0, 0);
                        mTab1.setImageDrawable(getResources().getDrawable(
                                R.drawable.btn_green_pressed));
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(one, two, 0, 0);
                        mTab2.setImageDrawable(getResources().getDrawable(
                                R.drawable.btn_green_pressed));
                    } else if (currIndex == 3) {
                        animation = new TranslateAnimation(three, two, 0, 0);
                        mTab4.setImageDrawable(getResources().getDrawable(
                                R.drawable.btn_green_pressed));
                    }
                    break;
                case 3:
                    mTab4.setImageDrawable(getResources().getDrawable(
                            R.drawable.btn_green_pressed));
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(zero, three, 0, 0);
                        mTab1.setImageDrawable(getResources().getDrawable(
                                R.drawable.btn_green_pressed));
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(one, three, 0, 0);
                        mTab2.setImageDrawable(getResources().getDrawable(
                                R.drawable.btn_green_pressed));
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, three, 0, 0);
                        mTab3.setImageDrawable(getResources().getDrawable(
                                R.drawable.btn_green_pressed));
                    }
                    break;
            }
            currIndex = arg0;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(150);// 动画持续时间
            mTabImg.startAnimation(animation);// 开始动画
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    }
}