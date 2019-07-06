package com.example.lenovo.tictac;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button[][] buttons = new Button[3][3];

    TextView textView;

    private boolean turn = true,status = true;

    private int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++)
            {
                String buttonID = "b_" + i + j;
                int value = getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j] = (Button) findViewById(value);
                buttons[i][j].setOnClickListener(this);
            }
        }
        change_background();

    }

    public void change_background(){
        LinearLayout l = (LinearLayout) findViewById(R.id.llout);
        int x;
        Random dice = new Random();

        x = dice.nextInt(500);
        x += 5;
        x = x % 5;
        switch(x)
        {
            case 0:
                l.setBackgroundResource(R.drawable.b);
                break;
            case 1:
                l.setBackgroundResource(R.drawable.b0);
                break;
            case 2:
                l.setBackgroundResource(R.drawable.b1);
                break;
            case 3:
                l.setBackgroundResource(R.drawable.b2);
                break;
            case 4:
                l.setBackgroundResource(R.drawable.b3);
                break;
        }
    }
    public void reset(View v)
    {
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                buttons[i][j].setText("");
            }
        }
        change_background();
        textView.setText("Play..");
        status = true;
        turn = true;
        counter = 0;
        textView.setTextColor(Color.parseColor("white"));
    }
    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }
        if (status)
        {
            if(turn)
            {
                ((Button) v).setText("X");
                counter++;
            }
            else
            {
                ((Button) v).setText("0");
                counter++;
            }
            if(check()==1)
            {
                if(turn)
                {
                    textView.setText("X win");
                }
                else
                {
                    textView.setText("0 win");
                }
                status = false;
            }
            else if(counter == 9)
            {
                textView.setText("Draw");
                status = false;
            }
            turn =! turn;
        }
        else
        {
            if(!turn)
            {
               textView.setText("How many time i have to tell you > X win");
            }
            else
            {
                textView.setText("How many time i have to tell you > 0 win");
            }
            textView.setTextColor(Color.parseColor("green"));
        }

    }
    public int check()
    {
        String[][] play = new String[3][3];
        for(int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                play[i][j] = buttons[i][j].getText().toString();
            }
        }

        for(int i=0;i<3;i++)
        {
            if((play[i][0].equals(play[i][1])) && (play[i][0].equals(play[i][2])) && !(play[i][0].equals("")))
                return 1;
            if(play[0][i].equals(play[1][i]) && play[0][i].equals(play[2][i]) && !play[0][i].equals(""))
                return 1;
            if((play[0][0].equals(play[1][1])) && (play[0][0].equals(play[2][2])) && !(play[0][0].equals("")))
                return 1;
            if((play[0][2].equals(play[1][1])) && (play[0][2].equals(play[2][0])) && !(play[0][2].equals("")))
                return 1;
        }
        return 0;
    }


}
