<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<t:page title="Projetos">
    <jsp:body>
        <div class="p-5 w-100">
            <div class="d-flex align-items-center justify-content-between">
                <h1>Projetos</h1>
                <a href="/projects/create" class="btn btn-primary">
                  + Novo projeto
                </a>
            </div>

             <div class="d-flex align-items-center justify-content-start">
                <form method="GET" action="/projects" class="d-flex flex-column mt-5 w-25">
                    <div class="form-group mb-2">
                        <label
                            class="mb-2"
                        >
                            Pesquisar
                        </label>
                        <div class="d-flex gap-1">
                            <input
                                type="text"
                                class="form-control"
                                id="search"
                                name="q"
                                value="${param.q}"
                                aria-describedby="q"
                                placeholder="Por nome, gerente, status, risco..."
                            />
                            <button
                                type="submit"
                                class="btn btn-primary"
                            >
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                </svg>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="d-flex flex-column w-100 mt-5">
                <div class="table-responsive shadow mb-5 bg-white rounded">
                  <table class="table">
                    <thead class="table-light">
                        <tr>
                            <th scope="col">Nome</th>
                            <th scope="col">Gerente</th>
                            <th scope="col">Data Início</th>
                            <th scope="col">Data Fim Prevista</th>
                            <th scope="col">Status</th>
                            <th scope="col">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${projects}" var="project" >
                            <tr>
                                <td>${project.name}</td>
                                <td>${project.managerName}</td>
                                <td>
                                    ${project.startDate}
                                </td>
                                <td>${project.expectedEndDate}</td>
                                <td>${project.projectStatus}</td>
                                <td>
                                    <a href="/projects/${project.id}/edit">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                          <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                          <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
                                        </svg>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                  </table>
                </div>
            </div>
        </div>
    </jsp:body>
</t:page>