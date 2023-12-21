<%@tag description="Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="title" %>

<!doctype html>
<html lang="pt_BR" data-bs-theme="auto">
  <head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="/public/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/public/assets/css/app.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@300;400;500;700&display=swap" rel="stylesheet">
    <title>Portifolify - ${title}</title>
  </head>
  <body>
      <main>
          <t:header />
          <div class="d-flex flex-nowrap">
                <t:navbar />
                <jsp:doBody/>
          </div>
      </main>
    <script src="/public/assets/js/jquery.min.js"></script>
    <script src="/public/assets/js/bootstrap.bundle.min.js"></script>
    <script defer src="/public/assets/js/app.js"></script>
  </body>
</html>