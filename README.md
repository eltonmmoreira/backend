# Backend - JAVA
# Getting Started
Clone o repositório:
<pre><code> https://github.com/eltonmmoreira/backend.git</code></pre>

# Tecnologias:
<pre><code> Spring Boot, JPA, Postgresql, JAVA, Hibernate, REST </pre>

# Building and running the application
## Pre-requisites
<pre><code>
JAVA 15
Maven
postgresql
</code></pre>

## Building
<pre><code> mvn clean install process-classes</code></pre>

## Configuration
<pre><code> 
uploadPath= 
Caminho para gravar os arquivos selecionados para upload. Caso não informado é gravado por padrão na raiz do projeto.

Configuração porta padrão da aplicação
server.port=8080

## PostgreSQL
Criar um banco com nome database, usuário e senha conforme configurações abaixo:

spring.datasource.url=jdbc:postgresql://localhost:5432/database
spring.datasource.username=postgres
spring.datasource.password=root
</code></pre>

# Application 
<pre><code>API REST de cadastro de pessoas utilizando as tecnologias acima. 
Listagem e cadastro de pessoas, operações de CRUD. Upload de Imagem.
</code></pre>
