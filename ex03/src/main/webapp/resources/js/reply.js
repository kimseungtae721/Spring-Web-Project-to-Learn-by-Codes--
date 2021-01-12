console.log("reply 테스트2222")

let replyService = (function() {

	function add(reply, callback, error) {

		console.log("reply.....");

	$.ajax({
		type: 'post', // 타입 (get, post, put 등등)
		url: '/replies/new', // 요청할 서버url
		contentType: "application/json; charset=utf-8",  //전송방식
		data: JSON.stringify(reply),// 보낼 데이터 Object , String, Array
		success: function(result, status, xhr) { // 결과 성공 콜백함수
			if (callback) {
				callback(result);
			}
		},
		error: function(xhr, status, er) { // 결과 에러 콜백함수
			if (error) {
				error(er);
			}
		}
	})
}
	//param이라는 객체를 통해 필요한 파라미터를 전달받아 json목록을 호출
	function getList(param, callback, error) {
		
		var bno = param.bno;
		var page = param.page || 1;
		
		
		//jquery의 메서드 이용
		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
			function(data){
				if(callback){
					callback(data);
				}
			}).fail(function(xhr, status, err){
				if(error){
					error();
				}
			});
		}
		
		//댓글삭제와 갱신 ( delete방식으로 데이터를 전달하므로 ajax를 이용해서 type 속성을 지정해줌)
	function remove(rno, callback, error){
		
		$.ajax({
			type: 'delete',
			url: '/replies/' + rno,
			success : function(deleteResult, status, xhr){
				if(callback){
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}			
		})
	}
	//수정 하는내용과 함께 댓글의 번호를 전송
	function update(reply, callback, error){
		
		console.log("RNO--------------: "+ reply.rno);
		
		$.ajax({
			type: 'put',
			url: '/replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});		
}
	//특정 조회 처리
	function get(rno, callback, error){
		
		$.get("/replies/" + rno + ".json", function(result){
			
			if(callback){
				callback(result);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}	
	return { 
		add: add, 
		getList: getList,
		remove: remove,
		update: update,
		get: get
		};
	})
();