package com.example.livedata_viewmodel_pra

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType {
    PLUS,MINUS
}

//데이터의 변경과 관련된것들
class MyNumberViewModel : ViewModel() {
    // 뮤터블 라이브 데이터 - 수정 가능한 친구
    // 라이브 데이터 - 값 변경 불가능 : read only

    private val _currentValue = MutableLiveData<Int>()//LiveData에 맵핑이 되어있는 상태

    val currentValue: LiveData<Int>
        get() = _currentValue

    //초기값 설정정
    init {
        Log.d("로그", "MyNumberViewModel - 생성자 호출")
        _currentValue.value = 0 //MutableLiveData이기 때문에 값 변경 가능 일반적인 LiveData로는 불가능하다
        //.value를 하면 맵핑되어있는 값에 접근할수 있다
    }

    //뷰모델이 가지고 있는 값을 변경하는 메소드
    fun updateValue(actionType: ActionType, input: Int) {// 메인에서 액션타입과 값을 받아서 동작 실행
        when(actionType){
            ActionType.PLUS ->
                _currentValue.value = _currentValue.value?.plus(input)
            ActionType.MINUS ->
                _currentValue.value = _currentValue.value?.minus(input)
        }
    }
}