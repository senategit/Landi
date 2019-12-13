package com.senate_system.testlandi;

import android.graphics.Color;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TextBody {

    private String text;
    private Integer color = Color.BLACK;
    private AlignEnum align = AlignEnum.LEFT;
    private Integer size = 14;
    private TextStyle textStyle = TextStyle.NORMAL;

    enum AlignEnum{
        LEFT,
        CENTER,
        RIGHT
    }

    enum TextStyle{
        NORMAL,
        BOLD,
        UNDERLINE
    }

    public TextBody(String text){
        this.text = text;
    }

    public TextBody(String text, @Nullable Integer color, @Nullable Integer size, @Nullable AlignEnum align, @Nullable TextStyle textStyle){
        this.text = text;
        this.color = color;
        this.align = align;
        this.size = size;
        this.textStyle = textStyle;
    }

    public String getText(){
        return this.text;
    }

    public Integer getColor(){
        return this.color;
    }

    public Integer getSize(){
        return this.size;
    }

    public AlignEnum getAlign(){
        return this.align;
    }

    public TextStyle getTextStyle(){
        return this.textStyle;
    }




}
