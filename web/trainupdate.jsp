<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>请选择车票</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet" />
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script src="js/snow.js"></script>
    <script src="js/jquery.pure.tooltips.js"></script>
    <script src="js/spop.min.js"></script>

    <style type="text/css">
        html{width: 100%; height: 100%;}

        body{
            border-radius: 5px;

            background-repeat: no-repeat;


            background-size: 100% 100%;

        }
        input[readonly]{
            background-color: #cccccc;
            cursor: not-allowed;
        }
        .disablechange input{
            max-width: 100px;
        }
        .disapear{
            display: none;
        }

    </style>

</head>
<body>
<div class="container">
    <h3 style="text-align: center">车次信息</h3>

    <div style="float: left;">

        <form class="form-inline" action="${pageContext.request.contextPath}/findtrainServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName1">车次：</label>
                <select id="exampleInputName1" name="trainid">
                    <option value="0">
                        --请选择车次--
                    </option>
                    <c:forEach items="${trainids}" var="train" >
                        <option value="${train}" <c:if test="${trainid==train}">selected</c:if>>
                                ${train}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>

    <form id="form" action="${pageContext.request.contextPath}/trainchangeServlet" method="post">
        <table border="1" class="table table-bordered table-hover" id="traintable">
            <tr class="disablechange">
                <td>信息</td>
                <c:forEach items="${trainShows}" var="trainShow" varStatus="s">
            <c:if test="${trainShow.timenum==1}">
            <td>始发地：<span style="color: red">${trainShow.start}</span></td>
            </c:if>
            <c:if test="${trainShow.timenum==size}">
                <td>终点站地：<span style="color: red">${trainShow.end}</span></td>
            </c:if>
            </c:forEach>
                <td>车次:<input type="text" value="${trainShows[0].id}" name="id" readonly></td>
            </tr>
            <tr class="success">
                <th>开始站点</th>
                <th>结束站点</th>
                <th>开始时间</th>
                <th>到达时间</th>
                <th>当前节点序号</th>
                <th>当前顺序座位</th>
                <th>退票及跳过座位号</th>
                <th>价格</th>
                <th>数量</th>
                <th class="disapear">操作</th>
            </tr>

            <c:forEach items="${trainShows}" var="trainShow" >
                <tr class="disablechange">
                    <td><input type="text" value="${trainShow.start}" name="start" readonly></td>
                    <td><input type="text" value="${trainShow.end}" name="end" readonly></td>
                    <td><input type="text" value="${trainShow.startt}" name="startt" readonly></td>
                    <td><input type="text" value="${trainShow.endt}" name="endt" readonly></td>
                    <td><input type="text" value="${trainShow.timenum}" name="timenum" readonly></td>
                    <td><input type="text" value="${trainShow.nowt}" name="nowt" readonly></td>
                    <td><input type="text" value="${trainShow.ago}" name="ago" readonly></td>
                    <td><input type="text" value="${trainShow.price}" name="price" readonly></td>
                    <td><input type="text" value="${trainShow.num}" name="num" readonly></td>
                    <td class="disapear">
                        <a class="button border-red" href="javascript:void(0);" onclick="delTr(this);">
                            <span class="icon-trash-o"></span> 删除</a></td>
                    </tr>
            </c:forEach>
</table>
        <div class="form-actions">
        <a class="btn pull-left btn-link text-muted showupdate" onclick="updatejl()">修改</a>
        <input class="btn btn btn-primary disapear" style="float: left" type="button" value="添加" id="btn_add">
        <input class="btn btn btn-primary disapear" style="float: left" type="submit" value="确认修改">
        </div>
    </form>

</div>
<script>
    function updatejl() {
    $(".disablechange td input").attr("readonly",false);
    $(".disapear").css("display","block");
    $(".showupdate").css("display","none");
}
</script>
<script>
    document.getElementById("btn_add").onclick = function() {

        //获取table
        var table = document.getElementById("traintable");
        //追加一行
        table.innerHTML += " <tr class=\"disablechange\">\n" +
            "                    <td><input type=\"text\"  name=\"start\" ></td>\n" +
            "                    <td><input type=\"text\"  name=\"end\" ></td>\n" +
            "                    <td><input type=\"text\"  name=\"startt\" ></td>\n" +
            "                    <td><input type=\"text\"  name=\"endt\" ></td>\n" +
            "                    <td><input type=\"text\"  name=\"timenum\" ></td>\n" +
            "                    <td><input type=\"text\"  name=\"nowt\" ></td>\n" +
            "                    <td><input type=\"text\"  name=\"ago\" ></td>\n" +
            "                    <td><input type=\"text\"  name=\"price\"></td>\n" +
            "                    <td><input type=\"text\"  name=\"num\" ></td>\n" +
            "                    <td ><a class=\"button border-red\" href=\"javascript:void(0);\" onclick=\"delTr(this);\">\n" +
            "                            <span class=\"icon-trash-o\"></span> 删除</a></td>\n" +
            "                    </tr>";
    }


    //删除方法
    function delTr(obj){
        var table = obj.parentNode.parentNode.parentNode;
        var tr = obj.parentNode.parentNode;

        table.removeChild(tr);
    }


</script>
</body>
</html>

