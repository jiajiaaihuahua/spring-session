/**
 * <p>好友列表JS文件</p>
 */
$(document).ready(function() {
        var preloadbg = document.createElement('img');
        $chatbox = $("#chatbox");
        $searchfield = $("#searchfield");
        var $chatview = $("#chatview");
        preloadbg.src = 'images/timeline1.png';//添加聊天页面的头部背景图
        
        $('#searchfield').focus(function() {
            if ($(this).val() == '搜索...') {
                $(this).val('');
            }
        });
        $('#searchfield').focusout(function() {
            if ($(this).val() == '') {
                $(this).val('搜索...');
            }
        });
        
        $searchfield.keydown(function() {
        	
            if (event.keyCode == "13") {
            	$chatbox.load("/robot/search",{name:$searchfield.val()});
            }
        });
        $('.friend').each(function() { //each() 方法规定为每个匹配元素规定运行的函数。在这里是每一个好友列表
            $(this).click(function() {//点击好友列表
            	var $uid = $(this).find(".uid");
            	$.ajaxSetup({  
        		    async : false
        		});
            	$chatview.load("/robot/chat?userid="+$uid.val());
                var childOffset = $(this).offset();//offset() 方法返回或设置匹配元素相对于文档的偏移（位置）。
                var parentOffset = $(this).parent().parent().offset();//整个聊天框的元素相对于文档的偏移
                var childTop = childOffset.top - parentOffset.top;//各个好友分别到聊天框的上边界距离
                var clone = $(this).find('img').eq(0).clone();//关闭
                var top = childTop + 12 + 'px';
                $(clone).css({
                    'top': top
                }).addClass('floatingImg').appendTo('#chatbox');//appendTo() 方法在被选元素的结尾（仍然在内部）插入指定内容。addClass() 方法向被选元素添加一个或多个类。
                setTimeout(function() {//setTimeout() 方法用于在指定的毫秒数后调用函数或计算表达式。
                    $('#profile p').addClass('animate');
                    $('#profile').addClass('animate');
                }, 100);
                setTimeout(function() {
                    $('#chat-messages').addClass('animate');
                    $('.cx, .cy').addClass('s1');//添加s1的样式
                    setTimeout(function() {
                        $('.cx, .cy').addClass('s2');//添加s2的样式
                    }, 100);
                    setTimeout(function() {
                        $('.cx, .cy').addClass('s3');//添加s3的样式
                    }, 200);
                }, 150);
                $('.floatingImg').animate({//animate() 方法执行 CSS 属性集的自定义动画。
                    'width': '68px',
                    'left': '108px',
                    'top': '20px'
                }, 200);
                var name = $(this).find('p strong').html();//好友昵称
                var email = $(this).find('p span').html();//好友签名
                $('#profile p').html(name);
                $('#profile span').html(email);
                $('.message').not('.right').find('img').attr('src', $(clone).attr('src'));//聊天界面中左边的消息的头像   attr() 方法设置或返回被选元素的属性值。
                $('#friendslist').fadeOut();//fadeOut() 方法使用淡出效果来隐藏被选元素，假如该元素是隐藏的。  好友列表组淡出
                $('#chatview').fadeIn();//fadeIn() 方法使用淡入效果来显示被选元素，假如该元素是隐藏的。    好友聊天界面淡入
               
            });
        });
    });