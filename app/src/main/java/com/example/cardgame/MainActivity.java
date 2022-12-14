package com.example.cardgame;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //버튼 배열
    private ImageButton[] buttons =  new ImageButton[8];

    //이미지 리스트
    private ArrayList<Integer> imageList;

    //카드 리스트
    private ArrayList<MemoryCard> cards;

    //결과 텍스트
    private TextView resultText;

    //초기화 버튼
    private TextView resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.result_text);

        init();

        Button resetBtn = findViewById(R.id.reset_btn);
        resetBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                init();
            }
        });


    }//onClick

    //초기화
    public void init(){

        //리스트에 데이터 등록
        imageList = new ArrayList<>();
        imageList.add(R.drawable.dog);
        imageList.add(R.drawable.pig);
        imageList.add(R.drawable.rabbit);
        imageList.add(R.drawable.cat);
        imageList.add(R.drawable.dog);
        imageList.add(R.drawable.pig);
        imageList.add(R.drawable.rabbit);
        imageList.add(R.drawable.cat);

        //순서 섞기
        Collections.shuffle(imageList);

        //카드 리스트 초기화
        cards = new ArrayList<>();

        //버튼 초기화
        for(int i = 0; i < buttons.length; i++){

            String buttonID = "imageBtn" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resourceID);

            //각 버튼에 클릭이벤트 적용
            buttons[i].setOnClickListener(this);

            //카드 리스트에 담기
            cards.add(new MemoryCard(imageList.get(i), false, false));

            buttons[i].setImageResource(R.drawable.question);
        }
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        int position = 0;

        if(id == R.id.imageBtn0){
            position = 0;

        }else if(id == R.id.imageBtn1){
            position = 1;
        }else if(id == R.id.imageBtn2){
            position = 2;
        }else if(id == R.id.imageBtn3){
            position = 3;
        }else if(id == R.id.imageBtn4){
            position = 4;
        }else if(id == R.id.imageBtn5){
            position = 5;
        }else if(id == R.id.imageBtn6){
            position = 6;
        }else if(id == R.id.imageBtn7){
            position = 7;
        }

        //업데이트 모델
        updateModel(position);

        //업데이트 뷰
        updateView(position);

    }// onClick()

    //데이터변경
    private void updateModel(int position){

        MemoryCard card = cards.get(position);

        //나중에 카드 비교할 때 뒤집고 재클릭 방지
        if (card.isFaceUp()) {

            Toast.makeText(this, "이미 뒤집혔음", Toast.LENGTH_SHORT).show();
            return;

        }// if

        //반대의 값을 넣는다 (앞면->뒷면, 뒷면->앞면)
        cards.get(position).setFaceUp(!card.isFaceUp());

    }// updateModel

    //뷰 변경
    private void updateView(int position){

        MemoryCard card = cards.get(position);

        //뒤집었으면 랜덤 이미지 보여준다.
        if(card.isFaceUp()){
            buttons[position].setImageResource(card.getImageId());
        } else {
            //기본 이미지
            buttons[position].setImageResource(R.drawable.question);
        }// if

    }// updateView



}//MainActivity