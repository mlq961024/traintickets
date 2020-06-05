$("#head_car").hover(
    //鼠标移入效果
    function(){
        $("#head_car").css("background","#ffb901");
        $(".head_car_text").css("color","red");
    },
    //鼠标移出效果
    function(){
        $("#head_car").css("background","#424242");
        $(".head_car_text").css("color","#b0b0b0");
    }
);
//GO的悬浮效果
$("#find_but").hover(
    function(){
        $(this).css({"background":"#ffc507",color:"#fb2708"});
    },
    function(){
        $(this).css({"background":"#fff",color:"#424242"});
    }
);
//搜索框
$("#find_input").focus(
    function(){
        $("#find_warp").css("border","1px solid #fb2708");
        $("#find_but").css("border-left","1px solid #fb2708");
    }
);
$("#find_input").blur(function(){
    $("#find_warp").css("border","1px solid #f0f0f0");
    $("#find_but").css("border-left","1px solid #f0f0f0");
});
//导航栏悬浮
$(".menu_li").hover(
    function(){
        //背景的展现
        $("#menu_content_bg").css("border","1px solid #f0f0f0");
        $(this).css("color","#fc2b0a");
        //显示框大小
        $("#menu_content_bg").height(230);
        //根据control属性找到对应的图片显示
        $("#"+$(this).attr("control")).show();
    },
    function(){
        //背景的展现
        $("#menu_content_bg").css("border","1px solid #f0f0f0");
        $(this).css("color","#424242");
        //显示框大小
        $("#menu_content_bg").height(0);
        //根据control属性找到对应的图片显示
        $("#"+$(this).attr("control")).hide();
    }
);

//悬浮导航
$("#banner_menu_warp").children().hover(
    function(){
        $(this).css("background","#ec3c00");
        $(this).children(".banner_menu_content").css("border","1px solid #f0f0f0").show();
    },
    function(){
        $(this).css("background","none");
        $(this).children(".banner_menu_content").css("border","1px solid #f0f0f0").hide();
    });

//轮播图
$(function(){
    var i = 0;
    //定位到轮播列表
    var big_banner_pic = $("#big_banner_pic");
    //轮播长度
    var allimg = $("#big_banner_pic li").length;
    //轮播方法
    function img_change(){

        //轮换每张图的位置
        var img_i = i*-1226+"px";
        //动画
        big_banner_pic.animate(
            {opacity:"0.8"},1000,function(){
                big_banner_pic.css("left",img_i);
                big_banner_pic.animate(
                    {opacity:"1"},1000);
            });
    }
    //改变i的值，此方法会在周期性定时器里调用
    function automatic(){

        i+=1;
        if(i>=allimg){
            i=0;
        }
        img_change();
    }
    //周期性定时器
    change_self_time = setInterval(automatic,3000);

    //当鼠标移入轮播区域的时候
    $("#big_banner_change_warp").hover(
        function(){
            //清除定时器
            clearInterval(change_self_time);
            //轮播符号要出现
            $("#big_banner_change_warp").children().show();
        },
        function(){
            //开始定时器
            change_self_time = setInterval(automatic,3000);
            //轮播符号要隐藏
            $("#big_banner_change_warp").children().hide();
        }
    );

    //符号翻页
    $("#big_banner_change_prev").click(
        function(){
            i+=1;
            if(i>=allimg){
                i=0;
            }
            img_change();
        }
    );

    $("#big_banner_change_next").click(
        function(){
            i-=1;
            if(i<=0){
                i=allimg-1;
            }
            img_change();
        }
    );
});


//换图符号的点击时间
$("#head_hot_goods_prave").hover(
    function(){
        $(this).css("color","#0070ea");
    },
    function(){
        $(this).css("color","#c8bebe");
    }
);

$("#head_hot_goods_next").hover(
    function(){
        $(this).css("color","#0070ea");
    },
    function(){
        $(this).css("color","#c8bebe");
    }
);

//点击
$("#head_hot_goods_prave").click(
    function(){
        $("#head_hot_goods_content").children("ul").animate({
            left:"-1226px"
        },300);
    }
);
$("#head_hot_goods_next").click(
    function(){
        $("#head_hot_goods_content").children("ul").animate({
            left:"0px"
        },300);
    }
);

//悬浮动态效果
$(".floor_goods_wrap_li").hover(
    function(){
        $(this).css({"top":"-10px","box-shadow":"0px 15px 30px rgba(0,0,0,0.2)"});
    },function(){
        $(this).css({"top":"0px","box-shadow":"none"});
    }
);
