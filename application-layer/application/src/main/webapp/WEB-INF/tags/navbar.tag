<%@tag description="Navbar template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 250px;" id="navbar">
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
        <a href="/" class="nav-link text-white ${requestScope['javax.servlet.forward.servlet_path'] == '/' ? 'active': ''}" aria-current="page">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
          Início
        </a>
      </li>
      <li>
        <a href="/projects" class="nav-link text-white  ${requestScope['javax.servlet.forward.servlet_path'] == '/projects' ? 'active': ''}">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#speedometer2"></use></svg>
          Projetos
        </a>
      </li>
      <li>
        <a href="employees" class="nav-link text-white ${requestScope['javax.servlet.forward.servlet_path'] == '/employees' ? 'active': ''}">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#table"></use></svg>
          Funcionários
        </a>
      </li>
    </ul>
</div>