package com.example.imac.swipecard.viewUtil;

import android.view.View;


public interface SwipeCallback {
    void cardSwipedLeft(View card);
    void cardSwipedRight(View card);
    void cardSwipedTop(View card);
    void cardSwipedBottom(View card);
    void cardOffScreen(View card);
    void cardActionDown();
    void cardActionUp();
    void LeftToRightSwipe();
    void RightToLeftSwipe();
    void OnCardClick(View card);

    /**
     * Check whether we can start dragging current view.
     * @return true if we can start dragging view, false otherwise
     */
    boolean isDragEnabled();
}
