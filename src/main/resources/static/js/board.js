let index = {
		init: function() {
			$("#btn-save").on("click", ()=>{
				this.save(); 
			});  
			$("#btn-delete").on("click", ()=>{
				this.deleteById(); // delete는 예약어라 deleteById로 하자
			}); 
			$("#btn-update").on("click", ()=>{
				this.update(); 
			});
			
		},
		
		save: function() {
			let data = {
				title: $("#title").val(),
				content: $("#content").val(),
			}

			$.ajax({
				type: "POST",
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
		},
		
		deleteById: function() {
			let id = $("#id").text();
			$.ajax({
				type: "DELETE",
				url: "/api/board/"+id, // 요청할 url
				dataType: "json" 
			}).done(function(resp){ 
				alert("삭제가 완료되었습니다.");
				location.href = "/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		},
		
		update: function() {
			let id = $("#id").val()
			
			let data = {
				title: $("#title").val(),
				content: $("#content").val(),
			}

			$.ajax({
				type: "PUT",
				url: "/api/board/"+id, // 요청할 url
				data: JSON.stringify(data), 
				contentType: "application/json; charset=utf-8", 
				dataType: "json" 
			}).done(function(resp){ 
				alert("글 수정이 완료되었습니다.");
				location.href = "/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		}

}
// 위까지는 그냥 오브젝트이기 때문에 아무일도 일어나지 않는다.
// 따라서 호출해주어야 작동한다.
index.init();
