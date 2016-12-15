package table.colorchen.com.fixtable;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * table fix column and row
 * Created by color on 15/12/2016 00:21.
 */

public class FixTableActivity extends AppCompatActivity {

    ListView mListView1;
    MyAdapter myAdapter;
    RelativeLayout tableTitleLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_table);
        tableTitleLayout = (RelativeLayout) findViewById(R.id.head);
        tableTitleLayout.setFocusable(true);
        tableTitleLayout.setClickable(true);
        tableTitleLayout.setBackgroundColor(Color.parseColor("#b2d235"));
        tableTitleLayout.setOnTouchListener(new ListViewAndHeadViewTouchListener());

        mListView1 = (ListView) findViewById(R.id.listView1);
        mListView1.setOnTouchListener(new ListViewAndHeadViewTouchListener());
        mListView1.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    int lastVisiableItem = mListView1.getLastVisiblePosition();
                    if (lastVisiableItem == mListView1.getAdapter().getCount() - 1) {
                        Toast.makeText(getApplicationContext(), "最后一个", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

        myAdapter = new MyAdapter(this, R.layout.activity_fix_table_item);
        mListView1.setAdapter(myAdapter);


    }

    class ListViewAndHeadViewTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View arg0, MotionEvent arg1) {
            //当在列头 和 listView控件上touch时，将这个touch的事件分发给 ScrollView
            HorizontalScrollView headScrollView = (HorizontalScrollView) tableTitleLayout
                    .findViewById(R.id.horizontalScrollView1);
            headScrollView.onTouchEvent(arg1);
            return false;
        }
    }

    public class MyAdapter extends BaseAdapter {
        private List<ItemBean> list = new ArrayList<ItemBean>();
        public List<ViewHolder> mHolderList = new ArrayList<ViewHolder>();

        class ItemBean {
            String name1;
            String name2;
            String name3;
            String name4;
            String name5;

            public ItemBean(String n1, String n2, String n3, String n4, String n5) {
                this.name1 = n1;
                this.name2 = n2;
                this.name3 = n3;
                this.name4 = n4;
                this.name5 = n5;
            }
        }

        int layoutId;
        LayoutInflater mInflater;

        public MyAdapter(Context context, int layoutId) {
            super();
            this.layoutId = layoutId;
            mInflater = LayoutInflater.from(context);

            getData();
        }

        private void getData() {
            list.clear();
            for (int i = 0; i < 3; i++) {
                list.add(new ItemBean("字段一", "字段二 二师弟的大耳朵", "12", "class", "这是一个测试字段 试一试有没有item自适应功能"));
                list.add(new ItemBean("这是一个测试字段 试一试有没有item自适应功能", "7", "字段二 二师弟的大耳朵", "字段四", "字段五 阮小五"));
                list.add(new ItemBean("这是一个测试字段 试一试有没有item自适应功能", "7", "字段二 二师弟的大耳朵", "字段四", "字段五 阮小五"));
                list.add(new ItemBean("字段小⑦", "字段六 诸葛小刘", "字段二 二师弟的大耳朵", "字段四", "字段五 阮小五"));
            }
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parentView) {
            ViewHolder holder = null;
            if (convertView == null) {
                synchronized (FixTableActivity.this) {
                    convertView = mInflater.inflate(layoutId, null);
                    holder = new ViewHolder();

                    MyHScrollView scrollView1 = (MyHScrollView) convertView
                            .findViewById(R.id.horizontalScrollView1);

                    holder.scrollView = scrollView1;
                    holder.txt1 = (TextView) convertView
                            .findViewById(R.id.textView1);
                    holder.txt2 = (TextView) convertView
                            .findViewById(R.id.textView2);
                    holder.txt3 = (TextView) convertView
                            .findViewById(R.id.textView3);
                    holder.txt4 = (TextView) convertView
                            .findViewById(R.id.textView4);


                    MyHScrollView headerScrollView = (MyHScrollView) tableTitleLayout.findViewById(R.id.horizontalScrollView1);
                    headerScrollView.AddOnScrollChangedListener( new OnScrollChangedListenerImp(scrollView1));

                    convertView.setTag(holder);
                    mHolderList.add(holder);
                }
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            ItemBean data = list.get(position);
            holder.txt1.setText(position + ":" + data.name1);
            holder.txt2.setText(position + "" + data.name2);
            holder.txt3.setText(position + "" + data.name3);
            holder.txt4.setText(position + "" + data.name4);
            holder.txt5.setText(position + "" + data.name5);

            return convertView;
        }

        class OnScrollChangedListenerImp implements MyHScrollView.OnScrollChangedListener {
            MyHScrollView mScrollViewArg;

            public OnScrollChangedListenerImp(MyHScrollView scrollViewar) {
                mScrollViewArg = scrollViewar;
            }

            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                mScrollViewArg.smoothScrollTo(l, t);
            }
        }

        ;

        class ViewHolder {
            TextView txt1;
            TextView txt2;
            TextView txt3;
            TextView txt4;
            TextView txt5;
            HorizontalScrollView scrollView;
        }
    }
}
