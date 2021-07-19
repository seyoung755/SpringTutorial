let index = {
    init: function (){
        // btn-save(회원가입 완료) 버튼을 누를 때 이벤트 생성
        $("#btn-save").on("click", ()=>{
            this.save();
        });


    },

    save: function() {
        // alert('user의 save함수 호출됨');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        // console.log(data);

        // ajax 호출 시 default가 비동기 호출
        // ajax를 이용해서 jsonify하여 서버에 요청
        // ajax가 통신에 성공하고 서버로부터 json을 받으면 자동으로 object로 받는다.
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8",
            dataType: "json", // 요청에 대한 응답이 왔을 때 기본적으로 문자열이지만 생긴게 json이면 => javascript object로 변경
            // 회원가입 수행 요청
        }).done(function (res){
            // 회원가입 수행 성공 시 호출
            alert("회원가입이 완료되었습니다");
            location.href = "/";
        }).fail(function (error) {
            // 실패 시 호출
            alert(JSON.stringify(error));
        }); // ajax 통신을 이용해서 3개의 parameter를 json으로 변경하여 insert 요청

    }




}

index.init();