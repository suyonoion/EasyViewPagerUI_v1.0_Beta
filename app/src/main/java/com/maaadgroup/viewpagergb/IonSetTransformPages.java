package com.maaadgroup.viewpagergb;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Build;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public enum IonSetTransformPages implements ViewPager.PageTransformer{

    NonAktif() {
        @Override
        public void transformPage(View page, float position) {

        }
    },
    AccordionTransformer() {
        @Override
        public void transformPage(View view, float position) {
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
                view.setPivotX(position < 0 ? 0 : view.getWidth());
                view.setScaleX(position < 0 ? 1f + position : 1f - position);
            }
            else {
                ViewHelper.setPivotX(view, position < 0 ? 0 : view.getWidth());
                ViewHelper.setScaleX(view, position < 0 ? 1f + position : 1f - position);
            }
        }
    },
    BackgroundToForegroundTransformer() {

        private float min(float val, float min) {
            return val < min ? min : val;
        }

        @Override
        public void transformPage(View view, float position) {
            final float height = view.getHeight();
            final float width = view.getWidth();
            final float scale = min(position < 0 ? 1f : Math.abs(1f - position), 0.5f);


            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
                view.setScaleX(scale);
                view.setScaleY(scale);
                view.setPivotX(width * 0.5f);
                view.setPivotY(height * 0.5f);
                view.setTranslationX(position < 0 ? width * position : -width * position * 0.25f);
            }
            else {
                ViewHelper.setScaleX(view, scale);
                ViewHelper.setScaleY(view, scale);
                ViewHelper.setPivotX(view, width * 0.5f);
                ViewHelper.setPivotY(view, height * 0.5f);
                ViewHelper.setTranslationX(view, position < 0 ? width * position : -width * position * 0.25f);
            }
        }
    },
    DepthPageTransformer() {
        private static final float MIN_SCALE = 0.75f;

        @Override
        public void transformPage(View view, float position) {
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
                if (position <= 0f) {
                    view.setTranslationX(0f);
                    view.setScaleX(1f);
                    view.setScaleY(1f);
                } else if (position <= 1f) {
                    final float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                    view.setAlpha(1 - position);
                    view.setPivotY(0.5f * view.getHeight());
                    view.setTranslationX(view.getWidth() * -position);
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);
                }
            }
            else {
                if (position <= 0f) {
                    ViewHelper.setTranslationX(view,0f);
                    ViewHelper.setScaleX(view,1f);
                    ViewHelper.setScaleY(view,1f);
                } else if (position <= 1f) {
                    final float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                    ViewHelper.setAlpha(view,1 - position);
                    ViewHelper.setPivotY(view,0.5f * view.getHeight());
                    ViewHelper.setTranslationX(view,view.getWidth() * -position);
                    ViewHelper.setScaleX(view,scaleFactor);
                    ViewHelper.setScaleY(view,scaleFactor);
                }
            }
        }
    },
    ForegroundToBackgroundTransformer() {
        private float min(float val, float min) {
            return val < min ? min : val;
        }

        @Override
        public void transformPage(View view, float position) {
            final float height = view.getHeight();
            final float width = view.getWidth();
            final float scale = min(position > 0 ? 1f : Math.abs(1f + position), 0.5f);

            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
                view.setScaleX(scale);
                view.setScaleY(scale);
                view.setPivotX(width * 0.5f);
                view.setPivotY(height * 0.5f);
                view.setTranslationX(position > 0 ? width * position : -width * position * 0.25f);
            }
            else {
                ViewHelper.setScaleX(view,scale);
                ViewHelper.setScaleY(view,scale);
                ViewHelper.setPivotX(view,width * 0.5f);
                ViewHelper.setPivotY(view,height * 0.5f);
                ViewHelper.setTranslationX(view,position > 0 ? width * position : -width * position * 0.25f);
            }
        }
    },
    CubeInTransformer() {
        @Override
        public void transformPage(View view, float position) {
            // Rotate the fragment on the left or right edge
            final float rotation = (position < 0 ? 90f : -90f) * Math.abs(position);
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
                view.setAlpha(rotation > 90f || rotation < -90f ? 0f : 1f);
                view.setPivotX(position > 0 ? 0 : view.getWidth());
                view.setPivotY(0);
                view.setRotationY(-90f * position);
            }
            else {
                ViewHelper.setAlpha(view,rotation > 90f || rotation < -90f ? 0f : 1f);
                ViewHelper.setPivotX(view,position > 0 ? 0 : view.getWidth());
                ViewHelper.setPivotY(view,0);
                ViewHelper.setRotationY(view,-90f * position);
            }
        }
    },
    CubeOutTransformer() {
        @Override
        public void transformPage(View view, float position) {
            final float rotation = (position < 0 ? 90f : -90f) * Math.abs(position);
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
                view.setAlpha(rotation > 90f || rotation < -90f ? 0f : 1f);
                view.setPivotX(position < 0f ? view.getWidth() : 0f);
                view.setPivotY(view.getHeight() * 0.5f);
                view.setRotationY(90f * position);
            }
            else {
                ViewHelper.setAlpha(view, rotation > 90f || rotation < -90f ? 0f : 1f);
                ViewHelper.setPivotX(view,position < 0f ? view.getWidth() : 0f);
                ViewHelper.setPivotY(view,view.getHeight() * 0.5f);
                ViewHelper.setRotationY(view, 90f * position);
            }
        }
    },
    FlipHorizontalTransformer() {
        @Override
        public void transformPage(View view, float position) {
            final float rotation = 180f * position;

            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
                view.setAlpha(rotation > 90f || rotation < -90f ? 0 : 1);
                view.setPivotX(view.getWidth() * 0.5f);
                view.setPivotY(view.getHeight() * 0.5f);
                view.setRotationY(rotation);
            }
            else {
                ViewHelper.setAlpha(view, rotation > 90f || rotation < -90f ? 0 : 1);
                ViewHelper.setPivotX(view,view.getWidth() * 0.5f);
                ViewHelper.setPivotY(view,view.getHeight() * 0.5f);
                ViewHelper.setRotationY(view,rotation);
            }
        }
    },
    FlipVerticalTransformer() {

        @Override
        public void transformPage(View view, float position) {
            final float rotation = -180f * position;

            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
                view.setAlpha(rotation > 90f || rotation < -90f ? 0f : 1f);
                view.setPivotX(view.getWidth() * 0.5f);
                view.setPivotY(view.getHeight() * 0.5f);
                view.setRotationX(rotation);
            }
            else {
                ViewHelper.setAlpha(view,rotation > 90f || rotation < -90f ? 0f : 1f);
                ViewHelper.setPivotX(view,view.getWidth() * 0.5f);
                ViewHelper.setPivotY(view,view.getHeight() * 0.5f);
                ViewHelper.setRotationX(view,rotation);
            }
        }
    },
    RotateDownTransformer() {
        private static final float ROT_MOD = -15f;

        @Override
        public void transformPage(View view, float position) {
            final float width = view.getWidth();
            final float height = view.getHeight();
            final float rotation = ROT_MOD * position * -1.25f;


            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
                view.setPivotX(width * 0.5f);
                view.setPivotY(height);
                view.setRotation(rotation);
            }
            else {
                ViewHelper.setPivotX(view,width * 0.5f);
                ViewHelper.setPivotY(view,height);
                ViewHelper.setRotation(view,rotation);
            }
        }
    },
    RotateUpTransformer() {
        private static final float ROT_MOD = -15f;

        @Override
        public void transformPage(View view, float position) {
            final float width = view.getWidth();
            final float rotation = ROT_MOD * position;


            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
                view.setPivotX(width * 0.5f);
                view.setPivotY(0f);
                view.setTranslationX(0f);
                view.setRotation(rotation);
            }
            else {
                ViewHelper.setPivotX(view,width * 0.5f);
                ViewHelper.setPivotY(view,0f);
                ViewHelper.setTranslationX(view,0f);
                ViewHelper.setRotation(view,rotation);
            }
        }
    },
    StackTransformer() {
        @Override
        public void transformPage(View view, float position) {
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
                view.setTranslationX(position < 0 ? 0f : -view.getWidth() * position);
            }
            else {
                ViewHelper.setTranslationX(view,position < 0 ? 0f : -view.getWidth() * position);
            }
        }
    },
    TabletTransformer() {
        private  Matrix OFFSET_MATRIX = new Matrix();
        private  Camera OFFSET_CAMERA = new Camera();
        private  float[] OFFSET_TEMP_FLOAT = new float[2];

        protected float getOffsetXForRotation(float degrees, int width, int height) {
            OFFSET_MATRIX.reset();
            OFFSET_CAMERA.save();
            OFFSET_CAMERA.rotateY(Math.abs(degrees));
            OFFSET_CAMERA.getMatrix(OFFSET_MATRIX);
            OFFSET_CAMERA.restore();

            OFFSET_MATRIX.preTranslate(-width * 0.5f, -height * 0.5f);
            OFFSET_MATRIX.postTranslate(width * 0.5f, height * 0.5f);
            OFFSET_TEMP_FLOAT[0] = width;
            OFFSET_TEMP_FLOAT[1] = height;
            OFFSET_MATRIX.mapPoints(OFFSET_TEMP_FLOAT);
            return (width - OFFSET_TEMP_FLOAT[0]) * (degrees > 0.0f ? 1.0f : -1.0f);
        }

        @Override
        public void transformPage(View view, float position) {
            final float rotation = (position < 0 ? 30f : -30f) * Math.abs(position);


            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
                view.setTranslationX(getOffsetXForRotation(rotation, view.getWidth(), view.getHeight()));
                view.setPivotX(view.getWidth() * 0.5f);
                view.setPivotY(0);
                view.setRotationY(rotation);
            }
            else {
                ViewHelper.setTranslationX(view, getOffsetXForRotation(rotation, view.getWidth(), view.getHeight()));
                ViewHelper.setPivotX(view,view.getWidth() * 0.5f);
                ViewHelper.setPivotY(view,0);
                ViewHelper.setRotationY(view,rotation);
            }
        }
    },
    ZoomInTransformer() {
        @Override
        public void transformPage(View view, float position) {
            final float scale = position < 0 ? position + 1f : Math.abs(1f - position);

            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
                view.setScaleX(scale);
                view.setScaleY(scale);
                view.setPivotX(view.getWidth() * 0.5f);
                view.setPivotY(view.getHeight() * 0.5f);
                view.setAlpha(position < -1f || position > 1f ? 0f : 1f - (scale - 1f));
            }
            else {
                ViewHelper.setScaleX(view,scale);
                ViewHelper.setScaleY(view,scale);
                ViewHelper.setPivotX(view,view.getWidth() * 0.5f);
                ViewHelper.setPivotY(view,view.getHeight() * 0.5f);
                ViewHelper.setAlpha(view,position < -1f || position > 1f ? 0f : 1f - (scale - 1f));
            }
        }
    },
    ZoomOutTranformer() {
        @Override
        public void transformPage(View view, float position) {
            final float scale = 1f + Math.abs(position);

            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
                view.setScaleX(scale);
                view.setScaleY(scale);
                view.setPivotX(view.getWidth() * 0.5f);
                view.setPivotY(view.getHeight() * 0.5f);
                view.setAlpha(position < -1f || position > 1f ? 0f : 1f - (scale - 1f));
                if(position == -1){
                    view.setTranslationX(view.getWidth() * -1);
                }
            }
            else {
                ViewHelper.setScaleX(view, scale);
                ViewHelper.setScaleY(view,scale);
                ViewHelper.setPivotX(view,view.getWidth() * 0.5f);
                ViewHelper.setPivotY(view,view.getHeight() * 0.5f);
                ViewHelper.setAlpha(view,position < -1f || position > 1f ? 0f : 1f - (scale - 1f));
                if(position == -1){
                    ViewHelper.setTranslationX(view,view.getWidth() * -1);
                }
            }
        }
    },
    ZoomOutSlideTransformer() {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View view, float position) {
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
                if (position >= -1 || position <= 1) {
                    // Modify the default slide transition to shrink the page as well
                    final float height = view.getHeight();
                    final float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    final float vertMargin = height * (1 - scaleFactor) / 2;
                    final float horzMargin = view.getWidth() * (1 - scaleFactor) / 2;

                    // Center vertically
                    view.setPivotY(0.5f * height);

                    if (position < 0) {
                        view.setTranslationX(horzMargin - vertMargin / 2);
                    } else {
                        view.setTranslationX(-horzMargin + vertMargin / 2);
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                    // Fade the page relative to its size.
                    view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
                }
            }
            else {
                if (position >= -1 || position <= 1) {
                    // Modify the default slide transition to shrink the page as well
                    final float height = view.getHeight();
                    final float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    final float vertMargin = height * (1 - scaleFactor) / 2;
                    final float horzMargin = view.getWidth() * (1 - scaleFactor) / 2;

                    // Center vertically
                    ViewHelper.setPivotY(view,0.5f * height);

                    if (position < 0) {
                        ViewHelper.setTranslationX(view,horzMargin - vertMargin / 2);
                    } else {
                        ViewHelper.setTranslationX(view,-horzMargin + vertMargin / 2);
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    ViewHelper.setScaleX(view,scaleFactor);
                    ViewHelper.setScaleY(view,scaleFactor);

                    // Fade the page relative to its size.
                    ViewHelper.setAlpha(view,MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
                }
            }
        }
    },
    DrawFromBackTransformer() {
        private static final float MIN_SCALE = 0.75f;

        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB){
                if (position < -1 || position > 1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0);
                    return;
                }

                if (position <= 0) { // [-1,0]
                    // Use the default slide transition when moving to the left page
                    // Fade the page out.
                    view.setAlpha(1 + position);
                    // Counteract the default slide transition
                    view.setTranslationX(pageWidth * -position);

                    // Scale the page down (between MIN_SCALE and 1)
                    float scaleFactor = MIN_SCALE
                            + (1 - MIN_SCALE) * (1 - Math.abs(position));
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);
                    return;

                }

                if (position > 0.5 && position <= 1) { // (0,1]
                    // Fade the page out.
                    view.setAlpha(0);

                    // Counteract the default slide transition
                    view.setTranslationX(pageWidth * -position);
                    return;
                }
                if (position > 0.3 && position <= 0.5) { // (0,1]
                    // Fade the page out.
                    view.setAlpha(1);

                    // Counteract the default slide transition
                    view.setTranslationX(pageWidth * position);

                    float scaleFactor = MIN_SCALE;
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);
                    return;
                }
                if (position <= 0.3) { // (0,1]
                    // Fade the page out.
                    view.setAlpha(1);
                    // Counteract the default slide transition
                    view.setTranslationX(pageWidth * position);

                    // Scale the page down (between MIN_SCALE and 1)
                    float v = (float) (0.3 - position);
                    v = v >= 0.25f ? 0.25f : v;
                    float scaleFactor = MIN_SCALE + v;
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);
                }
            }
            else {
                if (position < -1 || position > 1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    ViewHelper.setAlpha(view,0);
                    return;
                }

                if (position <= 0) { // [-1,0]
                    // Use the default slide transition when moving to the left page
                    // Fade the page out.
                    ViewHelper.setAlpha(view,1 + position);
                    // Counteract the default slide transition
                    ViewHelper.setTranslationX(view,pageWidth * -position);

                    // Scale the page down (between MIN_SCALE and 1)
                    float scaleFactor = MIN_SCALE
                            + (1 - MIN_SCALE) * (1 - Math.abs(position));
                    ViewHelper.setScaleX(view,scaleFactor);
                    ViewHelper.setScaleY(view,scaleFactor);
                    return;

                }

                if (position > 0.5 && position <= 1) { // (0,1]
                    // Fade the page out.
                    ViewHelper.setAlpha(view,0);

                    // Counteract the default slide transition
                    ViewHelper.setTranslationX(view,pageWidth * -position);
                    return;
                }
                if (position > 0.3 && position <= 0.5) { // (0,1]
                    // Fade the page out.
                    ViewHelper.setAlpha(view,1);

                    // Counteract the default slide transition
                    ViewHelper.setTranslationX(view,pageWidth * position);

                    float scaleFactor = MIN_SCALE;
                    ViewHelper.setScaleX(view,scaleFactor);
                    ViewHelper.setScaleY(view,scaleFactor);
                    return;
                }
                if (position <= 0.3) { // (0,1]
                    // Fade the page out.
                    ViewHelper.setAlpha(view,1);
                    // Counteract the default slide transition
                    ViewHelper.setTranslationX(view,pageWidth * position);

                    // Scale the page down (between MIN_SCALE and 1)
                    float v = (float) (0.3 - position);
                    v = v >= 0.25f ? 0.25f : v;
                    float scaleFactor = MIN_SCALE + v;
                    ViewHelper.setScaleX(view,scaleFactor);
                    ViewHelper.setScaleY(view,scaleFactor);
                }
            }
        }
    };
}