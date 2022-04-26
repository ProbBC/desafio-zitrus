# Desafio Backend - Zitrus Healthtech
O Projeto foi desenvolvido utilizando Maven, Spring Boot, Java 11 e MySQL

## Instalação com docker-compose

1. Clone este repositório
2. Rode na raiz do projeto:

```bash
docker-compose up -d
```

3. O container fará o download das dependencias do projeto para depois inicializar a aplicação, isso pode demorar um pouco.
4. A aplicação estará disponível em: http://localhost:8080/

## Instalação manual

1. É necessário rodar um servidor MySQL para utilizar a base de dados
2. Clone este repositório
3. Altere as configurações do banco de dados no arquivo "application.properties" em src/main/resources
4. Na raiz do projeto, rode o seguinte comando para compilar e baixar as dependências:
```bash
mvn clean package
```
5. Rode a aplicação:
```bash
mvn spring-boot:run
```
6. A aplicação estará disponível em: http://localhost:8080/

## APIs

A aplicação escuta APIs RESTful em 2 contextos: produtos e movimento-estoque.

Foi disponibilzado um arquivo de configuração do Insomnia para facilitar os testes (Insomnia.json).

Segue abaixo exemplos de requisições:
### produtos

- /produtos (POST) - Cria um produto
```json
{
  "descricao": "geladeira",
  "tipoProduto": "eletrodomestico",
  "valorFornecedor": 1800
}
```
- /produtos/{id} (PUT) - Altera um produto
```json
{
  "descricao": "notebook ryzen 5",
  "tipoProduto": "eletronico",
  "valorFornecedor": 3000
}
```
- /produtos{id} (DELETE) - Deleta um produto
- /produtos (GET) - Lista os produtos
- /produtos/{id} - Consulta um produto pelo ID
- /produtos/lucro/{id} - Consulta de lucro por produto
- /produtos/tipo/{tipoProduto} - Consulta de produtos por tipo (eletronico, eletrodomestico ou movel)

### movimento-estoque

- /movimento-estoque (POST) - Cria um movimento de estoque
```json
{
  "produto": {"id": 1},
  "tipoMovimentacao": "entrada",
  "valorVenda": 1200,
  "qtdMovimentada": 2
}
```
- /movimento-estoque/{id} (DEL) - Deleta um movimento de estoque
- /movimento-estoque (GET) - Lista os movimentos de estoque
- /movimento-estoque/{id} (GET) - Consulta um movimento de estoque pelo ID