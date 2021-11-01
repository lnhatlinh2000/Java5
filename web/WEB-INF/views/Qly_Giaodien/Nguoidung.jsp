<%-- 
    Document   : Nguoidung
    Created on : Feb 21, 2020, 7:05:07 PM
    Author     : ASUS
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="fLinh" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cLinh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/style/style_phongban.css" rel="stylesheet" type="text/css"/>
        <base href="${pageContext.servletContext.contextPath}/">
        <title>nguoidung.htm</title>
    </head>
    <body>
        <div class="container">
            <header style="background-image: url('https://cdn-s3.sappi.com/s3fs-public/sustainability/hub/Sustainability-group-banner-image.jpg');">
                <div class="top-right">
                    <a href="login.htm">Đăng xuất</a> | 
                    <a style="color: blue;
                       font-weight: bold;"
                       href="user.htm">user.${user}</a>
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
                                <li><a href="thanhtichCN.htm">Thành tích cá nhân</a></li>
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
                <div class="phongban">
                    <h1>Người dùng</h1>

                    <fLinh:form id="tab" action="function/nguoidung.htm" modelAttribute="User">
                        <div>
                            <label>Username: </label>
                            <fLinh:input path="username" readonly="${hide}" size="30"/>
                        </div>
                        <br/>
                        <div><label>Password:</label> 
                            <fLinh:password path="password" size="30"/>
                        </div>
                        <br/>
                        <div><label>Fullname:</label> 
                            <fLinh:input path="fullname" size="30"/>
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
                            <td>Tài khoản</td>
                            <td>Mật khẩu</td>
                            <td>Họ tên</td>
                            <td>Action</td>
                        </tr>
                        <cLinh:forEach var="u" items="${users}">
                            <tr>
                                <td>${u.username}</td>
                                <td>************</td>
                                <td>${u.fullname}</td>
                                <td>
                                    <a href="nguoidung/edit/${u.username}.htm">Edit</a> ||
                                    <a href="nguoidung/delete/${u.username}.htm">Delete</a>
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
