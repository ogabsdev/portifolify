<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page title="Projetos">
    <jsp:body>
        <div class="p-5 w-100">
            <div class="d-flex align-items-center justify-content-between">
                <h1>Projetos</h1>
                <a href="/projects/create" class="btn btn-primary">
                  + Novo projeto
                </a>
            </div>
        </div>
    </jsp:body>
</t:page>