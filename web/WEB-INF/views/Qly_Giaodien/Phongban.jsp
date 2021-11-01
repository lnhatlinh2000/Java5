 <%-- 
    Document   : Phongban
    Created on : Jan 15, 2020, 3:36:48 PM
    Author     : ASUS
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="sLinh" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fLinh" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="cLinh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/style/style_phongban.css" rel="stylesheet" type="text/css"/>
        <base href="${pageContext.servletContext.contextPath}/">
        <%--<tilte><sLinh:message code="global.title"/></tilte>--%>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function(){
               $("a[data-lang]").click(function(){
               var lang = $(this).attr("data-lang");
               $.get("?lang="+lang, function (){
                   location.reload();
               });
        });
    });
            
        </script>
        <!--        <script>
                    window.addEventListener("submit", function () {
                        var Id = document.getElementsByName("Id")[0].value;
                        var Name = document.getElementsByName("Name")[0].value;
                        var rog = "";
                        if (Id == rog) {
                            alert("'Mã phòng ban' còn để trống !!!");
                            event.preventDefault();
                        } else if (Name == rog) {
                            alert("'Tên phòng ban' còn để trống !!!");
                            event.preventDefault();
                        }
        
                    });
                </script>-->
        <title>phongban.htm</title>
    </head>
    <body>
        <div class="container">
            <header style="background-image: url('https://cdn-s3.sappi.com/s3fs-public/sustainability/hub/Sustainability-group-banner-image.jpg');">
                <div class="top-right">
                    <a href="login.htm">Đăng xuất</a> | 
                    <a href="user.htm">user.${user}</a>
                </div>
                <div class="company">
                    <h1>°○• abcGroup <sup>☆</sup></h1>
                </div>
            </header>
            <nav>
                <div class="nav-left">
                    <ul>
                        <li><a href="mainframe.htm"><sLinh:message code="global.menu.home"/></a> |</li>
                        <li><a href="nhanvien.htm">Nhân viên</a> |</li>
                        <li><a style="color: blue;
                               font-weight: bold;" href="phongban.htm"><sLinh:message code="global.menu.depart"/></a> |</li>
                        <li><a href="records.htm">Thành tích & kỷ luật</a> |</li>
                        <li><a href="#">Thống kê</a> |
                            <ul class="sub-menu" >
                                <li><a href="thanhtichCN.htm">Thành tích cá nhân</a></li>
                                <li><a href="thanhtichPB.htm">Thành tích phòng ban</a></li>
                            </ul>
                        </li>
                        <li><a href="#">Giới thiệu</a></li>  
                        <li style="float: right"><a href="phongban.htm" data-lang="vi">Tiếng việt</a></li>
                        <li style="float: right"><a href="phongban.htm" data-lang="en">English</a> |</li>
                    </ul>   
                </div>
            </nav>
            <article>
                <div class="phongban">
                    <h1>Phòng Ban</h1>
                    
                    <fLinh:form id="tab" action="function/departs.htm" modelAttribute="Depart">
                        <div>
                            <label>Mã phòng ban:</label>
                            <fLinh:input path="id" readonly="${hide}" size="30"/>
                        </div>
                        <br/>
                        <div><label>Tên phòng ban:</label> 
                            <fLinh:input path="name" size="30"/>
                        </div>
                        <br/>
                        <div>
                            <input type="submit" name="action" value="${execute}" />
                            <input type="reset" name="Reset" value="Mới">
                        </div>
                    </fLinh:form>
                    <br/>
                    <hr/>
                    <div style="float: left">
                        ${message}
                    </div>
                    <table>
                        <tr style="background-color: #ccffcc">
                            <td>MãPB</td>
                            <td>TênPB</td>
                            <td>Action</td>
                        </tr>
                        <cLinh:forEach var="pb" items="${departs}">
                            <tr>
                                <td>${pb.id}</td>
                                <td>${pb.name}</td>
                                <td>
                                    <a href="phongban/edit/${pb.id}.htm">Edit</a> ||
                                    <a href="phongban/delete/${pb.id}.htm">Delete</a>
                                </td>
                            </tr>
                        </cLinh:forEach>
                    </table>
                </div>
            </article>
            <!--        <aside>
                        </aside>-->
            <footer>FPT Polytechnic ♥</footer>
        </div>

    </body>
</html>
