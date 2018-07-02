package com.ghsoft.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    // 挑战练习
    // 2.9 添加后退按钮
        // 1.在布局文件中添加后退按钮
        // 2.添加后退按钮所需的向左箭头
        // 3.点击显示上一道测试题目(与点击next按钮时显示下一道测试题目的效果是相同的)
            // 3.1 -1
    // 2.10 从按钮到图标按钮
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true),
    };
    private int mCurrentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mQuestionTextView = findViewById(R.id.tv_question);
        mTrueButton = findViewById(R.id.btn_true);
        mFalseButton = findViewById(R.id.btn_false);
        mNextButton = findViewById(R.id.btn_next);
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
//        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
                // 切换显示不同的问题内容
//                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
//                updateQuestion();
//            }
//        });
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
}
