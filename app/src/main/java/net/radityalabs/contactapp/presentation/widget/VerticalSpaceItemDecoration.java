package net.radityalabs.contactapp.presentation.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.presentation.util.WidgetUtil;

/**
 * Created by radityagumay on 4/15/17.
 */

public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private final int verticalSpaceHeight;

    private Context mContext;
    private Drawable divider;
    private View mView;

    public VerticalSpaceItemDecoration(Context context, int verticalSpaceHeight, int resId) {
        final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
        divider = styledAttributes.getDrawable(0);
        styledAttributes.recycle();

        this.mContext = context;
        this.verticalSpaceHeight = verticalSpaceHeight;
        this.divider = ContextCompat.getDrawable(context, resId);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        mView = view;
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = verticalSpaceHeight;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mView != null) {
            int left = parent.getPaddingLeft() + (int) WidgetUtil.dpToPx(mContext, 20 + mView.findViewById(R.id.tv_body_one).getPaddingLeft());
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin + (int) WidgetUtil.dpToPx(mContext, 15);
                int bottom = top + divider.getIntrinsicHeight();

                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }
    }
}