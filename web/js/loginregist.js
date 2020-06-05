
//登陆用户名验证
function CheckUsename() {
    var username = $("#login-username").val();
    var regExp = new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5]{1,8}$");
    var flag = true ;
    if(!regExp.test(username)){
        $.pt({
            target: $("#login-username"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"用户名必须为8位以下的字母或数字或汉字"
        });
        flag=false;
    }
    //判断用户名密码是否为空
    if(username == ""){
        $.pt({
            target: $("#login-username"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"用户名不能为空"
        });
        flag=false;
    }
    return flag;
}
//登陆密码验证
function Checkpassword () {
    var password = $("#login-password").val();
    var flag=true;
    if(password == ""){
        $.pt({
            target: $("#login-password"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"密码不能为空",
            onClose:'5s'
        });
        flag=false;
    }
    return flag;
}
$("#login-username").blur(
   function () {
       CheckUsename();
   }
);
$("#login-password").blur(
    function () {
        Checkpassword();
    }
);
$("#loginform").submit(
        function () {
           return  CheckUsename()&&Checkpassword();
}
);


//注册

//注册用户名验证
function registerusername() {
    var username = $("#register-username").val();
    var regExp = new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5]{1,8}$");
    var flag = true ;
    if(!regExp.test(username)){
        $.pt({
            target: $("#register-username"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"用户名必须为8位以下的字母或数字或汉字"
        });
        flag=false;
    }
    //判断用户名密码是否为空
    if(username == ""){
        $.pt({
            target: $("#register-username"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"用户名不能为空"
        });
        flag=false;
    }
    return flag;
};
//注册密码验证
function registerpassword() {
   var password = $("#register-password").val();
    var regExp = new RegExp("^[a-zA-Z0-9]{1,10}$");
    var flag = true ;
    if(!regExp.test(password)){
        $.pt({
            target: $("#register-password"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"密码必须为10位以下的字母或数字"
        });
        flag=false;
    }
    //判断用户名密码是否为空
    if(password == ""){
        $.pt({
            target: $("#register-password"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"密码不能为空"
        });
        flag=false;
    }
    return flag;
};
//密码确认
function registerrepassword() {
    var repassword = $("#register-repassword").val();
    var password = $("#register-password").val();
    var regExp = new RegExp("^[a-zA-Z0-9]{1,10}$");
    var flag = true ;

    //判断密码是否为空
    if(repassword == ""){
        $.pt({
            target: $("#register-repassword"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"密码不能为空"
        });
        flag=false;
}else if(!regExp.test(repassword)){
        $.pt({
            target: $("#register-repassword"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"密码必须为10位以下的字母或数字"
        });
        flag=false;
    }else if(password != repassword){
        $.pt({
            target: $("#register-repassword"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"两次输入的密码不一致"
        });
        flag = false;
    }
    return flag;
};

//注册问题验证
function registerpsq() {
    var psq = $("#psq").val();
    var regExp = new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5]{1,20}$");
    var flag = true ;
    if(!regExp.test(psq)){
        $.pt({
            target: $("#psq"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"密保问题必须为20位以下的字母或数字或汉字"
        });
        flag=false;
    }
    //判断用户名密码是否为空
    if(psq == ""){
        $.pt({
            target: $("#psq"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"密保问题不能为空"
        });
        flag=false;
    }
    return flag;
};

//注册答案验证
function registeranswer() {
    var psq = $("#answer").val();
    var regExp = new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5]{1,20}$");
    var flag = true ;
    if(!regExp.test(psq)){
        $.pt({
            target: $("#answer"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"密保答案必须为20位以下的字母或数字或汉字"
        });
        flag=false;
    }
    if(psq == ""){
        $.pt({
            target: $("#answer"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"密保答案不能为空"
        });
        flag=false;
    }
    return flag;
};

function registerbirthday(){
    var password = $("#brithday").val();
    var flag = true ;
    if(password == ""){
        $.pt({
            target: $("#brithday"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"日期不能为空"
        });
        flag=false;
    }
    return flag;
};

$("#register-username").blur(
    function () {
        registerusername();
    }
);
$("#register-password").blur(
    function () {
        registerpassword();
    }
);
$("#register-repassword").blur(
    function () {
    registerrepassword();
});
$("#psq").blur(
    function () {
        registerpsq();
    }
);
$("#answer").blur(
    function () {
        registeranswer();
    }
);

$("#brithday").blur(
    function () {
        registerbirthday();
});

$("#checkcode").blur(
    function () {
        checkcodef();
    });
$("#registerform").submit(function () {
    return registerusername()&&registerpassword()&&registerrepassword()&&registerpsq()&&registeranswer()&&registerbirthday()&&checkcodef();
});
$("#updateform").submit(function () {
    return registerusername()&&registerpassword()&&registerrepassword()&&registerpsq()&&registeranswer()&&registerbirthday();
});
function forgetusename () {
    var name = $("#forget-username").val();
    var flag=true;
    if(name == ""){
        $.pt({
            target: $("#forget-username"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"密码不能为空",
            onClose:'5s'
        });
        flag=false;
    }
    return flag;
};
function forgetanswer () {
    var name = $("#forget-answer").val();
    var flag=true;
    if(name == ""){
        $.pt({
            target: $("#forget-answer"),
            position: 'r',
            align: 't',
            width: 'auto',
            height: 'auto',
            content:"密保答案不能为空",
            onClose:'5s'
        });
        flag=false;
    }
    return flag;
};

$("#forget-username").blur(function () {
    forgetusename();
});
$("#forget-answer").blur(function () {
    forgetanswer();
});
$("#lostform").submit(function () {
    return forgetanswer();
});


