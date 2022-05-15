let index = {
		init: function() {
			$("#btn-save").on("click", ()=>{
				this.save(); 
			});  
		},
		
		save: function() {
			let data = {
				title: $("#title").val(),
				content: $("#content").val(),
			}

			$.ajax({
				type: "post",
				url: "/api/board", // 요청할 url
				data: JSON.stringify(data), 
				contentType: "application/json; charset=utf-8", 
				dataType: "json" 
			}).done(function(resp){ 
				alert("글쓰기가 완료되었습니다.");
				location.href = "/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		}

}
// 위까지는 그냥 오브젝트이기 때문에 아무일도 일어나지 않는다.
// 따라서 호출해주어야 작동한다.
index.init();
