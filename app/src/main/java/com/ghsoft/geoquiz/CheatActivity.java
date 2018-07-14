package com.ghsoft.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    /**
     * 使用包名修饰extra数据信息,可以避免来自不同应用的extra间发生命名冲突
     */
    private static final String EXTRA_ANSWER_IS_TRUE = "com.ghsoft.geoquiz.answer_is_true";
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerTextView = findViewById(R.id.answerTextView);
        mShowAnswer = findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取extra中问题的答案
                boolean answerTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
                mAnswerTextView.setText(answerTrue?R.string.true_button:R.string.false_button);
            }
        });
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
        packageContext.startActivity(intent);
        return intent;
    }
}
