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
postgresql
</code></pre>

## Building
<pre><code> mvn clean install process-classes</code></pre>

## Configuration
<pre><code> 
uploadPath= 
Caminho para gravar os arquivos selecionados para upload. Caso não informafo é gravado por padrão na raiz do projeto.

server.port=8080

## PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/database
spring.datasource.username=postgres
spring.datasource.password=root
</code></pre>

# Application 
<pre><code>API REST de cadastro de pessoas utilizando as tecnologias acima. Listagem e cadastro de pessoas, operações de CRUD. Upload de Imagem.</code></pre>
