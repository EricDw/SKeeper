package com.publicmethod.owner.skeeper.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.publicmethod.owner.skeeper.R;

public class CalculatorActivity extends AppCompatActivity {

    Button mButton0, mButton1, mButton2, mButton3, mButton4, mButton5, mButton6,
            mButton7, mButton8, mButton9, mButtonAdd, mButtonSubtract, mButtonDivision,
            mButtonMultiply, mButtonDecimal, mButtonClear, mButtonEquals, mButtonDone;

    TextView mInputField;

    float mValueOne, mValueTwo;

    boolean mAddition, mSubtract, mMultiplication, mDivision, mJustGaveAnswer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        this.setTitle("Calculate All the Things");

        initializeScreen();
        mInputField.setText("0");

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber("1");
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber("2");
            }
        });

        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber("3");
            }
        });

        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber("4");
            }
        });

        mButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber("5");
            }
        });

        mButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber("6");
            }
        });

        mButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber("7");
            }
        });

        mButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber("8");
            }
        });

        mButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber("9");
            }
        });

        mButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber("0");
            }
        });

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(mInputField.getText().toString());
                mAddition = true;
                mInputField.setText("0");
            }
        });

        mButtonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(mInputField.getText() + "");
                mSubtract = true;
                mInputField.setText("0");
            }
        });

        mButtonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(mInputField.getText() + "");
                mMultiplication = true;
                mInputField.setText("0");
            }
        });

        mButtonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(mInputField.getText() + "");
                mDivision = true;
                mInputField.setText("0");
            }
        });

        mButtonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mValueTwo = Float.parseFloat(mInputField.getText() + "");
                mJustGaveAnswer = true;

                if (mAddition) {
                    mInputField.setText(mValueOne + mValueTwo + "");
                    mAddition = false;
                }

                if (mSubtract) {
                    mInputField.setText(mValueOne - mValueTwo + "");
                    mSubtract = false;
                }

                if (mMultiplication) {
                    mInputField.setText(mValueOne * mValueTwo + "");
                    mMultiplication = false;
                }

                if (mDivision) {
                    // Check for infinity, no infinities allowed!
                    if (mValueTwo == 0) {
                        inputNumber("0");
                        mDivision = false;
                    } else {
                        mInputField.setText(mValueOne / mValueTwo + "");
                        mDivision = false;
                    }
                }
            }
        });

        mButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputField.setText("0");
            }
        });

        mButtonDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputField.setText(mInputField.getText() + ".");
            }
        });

        mButtonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initializeScreen() {
        mButton0 = (Button) findViewById(R.id.button0);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton5 = (Button) findViewById(R.id.button5);
        mButton6 = (Button) findViewById(R.id.button6);
        mButton7 = (Button) findViewById(R.id.button7);
        mButton8 = (Button) findViewById(R.id.button8);
        mButton9 = (Button) findViewById(R.id.button9);
        mButtonDecimal = (Button) findViewById(R.id.button10);
        mButtonAdd = (Button) findViewById(R.id.buttonadd);
        mButtonSubtract = (Button) findViewById(R.id.buttonsub);
        mButtonMultiply = (Button) findViewById(R.id.buttonmul);
        mButtonDivision = (Button) findViewById(R.id.buttondiv);
        mButtonClear = (Button) findViewById(R.id.buttonC);
        mButtonEquals = (Button) findViewById(R.id.buttoneql);
        mInputField = (TextView) findViewById(R.id.textView_input);
        mButtonDone = (Button) findViewById(R.id.button_done);
    }

    private void inputNumber(String number) {

        if (mJustGaveAnswer == true) {
            mInputField.setText(number);
            mJustGaveAnswer = false;
        } else {
            if (mInputField.getText() == "0") {
                mInputField.setText(number);
            } else {
                String newNumber = mInputField.getText() + number;
                mInputField.setText(newNumber);
            }
        }

    }


}
