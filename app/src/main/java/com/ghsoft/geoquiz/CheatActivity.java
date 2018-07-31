package com.ghsoft.geoquiz;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    /**
     * 使用包名修饰extra数据信息,可以避免来自不同应用的extra间发生命名冲突
     */
    private static final String EXTRA_ANSWER_IS_TRUE = "com.ghsoft.geoquiz.answer_is_true";
    // 1.为extra键增加常量
    private static final String EXTRA_ANSWER_SHOWN = "com.ghsoft.geoquiz.answer_shown";

    private boolean isAnswerShown;

    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private TextView mTvApiLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerTextView = findViewById(R.id.answerTextView);
        mShowAnswer = findViewById(R.id.showAnswerButton);
        mTvApiLevel = findViewById(R.id.tv_api_level);
        mTvApiLevel.setText(mTvApiLevel.getText().toString() + Build.VERSION.SDK_INT);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取extra中问题的答案
                boolean answerTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
                mAnswerTextView.setText(answerTrue?R.string.true_button:R.string.false_button);
                // 3.在showAnswer按钮的监听器代码中调用该方法
                setAnswerShownResult(true);
                isAnswerShown = true;
                // 检查Andorid设备版本
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int cx = mShowAnswer.getWidth() / 2;
                    int cy = mShowAnswer.getHeight() / 2;
                    int radius = mShowAnswer.getWidth();
                    Animator anim = ViewAnimationUtils
                            .createCircularReveal(mShowAnswer, cx, cy, radius, 0);
                    anim.addListener(new Animator.AnimatorListener() {

                        @Override
                        public void onAnimationStart(Animator animation) {}

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mShowAnswer.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {}

                        @Override
                        public void onAnimationRepeat(Animator animation) {}
                    });
                    anim.start();
                }else{
                    mShowAnswer.setVisibility(View.INVISIBLE);
                }
            }
        });
        if (savedInstanceState == null) {
            return;
        }else {
            if (!savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN)) {
                return;
            }else{
                setAnswerShownResult(true);
                isAnswerShown = false;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(EXTRA_ANSWER_SHOWN,isAnswerShown);
    }

    /**
     * 创建intent
     * @param packageContext 启动方上下文对象
     * @param extra 传递到当前activity的值
     * @return 新的intent
     */
    public static Intent newIntent(Context packageContext,boolean extra){
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE,extra);
        return intent;
    }
    // 4.结果intent的内容也是CheatActivity的实现细节,添加方法协助解析出QA能用的信息
    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }
    // 2.创建私有方法 - 创建intent、附加extra并设置结果值
    /**
     * 设置答案已经显示
     * @param isAnswerShown
     */
    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);
    }
}
