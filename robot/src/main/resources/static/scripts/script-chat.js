/**
 * <p>聊天室JS文件</p>
 */
$(document).ready(function() {
	
	var $context = $("#context");
	var $chatMessages = $("#chat-messages");
	var $id = $("#userid").val();
	var $more = 1;
	var $headPortrait = $("#headPortrait").val();
	
	function init(){
		// 初始化的时候获取一条历史记录
		$.ajaxSetup({  
		    async : false
		});
		$.post("/robot/history",{userid:$id,pageNo:$more},function(result){
			$.each(result,function(i,n){
				var createTime = $.data(new Date(n.createTime));
				var $s = $("<div class=\"message\"><img src=\"images/"+$headPortrait+"\" /><div class=\"bubble\">"+n.sendContent+"<div class=\"corner\"></div><span>"+createTime+"</span></div></div>");
				var $r = $("<div class=\"message right\"><img src=\"images/6_copy.jpg\" /><div class=\"bubble\">"+n.replyContent+"<div class=\"corner\"></div><span>"+createTime+"</span></div></div>");
				$chatMessages.append($s);
				$chatMessages.append($r);
			});
		});
	};
	
	// 点击more显示更多的聊天消息
	$("#more").on("click", function(){
		$more++;
		$.ajaxSetup({  
		    async : false
		});
		$.post("/robot/history",{userid:$id,pageNo:$more},function(result){
			if(result.length == 0){
				$("#more").text("无更多消息显示");
			};
			$.each(result,function(i,n){
				var createTime = $.data(new Date(n.createTime));
				var $s = $("<div class=\"message\"><img src=\"images/"+$headPortrait+"\" /><div class=\"bubble\">"+n.sendContent+"<div class=\"corner\"></div><span>"+createTime+"</span></div></div>");
				var $r = $("<div class=\"message right\"><img src=\"images/6_copy.jpg\" /><div class=\"bubble\">"+n.replyContent+"<div class=\"corner\"></div><span>"+createTime+"</span></div></div>");
				$chatMessages.find("#more").after($r);
				$chatMessages.find("#more").after($s);
			});
		});
	});
	
	// 点击发送按钮，发送消息
	$("#send").on("click", function(e) {
		e.preventDefault();
		console.log("点击发送按钮,userid="+$("#userid").val());
		// 获取输入框的内容
		var content = $context.val();
		
		// 如果发送的值为null ，就不发送请求
		if(content.trim()!=''){
			// 渲染数据到展示页面
			var time1 = $.data(new Date());
			console.log(time1);
			
			// 过滤敏感词汇
			$.ajaxSetup({  
			    async : false
			});
			$.post("/robot/filter",{sendContent:content},function(result){
				var $send = $("<div class=\"message\"><img src=\"images/"+$headPortrait+"\" /><div class=\"bubble\">"+result+"<div class=\"corner\"></div><span>"+time1+"</span></div></div>");
				$chatMessages.append($send);
			});
			
			// 将滚动条滚动到可视位置
			$(".message")[$(".message").length-1].scrollIntoView();
			$("#context").val('');
			$.ajaxSetup({  
			    async : false
			});
			$.post("/robot/send",{sendContent:content,userid:$id},function(result){
				console.log("result的结果是---"+result);
				var time2 = $.data(new Date());
				var $receive = $("<div class=\"message right\"><img src=\"images/6_copy.jpg\" /><div class=\"bubble\">"+result+"<div class=\"corner\"></div><span>"+time2+"</span></div></div>");
				$chatMessages.append($receive);
				$(".message")[$(".message").length-1].scrollIntoView();
			});
		}
	});
	
	$context.keydown(function() {
        if (event.keyCode == "13") {	//keyCode=13是回车键
        	$("#send").click();
        }
    });
	
    
    $('#close').click(function() {         //unbind() 方法移除被选元素的事件处理程序。  
        $('#chat-messages, #profile, #profile p').removeClass('animate');//移除animate的样式
        $('.cx, .cy').removeClass('s1 s2 s3');//移除s1 s2 s3的样式
        $('.floatingImg').animate({
            'width': '40px',
            'top': top,
            'left': '12px'
        }, 200, function() {
            $('.floatingImg').remove();//remove() 方法移除被选元素，包括所有文本和子节点。
        });
        setTimeout(function() {
            $('#chatview').fadeOut();//好友聊天界面淡出
            $('#friendslist').fadeIn();//好友列表组淡入
        }, 50);
    });
    
    $.data = function(time){
    	var y = time.getFullYear();
    	var m = time.getMonth()+1;
    	var d = time.getDate();
    	var h = time.getHours();
    	var mm = time.getMinutes();
    	var s = time.getSeconds();
    	return /*y+'-'+$.add0(m)+'-'+$.add0(d)+' '+*/$.add0(h)+':'+$.add0(mm)+':'+$.add0(s);
    }
    
    $.add0 = function(m){
    	return m<10?'0'+m:m;
    }
    
    init();
});