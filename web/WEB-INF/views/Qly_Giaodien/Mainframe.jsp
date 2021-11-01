<%-- 
    Document   : Mainframe
    Created on : Jan 15, 2020, 9:48:39 PM
    Author     : ASUS
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fLinh" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cLinh" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="sLinh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/style/style_Mainframe.css" rel="stylesheet" type="text/css"/>
        <base href="${pageContext.servletContext.contextPath}/">
        <%--<tilte><sLinh:message code="global.title"/></tilte>--%>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("a[data-lang]").click(function () {
                    var lang = $(this).attr("data-lang");
                    $.get("mainframe.htm?lang=" + lang, function () {
                        location.reload();
                    });
                });
            });

        </script>
    </head>
    <body>
        <div class="container">
            <header style="background-image: url('https://cdn-s3.sappi.com/s3fs-public/sustainability/hub/Sustainability-group-banner-image.jpg');">
                <div class="top-right">
                    <a href="login.htm"><sLinh:message code="global.header.logout"/></a> | 
                    <a href="user.htm">user.${user}</a>

                </div>
                <div class="company">
                    <h1>°○• abcGroup <sup>☆</sup></h1>
                </div>
            </header>
            <nav>
                <div class="nav-left">
                    <ul>
                        <li><a style="color: blue;
                               font-weight: bold;" href="mainframe.htm"><sLinh:message code="global.menu.home"/></a> |</li>
                        <li><a href="nhanvien.htm"><sLinh:message code="global.menu.staff"/></a> |</li>
                        <li><a href="phongban.htm"><sLinh:message code="global.menu.depart"/></a> |</li>
                        <li><a href="records.htm"><sLinh:message code="global.menu.record"/></a> |</li>
                        <li><a href="#"><sLinh:message code="global.menu.statistical"/></a> |
                            <ul class="sub-menu" >
                                <li><a href="thanhtichCN.htm"><sLinh:message code="global.menu.statistical_P"/></a></li>
                                <li><a href="thanhtichPB.htm"><sLinh:message code="global.menu.statistical_D"/></a></li>
                            </ul>
                        </li>
                        <li><a href="#"><sLinh:message code="global.menu.about"/></a></li>  
                        <li style="float: right"><a href="mainframe.htm" data-lang="vi">Tiếng việt</a></li>
                        <li style="float: right"><a href="mainframe.htm" data-lang="en">English</a> |</li>
                    </ul>   
                </div>
            </nav>
            <article>
                <div class="art-tab">
                    <h1>♦♦♦<sLinh:message code="global.article.title"/>♦♦♦</h1>
                    <br/>
                    <table>
                        <tr>
                            <cLinh:forEach var="topD" items="${top5D}">
                                <td>
                                    <img src="${pageContext.request.contextPath}/image/${topD[4]}" width="140" height="160">
                                    ${topD[0]}_
                                    ${topD[1]}
                                    <br/>
                                    <sLinh:message code="global.article.points"/>: <strong>${topD[3]}</strong>
                                    <br/>
                                    ${topD[2]}
                                </td>
                            </cLinh:forEach>
                            
                        </tr>
                        <tr>
                            <cLinh:forEach var="topC" items="${top5C}">
                                <td>
                                    <img src="${pageContext.request.contextPath}/image/${topC[4]}" width="140" height="160">
                                    ${topC[0]}_
                                    ${topC[1]}
                                    <br/>
                                    <sLinh:message code="global.article.points"/>: <strong>${topC[3]}</strong>
                                    <br/>
                                    ${topC[2]}
                                </td>
                            </cLinh:forEach>
                        </tr>
                    </table>
                </div>
            </article>
            <!--        <aside>
                        </aside>-->
            <footer>FPT Polytechnic ♥</footer>
        </div>
    </body>
</html>
