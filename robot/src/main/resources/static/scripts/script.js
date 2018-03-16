/**
 * <p>主JS文件</p>
 */
$(document).ready(function() {
	
	$chatbox = $("#chatbox");
	function init(){
		$.ajaxSetup({  
		    async : false
		});
		// 加载好友页面
		$chatbox.load("/robot/list",{});
	};
	
	init();
});