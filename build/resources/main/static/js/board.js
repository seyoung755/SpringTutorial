let index = {
    init: function (){
        $("#btn-save").on("click", ()=>{
            this.save();
        });
        $("#btn-delete").on("click", ()=>{
            this.deleteById();
        });
        $("#btn-update").on("click", ()=>{
            this.update();
        });


    },

    save: function() {
        // alert('user의 save함수 호출됨');
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        }

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        }).done(function (res){
            alert("글쓰기가 완료되었습니다");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    deleteById: function() {
        var id = $("#id").text();

        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id,
            dataType: "json",
        }).done(function (res) {
            alert("삭제가 완료되었습니다");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    update: function() {
        var id = $("#id").text();
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        }

        $.ajax({
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        }).done(function (res){
            alert("수정이 완료되었습니다");
            location.href = "/board/" + id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });





    }};

index.init();