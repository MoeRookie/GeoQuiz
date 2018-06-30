package com.ghsoft.geoquiz;

/**
 * Created by mac on 2018/6/30.
 */

public class Question {
    private int mTextResId; // 问题文本(的资源ID)
    private boolean mAnswerTrue; // 问题答案

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
