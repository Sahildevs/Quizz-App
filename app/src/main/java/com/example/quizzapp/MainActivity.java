package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuestion, tvQuestionNo;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button btnNext;

    int totalQuestions;
    int questionCounter = 0;


    private QuestionModel currentQuestion;
    private List<QuestionModel> questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionsList = new ArrayList<>();
        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNo = findViewById(R.id.tvQuestNo);

        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rbOpt1);
        rb2 = findViewById(R.id.rbOpt2);
        rb3 = findViewById(R.id.rbOpt3);
        rb4 = findViewById(R.id.rbOpt4);
        btnNext = findViewById(R.id.btnNext);


        addQuestions();//All the questions and options for each question are defined in this method

        showNextQuestion();//this method will display the question and its option

        rbListner();// this method listens the radio button action and displays a Toast message

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {

                    radioGroup.clearCheck();
                    showNextQuestion();

                } else {
                    Toast.makeText(MainActivity.this, "Select an option", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void showNextQuestion() {

        totalQuestions = questionsList.size();

        if (questionCounter < totalQuestions) {
            currentQuestion = questionsList.get(questionCounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOpt1());
            rb2.setText(currentQuestion.getOpt2());
            rb3.setText(currentQuestion.getOpt3());
            rb4.setText(currentQuestion.getOpt4());

            questionCounter++;
            tvQuestionNo.setText(questionCounter + "/" + totalQuestions);

        } else  {
            finish();
        }
    }

    private void addQuestions() {

        questionsList.add(new QuestionModel("Android is owned by ?", " A. Microsoft", " B. Apple", " C. Google", " D. Oracle", 3));
        questionsList.add(new QuestionModel("Who is the CEO of GOOGLE ?", " A. Elon Musk", " B. Mark", " C. Jeff Bezzos", " D. Sundar Pichai", 4));
        questionsList.add(new QuestionModel("Who is the CEO of SpaceX ?", " A. Elon Musk", " B. Mark", " C. Jeff Bezzos", " D. Sundar Pichai", 1));
        questionsList.add(new QuestionModel("Who is the CEO of Amazon ?", " A. Elon Musk", " B. Mark", " C. Jeff Bezzos", " D. Sundar Pichai", 3));

    }


    public void rbListner() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedButtonId) {

                //checkedButtonId contains the radio button id which is selected by user
                switch (checkedButtonId) {
                    case R.id.rbOpt1:
                        Toast.makeText(MainActivity.this, "Option A selected", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.rbOpt2:
                        Toast.makeText(MainActivity.this, "Option B selected", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.rbOpt3:
                        Toast.makeText(MainActivity.this, "Option C selected", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.rbOpt4:
                        Toast.makeText(MainActivity.this, "Option D selected", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}

