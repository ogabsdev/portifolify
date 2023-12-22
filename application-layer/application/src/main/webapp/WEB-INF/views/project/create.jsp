<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<t:page title="Novo Projeto">
    <jsp:body>
        <div class="p-5 w-100">
            <div class="d-flex flex-column">
                <div class="mb-3">
                    <a href="/projects"><small><- ver todos os projetos </small></a>
                </div>
                <div>
                    <h2>Novo Projeto</h2>
                </div>
                <hr />
            </div>
            <f:form
                class="d-flex flex-column mt-3 w-100"
                method="POST"
                action="/projects"
                modelAttribute="project"
            >
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
                                      itemValue="id"
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
                                    itemValue="id"
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
                    >
                        Salvar
                    </button>
                </div>
            </f:form>
        </div>
    </jsp:body>
</t:page>