# course-api-frontend

Frontend com Thymeleaf da API de cursos feito para o desafio 02 da Rocketseat.

[Link do desafio.](https://efficient-sloth-d85.notion.site/Desafio-02-Thymeleaf-77f87f5b46d24c77927a9dc0985759e4)

[Link do repositório com o backend criado no desafio 01.](https://github.com/gabrielburich/course-api)

### Fluxo de edição do status Ativo/Inativo

Não é possível criar um campo no formulário para este campo, pois ele é editado por um endpoint patch com uma função toggle.
Por isso a edição dele foi implementada assim:

1. Acesse a página de listagem de cursos.
2. Clique no valor da coluna Ativo, o valor será alterado.
