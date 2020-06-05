function CheckUsename() {
    var username = $("#user").val();
    var regExp = new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5]{1,8}$");
    var flag = true ;
    if(!regExp.test(username)){

        flag=false;
    }
    //判断用户名密码是否为空
    if(username == ""){
        $.pt({
            target: $("#user"),
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
    var password = $("#password").val();
    var flag=true;
    if(password == ""){
        $.pt({
            target: $("#password"),
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

    $("#user").blur(
        function () {

            CheckUsename();
        }
    );
    $("#password").blur(
        function () {
            Checkpassword();
        }
    );
    $("#loginform").submit(
        function () {
            return  CheckUsename()&&Checkpassword();
        }
    );

