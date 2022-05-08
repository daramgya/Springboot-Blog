let index = {
		init: function() {
			// jQuery 사용
			$("#btn-save").on("click", ()=>{ // #btn-save(id) 찾아서 이벤트가 발생하면(click)
				this.save(); // 이 함수(save함수)가 호출된다.
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
			$.ajax().done().fail();
		}
}
// 위까지는 그냥 오브젝트이기 때문에 아무일도 일어나지 않는다.
// 따라서 호출해주어야 작동한다.
index.init();
