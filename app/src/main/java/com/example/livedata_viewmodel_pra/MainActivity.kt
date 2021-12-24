package com.example.livedata_viewmodel_pra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.livedata_viewmodel_pra.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    // 나중에 값이 설정될거라고 lateinit 으로 설정
    lateinit var myNumberViewModel: MyNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myNumberViewModel = ViewModelProvider(this)[MyNumberViewModel::class.java]

        //라이브 데이터에 접근
        myNumberViewModel.currentValue.observe(this, Observer { // 데이터의 변경을 계속 관찰함
            Log.d("로그","MainActivity - MyNumberViewModel - currentValue 라이브 데이터 값 변경 : $it")
            binding.numberTextview.text = it.toString()
        })

        //리스너 연결
        binding.plusBtn.setOnClickListener(this)//더하기 버튼이 눌렸을때
        binding.minusBtn.setOnClickListener(this)//빼기 버튼이 눌렸을때
    }

    // 클릭
    override fun onClick(view: View?) {
        val userInput : Int = binding.userinputEdittext.text.toString().toInt()//사용자가 edittext에 입력한 값

        // 뷰모델에 라이브데이터 값을 변경하는 메소드 실행
        when(view){
            binding.plusBtn ->
                myNumberViewModel.updateValue(actionType = ActionType.PLUS, userInput)//뷰 모델에 있는 updateValue라는 메소드 실행
            binding.minusBtn ->
                myNumberViewModel.updateValue(actionType = ActionType.MINUS, userInput)//뷰 모델에 있는 updateValue라는 메소드 실행
        }
    }
}