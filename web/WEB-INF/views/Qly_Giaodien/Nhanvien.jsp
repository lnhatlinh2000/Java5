<%-- 
    Document   : Nhanvien
    Created on : Jan 16, 2020, 3:58:58 PM
    Author     : ASUS
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="sLinh" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fLinh" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="cLinh" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="cLinh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/style/style_Nhanvien.css" rel="stylesheet" type="text/css"/>
        <base href="${pageContext.servletContext.contextPath}/">
        <title>nhanvien.htm</title>
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
                        <li><a style="color: blue;
                               font-weight: bold;" href="nhanvien.htm">Nhân viên</a> |</li>
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
                <div class="records">
                    <h1>Nhân Viên</h1>
                    <fLinh:form action="function/staff.htm" modelAttribute="Staff" enctype="multipart/form-data">
                        <div>
                            <label>ID:</label>
                            <fLinh:input path="id" readonly="${hide}" size="30"/>
                            <label>Họ tên:</label> 
                            <fLinh:input path="name" size="30"/>
                            <label>Giới tính: </label>
                            <fLinh:radiobutton path="gender" value="true" label="Nam" checked="true"/>
                            <fLinh:radiobutton path="gender" value="false" label="Nữ"/>
                        </div>
                        <br/>
                        <div>
                            <label>Ngày sinh:</label> 
                            <fLinh:input path="birthday" size="30"/>
                            <label>Email:</label> 
                            <fLinh:input path="email" size="30"/>
                            <label>Phòng ban:</label> 
                            <fLinh:select path="depart.id" items="${departs}"
                                          itemValue="id" itemLabel="name"/>
                        </div>
                        <br/>
                        <div>
                            <label>Lương:</label> 
                            <fLinh:input path="salary" size="30"/>
                            <label>Điện thoại:</label> 
                            <fLinh:input path="phone" size="30"/>
                            
                            <label>Image:</label> 
                            <input type="file" name="photo">
                            
                            <br/>
                            <br/>
                            <label>Ghi chú</label>
                        </div>
                        <br/>
                        <div style="float: left;">
                            <fLinh:textarea path="notes" rows="4" cols="30"/>
                        </div>
                        <div style="float: right; margin-right: 220px; margin-top: -50px;">
                            <img src="${pageContext.request.contextPath}/image/${photo_name}" width="125" height="140">
                        </div>
                        <br/>
                        <div style="clear: both;">
                            <input type="submit" name="action" value="${execute}" />
                            <input type="reset" name="Reset" value="Mới">
                        </div>
                    </fLinh:form>
                    <br/>
                    <hr/>
                    <br/>
                    <div align="center">
                        <form action="" method="">
                            <button>Tìm kếm</button>
                            <input type="text" name="search" value="" placeholder="Tìm kiếm theo ID" size="50">
                        </form>
                    </div>
                    <br/>
                    <div style="float: left">
                        ${message}
                    </div>
                    <table>
                        <tr style="background-color: #ccffcc">
                            <td>ID</td>
                            <td>Họ tên</td>
                            <td>Giới tính</td>
                            <td>Ngày sinh</td>
                            <td>Lương</td>
                            <td>Email</td>
                            <td>Điện thoại</td>
                            <td>Phòng ban</td>
                            <td>Ghi chú</td>
                            <td>Hình ảnh</td>
                            <td>Action</td>
                        </tr>
                        <cLinh:forEach var="s" items="${staffs}">
                            <tr>
                                <td>${s.id}</td>
                                <td>${s.name}</td>
                                <td>${s.gender?"Nam":"Nữ"}</td>
                                <td>${s.birthday}</td>
                                <td>${s.salary}</td>
                                <td>${s.email}</td>
                                <td>${s.phone}</td>
                                <td>${s.depart.name}</td>
                                <td>${s.notes}</td>
                                <td><img src="${pageContext.request.contextPath}/image/${s.photo}" width="50" height="60"></td>
                                <td>
                                    <a href="staff/edit/${s.id}.htm">Edit</a> ||
                                    <a href="staff/delete/${s.id}.htm">Delete</a>
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
