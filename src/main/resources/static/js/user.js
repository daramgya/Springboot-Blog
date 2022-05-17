let index = {
		init: function() {
			// jQuery 사용
			// function(){}이 아니라 ()=>{}를 사용한 이유는
			// this를 바인딩하기 위해서이다.
			$("#btn-save").on("click", ()=>{ // #btn-save(id) 찾아서 이벤트가 발생하면(click)
				this.save(); // 이 함수(save함수)가 호출된다.
			}); 
			$("#btn-update").on("click", ()=>{
				this.update();
			});  
			
		},
		
		save: function() {
			//alert("user의 save함수 호출됨");
			let data = {
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val() // 이것들을 찾아서 data 오브젝트(js 오브젝트)에 넣는다.
			}
			//console.log(data);
			
			// ajax 통신을 이용해 3개의 데이터를 json으로 변경하여 insert 요청
			$.ajax({
				type: "post",
				url: "/auth/joinProc", // 요청할 url
				data: JSON.stringify(data), // http body데이터, JSON.stringify( )는 자바스크립트의 값을 JSON 문자열로 변환한다.
				// 서버로 전송할 데이터이다. 즉, 서버에 요청할 때 보낼 매개변수 설정하는 것이다.
				contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
				dataType: "json" // 서버에서 전송받을 데이터형식을 말한다. 즉, 응답 받을 데이터 타입(XML,HTML,JSON..)을 설정한다.
			}).done(function(resp){ // 위 응답의 결과가 함수의 파라미터로 전달된다. 
				alert("회원가입이 완료되었습니다.");
				location.href = "/";
				console.log(resp);
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		},
		
		update: function() {
			let data = {
				id: $("#id").val(),
				password: $("#password").val(),
				email: $("#email").val()
			}
			
			$.ajax({
				type: "PUT",
				url: "/user", 
				data: JSON.stringify(data), 
				contentType: "application/json; charset=utf-8", 
				dataType: "json" 
			}).done(function(resp){  
				alert("회원수정이 완료되었습니다.");
				location.href = "/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		}

}
// 위까지는 그냥 오브젝트이기 때문에 아무일도 일어나지 않는다.
// 따라서 호출해주어야 작동한다.
index.init();
