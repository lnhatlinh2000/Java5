<%-- 
    Document   : TTCanhan
    Created on : Jan 16, 2020, 8:19:14 PM
    Author     : ASUS
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cLinh" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fLinh" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/style/style_Mainframe.css" rel="stylesheet" type="text/css"/>
        <base href="${pageContext.servletContext.contextPath}/">
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
                        <li><a href="mainframe.htm">Trang chủ</a> |</li>
                        <li><a href="nhanvien.htm">Nhân viên</a> |</li>
                        <li><a href="phongban.htm">Phòng ban</a> |</li>
                        <li><a href="records.htm">Thành tích & kỷ luật</a> |</li>
                        <li><a href="#">Thống kê</a> |
                            <ul class="sub-menu" >
                                <li><a style="color: blue;
                                       font-weight: bold;" href="thanhtichCN.htm">Thành tích cá nhân</a></li>
                                <li><a href="thanhtichPB.htm">Thành tích phòng ban</a></li>
                            </ul>
                        </li>
                        <li><a href="#">Giới thiệu</a></li>  
                        <li style="float: right"><a href="#">Tiếng việt</a></li>
                        <li style="float: right"><a href="#">English</a> |</li>
                    </ul>   
                </div>
            </nav>
            <article>
                <div class="art-tab">
                    <h1>Thành Tích Cá Nhân</h1>
                    <table>
                        <tr style="background-image: url('https://cdn-s3.sappi.com/s3fs-public/sustainability/hub/Sustainability-group-banner-image.jpg');">
                            <th>Nhân viên</th>
                            <th>Tổng thành tích</th>
                            <th>Tổng kỷ luật</th>
                            <th>Điểm thưởng</th>
                        </tr>
                        <cLinh:forEach var="cn" items="${CaNhan}">
                            <tr>
                                <td>${cn[0]}</td>
                                <td>${cn[1]}</td>
                                <td>${cn[2]}</td>
                                <td>${cn[3]}</td>
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
