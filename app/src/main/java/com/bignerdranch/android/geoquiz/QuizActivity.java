package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_china,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_oceans,true)

    };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz); //resource ID for layout

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        updateQuestion();
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion(); //to next question by clicking
            }
        });

        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){ //anonymous inner class
            @Override
            public void onClick(View v){
                checkAnswer(true);
            }
        });

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(false);
            }
        });

        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });

        mPrevButton = (ImageButton)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex-1+mQuestionBank.length)%mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismAnswerTrue();
        int messageResId = 0;
        if (answerIsTrue == userPressedTrue){
            messageResId = R.string.correct_toast;
        }else{
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(QuizActivity.this,
                messageResId,
                Toast.LENGTH_SHORT).show();
    }
}
