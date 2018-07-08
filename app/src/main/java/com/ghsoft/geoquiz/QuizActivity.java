package com.ghsoft.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    // 1,新增TAG常量
    private static final String TAG = QuizActivity.class.getSimpleName();
    // 挑战练习
    // 2.10 从按钮到图标按钮
    // Button -> ImageButton
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true),
    };
    private int mCurrentIndex = 0;
    private ImageButton mPreButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 2,添加日志输出代码
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        mQuestionTextView = findViewById(R.id.tv_question);
        mTrueButton = findViewById(R.id.btn_true);
        mFalseButton = findViewById(R.id.btn_false);
        mNextButton = findViewById(R.id.btn_next);
        mPreButton = findViewById(R.id.btn_pre);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 切换显示不同的问题内容
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        // 2.8 为TextView添加监听器
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 切换显示不同的问题内容
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        // 2.9 添加后退按钮
        mPreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 切换显示不同的问题内容
                // 点击后为-
                /**
                 * 点击之后,先减(别忘了赋值)
                 * 减的话,点击一次只减一次就得了
                 */
                if (--mCurrentIndex < 0) {
                    mCurrentIndex = mQuestionBank.length - Math.abs(mCurrentIndex);
                } else {
                    // 点击后为+
                    mCurrentIndex %= mQuestionBank.length;
                }
                updateQuestion();
            }
        });
        updateQuestion();
    }

    /**
     * 更新显示问题
     */
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    /**
     * 检查用户给出的答案
     * @param userPressedTrue 用户的选择
     */
    private void checkAnswer(boolean userPressedTrue){
        // 获取当前问题的答案
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        // 比对用户给出的答案和问题的答案
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        // 弹出提示用户选择正误的提示
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
    // 3,覆盖更多生命周期方法

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
