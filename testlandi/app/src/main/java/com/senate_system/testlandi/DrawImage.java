package com.senate_system.testlandi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DrawImage {

    private final Context mContext;
    private int mPaperWidth;
    private int mPaperHeight;
    private Bitmap imageBitmap;
    private OnDrawSuccessListener mOnDrawImageSuccess;
    private Paint p;
    private Integer lineSpacing = 14;
    private Padding padding;

    private ArrayList<TextBody> arrayList = new ArrayList<>();

    public DrawImage setOnDrawImageSuccess(OnDrawSuccessListener onDrawImageSuccess) {
        mOnDrawImageSuccess = onDrawImageSuccess;
        return this;
    }

    interface OnDrawSuccessListener {
        void onImage(Bitmap bitmap);
    }

    public DrawImage(Context context) {
        mContext = context;
    }

    public DrawImage createPaperBitmap() {
        imageBitmap = Bitmap.createBitmap(mPaperWidth, mPaperHeight, Bitmap.Config.ARGB_8888);
        return this;
    }

    public DrawImage setPaperWidth(int PaperWidth) {
        mPaperWidth = PaperWidth;
        return this;
    }

    public DrawImage setPaperHeight(int PaperHeight) {
        mPaperHeight = PaperHeight;
        return this;
    }

    public Bitmap getBitmap() {
        return imageBitmap;
    }

    public DrawImage createNewPaint(@Nullable Integer color, @Nullable Integer textSize) {
        p = new Paint();

        if (color == null) {
            p.setColor(Color.BLACK);
        } else {
            p.setColor(color);
        }

        if (textSize == null) {
            p.setTextSize(24);
        } else {
            p.setTextSize(textSize);
        }

        return this;
    }

    public void drawText(String input) {
        Canvas canvas = new Canvas(imageBitmap);
        canvas.drawText(input, mPaperWidth / 2, mPaperHeight / 2, p);
        if (mOnDrawImageSuccess != null) {
            mOnDrawImageSuccess.onImage(imageBitmap);
        }
    }

    public DrawImage addTextBody(TextBody textBody) {
        arrayList.add(textBody);
        return this;
    }

    public void clearTextBodyList() {
        arrayList.clear();
    }

    public ArrayList<TextBody> getTextBodyList() {
        return arrayList;
    }

    public Bitmap getPaperTextBodyList() {
        imageBitmap = Bitmap.createBitmap(mPaperWidth, calculatePaperHight(padding.topPadding,padding.bottomPadding), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(imageBitmap);
        for (int i = 0; i < arrayList.size(); i++) {
            PositionXY positionXY = new PositionXY(i,lineSpacing,arrayList.get(i).getSize(),arrayList.get(i).getAlign());

            Paint _p = new Paint();
            _p.setColor(arrayList.get(i).getColor());
            _p.setTextSize(arrayList.get(i).getSize());
            _p.setTextAlign(getAlign(arrayList.get(i).getAlign()));

            canvas.drawText(
                    arrayList.get(i).getText(),
                    positionXY.getPositionX(),
                    positionXY.getPositionY(),
                    _p);
        }
        return imageBitmap;
    }

    public Paint.Align getAlign(TextBody.AlignEnum alignEnum){
        if(alignEnum == TextBody.AlignEnum.LEFT){
            return Paint.Align.LEFT;
        }else if(alignEnum == TextBody.AlignEnum.CENTER){
            return Paint.Align.CENTER;
        }else{
            return Paint.Align.RIGHT;
        }
    }

    public void setLineSpacing(Integer lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public void setPaddingPaper(int topPadding,int leftPadding,int rightPadding,int bottomPadding){
        padding = new Padding(topPadding,leftPadding,rightPadding,bottomPadding);
    }

    private Integer calculatePaperHight(int topPadding, int bottomPadding) {
        int size = 0;
        size = size + topPadding + bottomPadding;
        for (int i = 0; i < arrayList.size(); i++) {
            size = size + arrayList.get(i).getSize() + lineSpacing;
        }
        return size;
    }

    private class PositionXY {

        private int positionX;
        private int positionY;

        public PositionXY(int lineNumber, int lineSpacing, int textSize, TextBody.AlignEnum alignEnum) {
            positionX=;
            positionY=;
        }

        public int getPositionX() {
            return positionX;
        }

        public int getPositionY() {
            return positionY;
        }
    }//

    private class Padding{
        private int topPadding;
        private int leftPadding;
        private int rightPadding;
        private int bottomPadding;

        Padding(int topPadding,int leftPadding,int rightPadding,int bottomPadding){
            this.topPadding = topPadding;
            this.leftPadding = leftPadding;
            this.rightPadding = rightPadding;
            this.bottomPadding = bottomPadding;
        }
    }
}
