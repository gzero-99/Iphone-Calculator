package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var num = findViewById(R.id.textNumber) as TextView
        var preNum = 0f //연산자 누르기 이전 값 초기화
        var calc = 0f   //계산 값 초기화
        var count = 0 //연산자 누른 횟수.
        var clicked = false//연산자 누른 후 문자열비우기 위함
        var dclicked=false
        var sclicked=false
        var mclicked= false
        var pclicked = false
        var rclicked = false
        var isCleared = false
        var signed = false
        //값 출력할때 끝에서 두 인덱스가 '.0'이면 int로 바꿔서 출력 하려면 . .? 이거 아직 못 추가함

        result.setOnClickListener { //계산 결과 출력
            var num_text : String = num.text.toString()
            if( count == 0 ){ //float으로 못바꿔서 강종됨
                //버튼이 눌려도 아무작업하지 않기
            }
            else if(dclicked == true) {
                calc = preNum / num_text.toFloat()
                dclicked = false
               // if( calc == calc.toInt()) //.0없애고 싶은데 ,,
                num.setText(calc.toString())
                clicked = true
                rclicked = true
            }
            else if(sclicked == true){
                calc = preNum - num_text.toFloat()
                num.setText(calc.toString())
                sclicked = false
                clicked = true
                rclicked = true
            }
            else if(mclicked == true){
                calc = preNum * num_text.toFloat()
                mclicked = false
                num.setText(calc.toString())
                clicked = true
                rclicked = true
            }
            else if(pclicked == true){
                calc = preNum + num_text.toFloat()
                pclicked = false
                num.setText(calc.toString())
                clicked = true
                rclicked = true
            }

        }
        clear.setOnClickListener{
            preNum =  num.text.toString().toFloat() // 지금 값은 preNum에 저장해두고,
            isCleared = true //이 기준으로 연산자 사용시 적용 유무를 가린다.
            num.setText("") //현재 num을 0으로 비워버리기
        }
        allclear.setOnClickListener{ //이제까지 모든 것 지워버리기
            preNum=0f
            calc = 0f
            count = 0
            clicked = false
            dclicked=false
            sclicked=false
            mclicked= false
            pclicked = false
            signed = false
            num.setText("")
        }
        percent.setOnClickListener{//백분율
            if( num.text.toString().equals("")==true ){ //float으로 못바꿔서 강종됨
                //버튼이 눌려도 아무작업하지 않기
            }
            else{
                //if(num.text.toString().toFloat()) // 숫자-> 퍼센트 -> 숫자 면 text 값 새로 시작해야함
                var num_text: Float = num.text.toString().toFloat()
                var percentCalc = num_text / 100
                num.setText(percentCalc.toString())
            }
        }
        //  < 사칙연산 >
        // 맨 처음 누른 버튼이면 , 버튼 누르기 전의 이전 값 저장 , 이전 숫자 띄워놓고  버튼 눌린 효과 있게 했다가 새 숫자 입력받기
        // 1번 이후의 횟수이면 저장된 이전값과 현재 입력된 값의 계산 결과 출력, 버튼 눌린 효과 있게 했다가 새 숫자 입력받기.
        division.setOnClickListener{
            count++
            var num_text : String = num.text.toString()
            if(count==1 && num_text.equals("") == true) {
                //숫자 입력안하고 실수로 연산자부터 누르면 ? 아무것도 안하기
                count =0
            }
            else if(count==1 || rclicked == true){
                preNum = num_text.toFloat()
                dclicked = true
                clicked = true
                rclicked = false
            }
            else {
                if(isCleared == true) {
                    isCleared = false
                    //clear 버튼 누를때 , 넣어 저장된 preNum이 사용됨
                }

                dclicked = true
                if(pclicked == true){
                    calc = preNum +  num_text.toFloat()
                    pclicked = false
                }
                else if(mclicked == true){
                    calc = preNum *  num_text.toFloat()
                    mclicked = false
                }
                else if(sclicked == true){
                    calc = preNum -  num_text.toFloat()
                    sclicked = false
                }
                else if(dclicked == true){
                    calc = preNum /  num_text.toFloat()
                    // dclicked = false
                }
                else {
                   // preNum = num_text.toFloat()
                    calc = preNum /  num_text.toFloat()
                    dclicked = false
                }
                count = 1
                preNum = calc //계산값을 이전값으로 저장해주기 .
                num.setText(calc.toString())
                clicked = true
                rclicked = false
            }

        }
        multiple.setOnClickListener{
            count++
            var num_text : String = num.text.toString()
            if(count==1 && num_text.equals("") == true) {
                //숫자 입력안하고 실수로 연산자부터 누르면 ? 아무것도 안하기
                count =0
            }
            else if(count==1 || rclicked == true){
                preNum = num_text.toFloat()
                mclicked = true
                clicked = true
                rclicked = false
            }
            else {
                if(isCleared == true) {
                    isCleared = false
                    //clear 버튼 누를때 , 넣어 저장된 preNum이 사용됨
                }

                mclicked = true
                if(pclicked == true){
                    calc = preNum +  num_text.toFloat()
                    pclicked = false
                }
                else if(dclicked == true){
                    calc = preNum /  num_text.toFloat()
                    dclicked = false
                }
                else if(sclicked == true){
                    calc = preNum -  num_text.toFloat()
                    sclicked = false
                }
                else if(mclicked == true){
                    calc = preNum *  num_text.toFloat()
                    //mclicked = false
                }
                else {
                  //  preNum = num_text.toFloat()
                    calc = preNum *  num_text.toFloat()
                    mclicked = false
                }
                count = 1
                preNum = calc //계산값을 이전값으로 저장해주기 .
                num.setText(calc.toString())
                clicked = true
                rclicked = false
            }
        }
        subtract.setOnClickListener{
            count++
            var num_text : String = num.text.toString()
            if(count==1 && (num_text.equals("") == true )) {//|| preNum == 0f
                //숫자 입력안하고 실수로 연산자부터 누르면 ? 아무것도 안하기
                count = 0
                num.setText("-") //- 붙여주자 .
                //"" - 2 = 입력하면 -2 출력하도록 해야함 . '-' 가 붙어야한다는 의미로 bool추가하던가 ..
            }
            else if(count==1 || rclicked == true){
                preNum = num_text.toFloat()
                sclicked = true
                clicked = true
                rclicked = false
            }
            else {
                if(isCleared == true) {
                    isCleared = false
                    //clear 버튼 누를때 , 넣어 저장된 preNum이 사용됨
                }

                sclicked = true
                if(pclicked == true){
                    calc = preNum +  num_text.toFloat()
                    pclicked = false

                }
                else if(mclicked == true){
                    calc = preNum *  num_text.toFloat()
                    mclicked = false
                }
                else if(dclicked == true){
                    calc = preNum /  num_text.toFloat()
                    dclicked = false
                }
                else if(sclicked == true){
                    calc = preNum -  num_text.toFloat()
                    //sclicked = false
                }
                else {
                  //  preNum = num_text.toFloat()
                    calc = preNum -  num_text.toFloat()
                    sclicked = false
                }
                count = 1
                preNum = calc //계산값을 이전값으로 저장해주기 .
                num.setText(calc.toString())
                clicked = true
                rclicked = false
            }
        }
        plus.setOnClickListener{
            count++
            var num_text : String = num.text.toString()
            if(count==1 && num_text.equals("") == true) {
                //숫자 입력안하고 실수로 연산자부터 누르면 ? 아무것도 안하기
                count =0
            }
            else if(count==1 || rclicked == true){
                preNum = num_text.toFloat()
                pclicked = true
                clicked = true
                rclicked = false
            }
            else {
                if(isCleared == true) {
                    isCleared = false
                    //clear 버튼 누를때 , 넣어 저장된 preNum이 사용됨
                }

                pclicked = true
                if(dclicked == true){
                    calc = preNum /  num_text.toFloat()
                    dclicked = false
                }
                else if(mclicked == true){
                    calc = preNum *  num_text.toFloat()
                    mclicked = false
                }
                else if(sclicked == true){
                    calc = preNum -  num_text.toFloat()
                    sclicked = false
                }
                else if(pclicked == true){
                    calc = preNum +  num_text.toFloat()
                    //pclicked = false
                }
                else {
                  //  preNum = num_text.toFloat()
                    calc = preNum +  num_text.toFloat()
                    pclicked = false
                }
                count = 1
                preNum = calc //계산값을 이전값으로 저장해주기 .
                num.setText(calc.toString())
                clicked = true
                rclicked = false
            }
        }
        //부호변경
        sign.setOnClickListener {
            var nt:String = num.text.toString()
            fun isNegative(c: Char) = c in nt
            if(nt.equals("")==true){
                num.setText("-0")
            }
            else if(clicked==true){ //사칙연산이 눌린 상태라면
                signed =  true
                //사칙연산 눌렸으면서 , 내용이 있을때(c=F) 와 아직 숫자 누르기 전(c=T)
                if(isNegative('-')==false) { // -가 없다면
                    num.setText("-0")                }
                else { // - 가 있다면
                    num.setText(nt.replace("-",""))
                }
            }
            //연산자 눌린 후 숫자 입력한 상태(c=F)
            else if(isNegative('-')==false) { // -가 없다면
                num.setText("-"+nt)
            }
            else { // - 가 있다면
                num.setText(nt.replace("-",""))
            }

        }
        // <숫자 입력 버전 2 >
        var listener = object: View.OnClickListener{
            override fun onClick(v: View?) {
                if( clicked == true ){
                    if(signed==true) { //부호눌렀으면 이미 초기화해둠
                        signed = false
                    }
                    else { num.setText("") } //초기화
                    clicked = false
                }
                var num_text : String = num.text.toString()
                if(num_text.equals("0")==true){
                    when (v?.id) {
                        one.id -> num.setText("1")
                        two.id -> num.setText("2")
                        three.id -> num.setText("3")
                        four.id -> num.setText("4")
                        five.id -> num.setText("5")
                        six.id -> num.setText("6")
                        seven.id -> num.setText( "7")
                        eight.id -> num.setText( "8")
                        nine.id -> num.setText("9")
                        zero.id -> num.setText("0")
                    }
                }
                else if(num_text.equals("-0")==true){
                    when (v?.id) {
                        one.id -> num.setText("-1")
                        two.id -> num.setText("-2")
                        three.id -> num.setText("-3")
                        four.id -> num.setText("-4")
                        five.id -> num.setText("-5")
                        six.id -> num.setText("-6")
                        seven.id -> num.setText( "-7")
                        eight.id -> num.setText( "-8")
                        nine.id -> num.setText("-9")
                        zero.id -> num.setText("-0")
                    }
                }
                else {
                    when (v?.id) {
                        one.id -> num.setText(num_text + "1")
                        two.id -> num.setText(num_text + "2")
                        three.id -> num.setText(num_text + "3")
                        four.id -> num.setText(num_text + "4")
                        five.id -> num.setText(num_text + "5")
                        six.id -> num.setText(num_text + "6")
                        seven.id -> num.setText(num_text + "7")
                        eight.id -> num.setText(num_text + "8")
                        nine.id -> num.setText(num_text + "9")
                        zero.id -> num.setText(num_text + "0")
                    }
                }
            }
        }
        one.setOnClickListener (listener)
        two.setOnClickListener (listener)
        three.setOnClickListener (listener)
        four.setOnClickListener (listener)
        five.setOnClickListener (listener)
        six.setOnClickListener (listener)
        seven.setOnClickListener (listener)
        eight.setOnClickListener (listener)
        nine.setOnClickListener (listener)
        zero.setOnClickListener (listener)

        point.setOnClickListener{
            if(clicked == true){
                num.setText("")
                clicked = false
            }
            var num_text : String = num.text.toString()
            fun isPoint(c: Char) = c in num_text
            if(isPoint('.') == false) { //점은 한번만
                num.setText(num_text + ".")
            }
            var nt : String = num.text.toString()
            if(nt.indexOf('.') == 0) { // 빈칸에 .찍으면 0.으로 출력되도록
                num.setText("0" + nt)
            }
        }
    }

}
