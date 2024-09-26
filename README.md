 

# API RESTful para Gerenciamento de Tarefas com Spring Boot e HATEOAS
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

## Descrição
* Este projeto é uma API RESTful desenvolvida em Spring Boot para o gerenciamento de tarefas. Ele permite criar, atualizar, excluir e visualizar tarefas, com suporte ao HATEOAS para facilitar a navegação entre os recursos.

## Funcionalidades
* Adicionar novas tarefas 
####
* Listar todas as tarefas
####
* Obter detalhes de uma tarefa específica
####
* Atualizar informações de uma tarefa
####
* Excluir tarefas 
####
* Retornar links HATEOAS para navegação entre recursos

## Tecnologias Utilizadas

* Java: Linguagem de programação principal
####
* Spring Boot: Framework para criação da API RESTful
####
* H2 Database: Banco de dados em memória (para desenvolvimento e testes)
####
* PostgreSQL: Banco de dados utilizado em produção
####
* HATEOAS: Hypermedia As The Engine Of Application State
####
* Maven: Gerenciamento de dependências
####
* Hibernate: ORM para gerenciamento das entidades no banco de dados

# Requisitos
* ### **Java** 17
####
* ### **Maven** 3.8+
####
* ### **Postman** ou qualquer plataforma que faça interações CRUD.
# Como executar o projeto

 ### 1. Clone o repositorio
```bash
git clone https://github.com/GomesMaurici0/TaskTrack.git
```
### 2.  Navegue para a pasta do projeto
```bash
cd TaskTrack
```

 ### 3. Compile e execute a aplicação
 ```bash
mvn spring-boot:run
```

### 4. A aplicação estará disponível em: http://localhost:8080

# Documentação 
## A documentação estará disponível via Swagger. Para acessar:
1. Após iniciar a aplicação, abra seu navegador e acesse: 
```bash
    http://localhost:8080/api/swagger-ui/index.html#/
```
2. O Swagger exibirá uma interface gráfica onde você pode testar todas as operações disponíveis na API, como criar, listar, atualizar e deletar tarefas.

###Ou interaja com a API Usando o Postman:
   * Abra o Postman e crie uma nova requisição do tipo POST por exemplo.
   * Insira a URL: http://localhost:8080/api/task.
   * Defina o cabeçalho Content-Type como application/json.
   * No corpo da requisição, ou atualização, selecione a opção "raw" e escolha JSON. Insira o seguinte exemplo de JSON:
# Exemplo de Requisição POST (Adicionar Tarefa)
```json lines
{
 "titulo": "Estudos de Java",
 "descricao": "Aqui está um estudo sobre java.",
 "status": "PENDENTE" // ou "CONCLUIDA" 
}
```

# EndPoints da API
###
* **POST:** `/api/task:` Adiciona uma nova tarefa
####
* **GET:** `/api/task:` Retorna todas as tarefas
####
* **GET:** `/api/task:{id}` Retorna uma tarefa expecífica
####
* **PUT:** `/api/task/{id}` Atualiza uma tarefa existente  
####
* **DELETE** `/api/taskk/{id}` Exclui uma tarefa
# Autor
* ## Mauricio [Linkedin](https://www.linkedin.com/in/mauricio-gomes-479221223/) 

