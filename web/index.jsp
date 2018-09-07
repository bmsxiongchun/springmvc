<%--
  Created by IntelliJ IDEA.
  User: bonree
  Date: 2018/9/7
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Title$</title>
    <script type="text/javascript">
        function createXMLHttpRequest() {
            try {
                return new XMLHttpRequest();
            } catch (e) {
                try {
                    return new ActiveXObject("Msxml2.XMLHTTP");
                } catch (e) {
                    try {
                        return new ActiveXObject("Microsoft.XMLHTTP");
                    } catch (e) {
                        alert("哥们,你用的什么浏览器啊");
                        throw e;
                    }
                }
            }
        }

        window.onload=function () {
            var xmlHttp = createXMLHttpRequest();
            xmlHttp.open("GET", "<c:url value="/ProvinceServlet"/>", true);
            xmlHttp.send(null);
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                    var text = xmlHttp.responseText;
                    var provinceArray = eval("(" + text + ")");
                    for (var i=0; i < provinceArray.length; i++) {
                        var province = provinceArray[i];
                        var opentionEle = document.createElement("option");
                        opentionEle.value = province.pid;

                        var textNode = document.createTextNode(province.pname);
                        opentionEle.appendChild(textNode);

                        document.getElementById("province").appendChild(opentionEle);
                    }
                }
            }

            document.getElementById("province").onchange = function () {
                var xmlHttp = createXMLHttpRequest();
                xmlHttp.open("POST", "<c:url value="/CityServlet"/>", true);
                xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xmlHttp.send("pid=" + this.value);
                xmlHttp.onreadtstatechange = function () {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                        var citySelect = document.getElementById("city");
                        var cityOptionArray = citySelect.getElementsByTagName("option");
                        while(cityOptionArray.length > 1) {
                            citySelect.remove(cityOptionArray[1]);
                        }

                        var text = xmlHttp.responseText;
                        var cityArray = eval("(" + text + ")");

                        for (var i=0; i < cityArray.length; i++) {
                            var city = cityArray[i];
                            var optionEle = document.createElement("option");
                            optionEle.value = city.cid;
                            var textNode = document.createTextNode(city.cname);
                            optionEle.appendChild(textNode);

                            citySelect.appendChild(optionEle);
                        }
                    }
                }
            }
        }
    </script>

</head>
<body>
<h1>省市联动</h1>
<select name="province" id="province">
    <option>==请选择省份==</option>
</select>
<select name="city" id="city">
    <option>==请选择城市==</option>
</select>
</body>
</html>
