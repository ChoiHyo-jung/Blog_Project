let index = {
	init: function() {
		$("#btn-save").on("click", () => { //this를 바인딩하기 위해 사용 
			this.save();
		});
	},

	save: function() {
		let data = {
			userName: $("#userName").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		console.log("####" + data);

		//$.ajax().done().fail()
		//ajax 호출시 default가 비동기 호출 
		$.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data), //자바스크립트 obj를 -> json으로 
			contentType: "application/json; charset=utf-8",
			dataType: "json" //응답이 왔을 때 기본적으로 모든것이 문자열 인데 (생긴게) json -> 자바스크립트 obj로 파싱해서 resp로 
		}).done(function(resp) {
			alert("회원가입 성공");
			console.log( resp);
			//location.href = "/blog";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청 
	}
}

index.init();