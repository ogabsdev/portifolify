<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

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
            <form class="d-flex flex-column mt-3 w-100">
            <!--
                 id bigserial NOT NULL,
                  nome VARCHAR(200) NOT NULL,
                  data_inicio DATE ,
                  data_previsao_fim DATE ,
                  data_fim DATE ,
                  descricao VARCHAR(5000) ,
                  status VARCHAR(45) ,
                  orcamento FLOAT ,
                  risco VARCHAR(45) ,
                  idgerente bigint NOT NULL,
                -->
                <div class="d-flex">
                    <div class="col-3">
                        <h4>Detalhes</h4>
                        <p>Informe dados básicos do projeto</p>
                    </div>
                    <div class="col-9">
                        <div class="shadow p-3 mb-5 bg-white rounded">
                            <div class="form-group mb-2">
                                <label
                                    class="mb-2"
                                    for="name"
                                >
                                    Nome <span class="text-danger">*<span>
                                </label>
                                <input
                                    type="text"
                                    class="form-control"
                                    id="name"
                                    aria-describedby="name"
                                    placeholder=""
                                >
                            </div>
                            <div class="form-group mb-2">
                                <label
                                    class="mb-2"
                                    for="start-date"
                                >
                                    Data de início <span class="text-danger">*<span>
                                </label>
                                <input
                                    type="date"
                                    class="form-control"
                                    id="start-date"
                                    aria-describedby="start-date"
                                    placeholder=""
                                >
                            </div>
                            <div class="form-group mb-2">
                              <label
                                class="mb-2"
                                for="expected-end-date"
                              >
                                Data prevista para fim <span class="text-danger">*<span>
                              </label>
                              <input
                                type="date"
                                class="form-control"
                                id="expected-end-date"
                                aria-describedby="name"
                                placeholder=""
                              >
                            </div>
                            <div class="form-group">
                                <label
                                    class="mb-2"
                                    for="exampleFormControlTextarea1"
                                >
                                   Descrição
                                </label>
                                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
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
                                <label
                                    class="mb-2"
                                    for="name"
                                >
                                    Nome <span class="text-danger">*<span>
                                </label>
                                <input
                                    type="text"
                                    class="form-control"
                                    id="name"
                                    aria-describedby="name"
                                    placeholder=""
                                >
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
                                <label
                                    class="mb-2"
                                    for="name"
                                >
                                    Nome <span class="text-danger">*<span>
                                </label>
                                <input
                                    type="text"
                                    class="form-control"
                                    id="name"
                                    aria-describedby="name"
                                    placeholder=""
                                >
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
                                <label
                                    class="mb-2"
                                    for="name"
                                >
                                    Nome <span class="text-danger">*<span>
                                </label>
                                <input
                                    type="text"
                                    class="form-control"
                                    id="name"
                                    aria-describedby="name"
                                    placeholder=""
                                >
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
            </form>
        </div>
    </jsp:body>
</t:page>