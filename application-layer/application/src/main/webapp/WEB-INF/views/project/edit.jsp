<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<t:page title="Editar ${project.name}">
    <jsp:body>
        <div class="p-5 w-100">
            <div class="d-flex flex-column">
                <div class="mb-3">
                    <a href="/projects">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left-short" viewBox="0 0 16 16">
                          <path fill-rule="evenodd" d="M12 8a.5.5 0 0 1-.5.5H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5a.5.5 0 0 1 .5.5"/>
                        </svg>
                        <small> ver todos os projetos </small>
                    </a>
                </div>
                <div>
                    <h2>Editar: ${project.name}</h2>
                </div>
                <hr />
            </div>
            <c:if test="${param.error == 'not-can-deleted'}">
                <div class="d-flex mt-3 w-100 alert alert-danger fade show" role="alert">
                    O projeto está em uma das situações que não pode ser removido: iniciado, em andamento ou encerrado
                </div>
            </c:if>
             <c:if test="${param.feedback == 'updated'}">
                <div class="d-flex mt-3 w-100 alert alert-success alert-dismissible fade show" role="alert">
                    Projeto atualizado com sucesso
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close">
                     </button>
                </div>
            </c:if>
            <f:form
                class="d-flex flex-column mt-3 w-100"
                method="POST"
                action="/projects/${project.id}"
                modelAttribute="project"
                id="update-project"
            >
               <input type="hidden" name="_method" value="PUT" />

                <div class="d-flex">
                    <div class="col-3">
                        <h4>Detalhes</h4>
                        <p>Informe dados básicos do projeto</p>
                    </div>
                    <div class="col-9">
                        <div class="shadow p-3 mb-5 bg-white rounded">
                            <div class="form-group mb-2">
                                <f:label
                                    class="mb-2"
                                    path="name"
                                >
                                    Nome <span class="text-danger">*<span>
                                </f:label>
                                <f:input
                                    type="text"
                                    class="form-control ${requestScope['org.springframework.validation.BindingResult.project'].hasFieldErrors('name')? 'is-invalid' : ''}"
                                    id="name"
                                    path="name"
                                    aria-describedby="name"
                                    placeholder=""
                                />
                                <f:errors path="name" class="invalid-feedback" />
                            </div>
                            <div class="form-group mb-2">
                                <f:label
                                    class="mb-2"
                                    for="start-date"
                                    path="startDate"
                                >
                                    Data de início <span class="text-danger">*<span>
                                </f:label>
                                <f:input
                                    type="date"
                                    class="form-control ${requestScope['org.springframework.validation.BindingResult.project'].hasFieldErrors('startDate')? 'is-invalid' : ''}"
                                    id="start-date"
                                    path="startDate"
                                    aria-describedby="start-date"
                                    placeholder=""
                                />
                                <f:errors path="startDate" class="invalid-feedback" />
                            </div>
                            <div class="form-group mb-2">
                              <f:label
                                class="mb-2"
                                for="expected-end-date"
                                path="expectedEndDate"
                              >
                                Data prevista para fim <span class="text-danger">*<span>
                              </f:label>
                              <f:input
                                type="date"
                                class="form-control ${requestScope['org.springframework.validation.BindingResult.project'].hasFieldErrors('expectedEndDate')? 'is-invalid' : ''}"
                                id="expected-end-date"
                                aria-describedby="name"
                                placeholder=""
                                path="expectedEndDate"
                              />
                              <f:errors path="expectedEndDate" class="invalid-feedback" />
                            </div>
                            <div class="form-group">
                                <f:label
                                    class="mb-2"
                                    for="description"
                                    path="description"
                                >
                                   Descrição
                                </f:label>
                                <f:textarea
                                    class="form-control ${requestScope['org.springframework.validation.BindingResult.project'].hasFieldErrors('description')? 'is-invalid' : ''}"
                                    id="description"
                                    rows="5"
                                    path="description"
                                ></f:textarea>
                                <f:errors path="description" class="invalid-feedback" />
                            </div>
                        </div>
                    </div>
                </div>

                <div class="d-flex">
                    <div class="col-3">
                        <h4>Status do projeto</h4>
                        <p>Selecione em qual fase este projeto está</p>
                    </div>
                    <div class="col-9">
                        <div class="shadow p-3 mb-5 bg-white rounded">
                            <div class="form-group mb-2">
                                <f:label
                                    class="mb-2"
                                    for="status"
                                    path="projectStatusId"
                                >
                                    Status <span class="text-danger">*<span>
                                </f:label>
                                <f:select
                                    id="status"
                                    class="form-select ${requestScope['org.springframework.validation.BindingResult.project'].hasFieldErrors('projectStatusId')? 'is-invalid' : ''}"
                                    aria-label="Status do projeto"
                                    path="projectStatusId"
                                >
                                  <f:option value="">Selecione uma opção</f:option>
                                  <f:options
                                      items="${projectStatuses}"
                                      itemValue="description"
                                      itemLabel="description"
                                   />
                                </f:select>
                                <f:errors path="projectStatusId" class="invalid-feedback" />
                            </div>
                        </div>
                    </div>
                </div>

                <div class="d-flex">
                    <div class="col-3">
                        <h4>Financeiro</h4>
                        <p>Informe o orçamento e nível de risco para <br> o negócio.</p>
                    </div>
                    <div class="col-9">
                        <div class="shadow p-3 mb-5 bg-white rounded">
                            <div class="form-group mb-2">
                                <f:label
                                    class="mb-2"
                                    for="budget"
                                    path="budget"
                                >
                                    Orçamento <span class="text-danger">*<span>
                                </f:label>
                                <f:input
                                    type="number"
                                    class="form-control ${requestScope['org.springframework.validation.BindingResult.project'].hasFieldErrors('budget')? 'is-invalid' : ''}"
                                    id="bugdet"
                                    aria-describedby="budget"
                                    placeholder=""
                                    path="budget"
                                />
                                <f:errors path="budget" class="invalid-feedback" />
                            </div>
                            <div class="form-group mb-2">
                                <f:label
                                    class="mb-2"
                                    for="projectRisks"
                                    path="projectRiskId"
                                >
                                    Risco mapeado <span class="text-danger">*<span>
                                </f:label>
                                <f:select
                                    id="projectRisks"
                                    class="form-select ${requestScope['org.springframework.validation.BindingResult.project'].hasFieldErrors('projectRiskId')? 'is-invalid' : ''}"
                                    aria-label="Riscos do projeto"
                                    path="projectRiskId"
                                >
                                  <f:option value="">Selecione uma opção</f:option>
                                  <f:options
                                    items="${projectRisks}"
                                    itemValue="description"
                                    itemLabel="description"
                                  />
                                </f:select>
                                <f:errors path="projectRiskId" class="invalid-feedback" />
                            </div>
                        </div>
                    </div>
                </div>

                <div class="d-flex">
                    <div class="col-3">
                        <h4>Gestor</h4>
                        <p>Informe o gerente responsável pela <br> condução do projeto.</p>
                    </div>
                    <div class="col-9">
                        <div class="shadow p-3 mb-5 bg-white rounded">
                             <div class="form-group mb-2">
                                <f:label
                                    class="mb-2"
                                    for="manager"
                                    path="managerId"
                                >
                                    Gerente responsável <span class="text-danger">*<span>
                                </f:label>
                                <f:select
                                    id="manager"
                                    class="form-select ${requestScope['org.springframework.validation.BindingResult.project'].hasFieldErrors('managerId')? 'is-invalid' : ''}"
                                    aria-label="Gerentes"
                                    path="managerId"
                                >
                                  <f:option value="">Selecione uma opção</f:option>
                                  <f:options
                                      items="${managers}"
                                      itemValue="id"
                                      itemLabel="name"
                                  />
                                </f:select>
                                <f:errors path="managerId" class="invalid-feedback" />
                            </div>
                        </div>
                    </div>
                </div>

                <hr />

                <div class="d-flex align-items-center justify-content-start">
                     <button
                        type="button"
                        class="btn btn-danger"
                        data-bs-toggle="modal"
                        data-bs-target="#delete-project-${project.id}"
                    >
                        Remover
                    </button>
                </div>

                <div class="d-flex align-items-center justify-content-end gap-3">
                    <a
                        href="/projects"
                        class="btn btn-link"
                    >
                        Cancelar
                    </a>
                    <button
                        type="submit"
                        class="btn btn-primary"
                        id="submit-update-project"
                    >
                        Salvar
                    </button>
                </div>
            </f:form>

            <div class="modal fade" tabindex="-1" role="dialog" id="delete-project-${project.id}">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title">Confirmar remoção do projeto: ${project.name}</h5>
                  </div>
                  <div class="modal-body">
                  <p><strong>Atenção:</strong> esta ação não pode ser desfeita. Lembre-se que projetos com status iniciado, em andamento ou encerrado não podem ser removidos.</p>
                   <f:form id="delete-project" method="POST" action="/projects/${project.id}">
                        <input type="hidden" name="_method" value="DELETE" />
                    </f:form>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        Cancelar
                    </button>
                    <button type="submit" class="btn btn-danger" id="submit-delete-project">
                        Remover
                    </button>
                  </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:page>