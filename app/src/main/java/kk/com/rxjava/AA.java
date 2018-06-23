package kk.com.rxjava;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.lang.reflect.Field;

public class AA extends LinearLayoutManager {

    private final RecyclerView.Adapter adapter;

    public AA(Context context, RecyclerView.Adapter adapter) {
        super(context);
        this.adapter = adapter;
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            if (adapter.getItemCount() != state.getItemCount()) {

                String a="abc";
                Field valueFieldString=this.getClass().getSuperclass().getDeclaredField("mLayoutState");
                valueFieldString.setAccessible(true);

                valueFieldString.get(this);


                return;
            }
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
//        if (state instanceof SavedState) {
//            mPendingSavedState = (SavedState) state;
//            requestLayout();
//            if (DEBUG) {
//                Log.d(TAG, "loaded saved state");
//            }
//        } else if (DEBUG) {
//            Log.d(TAG, "invalid saved state class");
//        }
    }

}
