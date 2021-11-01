<%-- 
    Document   : TT_KL
    Created on : Jan 15, 2020, 4:38:31 PM
    Author     : ASUS
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cLinh" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fLinh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/style/style_TT_KL.css" rel="stylesheet" type="text/css"/>
        <base href="${pageContext.servletContext.contextPath}/">
        <title>records.htm</title>
    </head>
    <body>
        <div class="container">
            <header style="background-image: url('https://cdn-s3.sappi.com/s3fs-public/sustainability/hub/Sustainability-group-banner-image.jpg');">
                <div class="top-right">
                    <a href="login.htm">Đăng xuất</a> | 
                    <a href="user.${user}">user.${user}</a>
                </div>
                <div class="company">
                    <h1>°○• abcGroup <sup>☆</sup></h1>
                </div>
            </header>
            <nav>
                <div class="nav-left">
                    <ul>
                        <li><a  href="mainframe.htm">Trang chủ</a> |</li>
                        <li><a href="nhanvien.htm">Nhân viên</a> |</li>
                        <li><a href="phongban.htm">Phòng ban</a> |</li>
                        <li><a style="color: blue;
                               font-weight: bold;" href="records.htm">Thành tích & kỷ luật</a> |</li>
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
<!--                <script>
                    $item = 4 ;
                    $curent = 1;
                    $offset = ($curent-1) * $item;
                    $products = "select * from Records";
                    
                </script>-->
                <div class="records">
                    <h1>Thành tích & Kỷ luật</h1>

                    <fLinh:form id="tab" action="function/record.htm" modelAttribute="Record">
                        <div>
                            <label>Nhân viên: </label>
                            <fLinh:hidden path="id"/>
                            <fLinh:select path="staff.id" items="${staffs}"
                                          itemValue="id" itemLabel="name"/>
                        </div>
                        <div>
                            <label>Loại: </label>
                            <fLinh:radiobutton path="type" value="1" label="Thành tích" checked="1"/>
                            <fLinh:radiobutton path="type" value="0" label="Kỷ luật"/>
                        </div>
                        <label>Lý do: </label>
                        <div>

                            <fLinh:textarea path="reason" rows="3"/>
                        </div>
                        <br/>
                        <div>
                            <input type="submit" name="action" value="${execute}" />
                            <input type="reset" name="Reset" value="Mới">
                        </div>
                    </fLinh:form>
                    <br/>
                    <hr/>
                    <br/>
                    <div align="center">
                        <%--<fLinh:form action="record/search.htm" modelAttribute="Record">
                            <button>Tìm kếm</button>
                            <fLinh:input path="search" placeholder="Tìm theo ID"/>
                        </fLinh:form>--%>
                        <form action="record/search.htm">
                            <button>Tìm kếm</button>
                            <input type="text" name="search" value="" placeholder="Tìm kiếm theo ID" size="50">
                        </form>
                    </div>
                    <br/>
                    <hr/>
                    <div style="float: left">
                        ${message}
                    </div>
                    <div class="bang">
                        <table>
                            <tr style="background-color: #ccffcc">
                                <td>ID</td>
                                <td>Loại</td>
                                <td>Lý do</td>
                                <td>Ngày ghi nhận</td>
                                <td>Nhân viên</td>
                                <td>Action</td>
                            </tr>
                            <cLinh:forEach var="rc" items="${records}">
                                <tr>
                                    <td>${rc.id}</td>
                                    <td>${rc.type==1?"Thành tích":"Kỷ luật"}</td>
                                    <td>${rc.reason}</td>
                                    <td>${rc.date}</td>
                                    <td>${rc.staff.name}</td>
                                    <td>
                                        <a href="record/edit/${rc.id}.htm">Edit</a> ||
                                        <a href="record/delete/${rc.id}.htm">Delete</a>
                                    </td>
                                </tr>
                            </cLinh:forEach>
                        </table>
                        <cLinh:set var="a" value="1"/>
                        <a href="record/next.htm?page=${a+1}">>></a>
                        <a href="record/back.htm?page=${a-1}"><<</a>
                        
                        
                    </div>
                    <div>
                        
                    </div>
                </div>
                    
            </article>
            <!--        <aside>
                        </aside>-->
            <footer>FPT Polytechnic ♥</footer>
        </div>

    </body>
</html>