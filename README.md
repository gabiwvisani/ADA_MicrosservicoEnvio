# Microsserviço Envio - API de E-commerce
Este documento descreve o microsserviço de Envio para um sistema de e-commerce.

**Responsabilidade:**
O microsserviço de Envio é responsável por cadastrar, consultar e alterar o status de envios de produtos comprados pelos clientes.

**Tecnologias utilizadas:**
- Spring Boot
- Java 17 ou superior
- PostgreSQL
-	RabbitMQ
-	Docker Compose
-	Lombok
-	ModelMapper

**Pré-requisitos:**
1.	Instalar o Docker: https://docs.docker.com/get-docker/
2.	Clonar o repositório do projeto.
   
**Executando a API:**
1.	Rodar o serviço através do comando docker-compose up.
2.	O RabbitMQ precisa estar funcional na porta 5672.
3.	O banco de dados PostgreSQL precisa estar funcional na porta 5433 e configurado de acordo com o arquivo application.yml.

**Acessando a API:**
A API está disponível na porta 9080. Você pode utilizar ferramentas como Postman ou Insomnia para testar os endpoints.
- Documentação: http://localhost:9080/swagger-ui/index.html

**Endpoints:**
Cadastrar Envio (POST /add/envio). Recebe um objeto EnvioRequest no corpo da requisição e cadastra um novo envio.

Campos obrigatórios:
- id_cliente: identificador do cliente
-	id_compra: identificador da compra
-	destinatario: nome do destinatário
-	rua: rua do endereço de entrega
-	cep: CEP do endereço de entrega

Retorno:
-	Sucesso (201): não retorna nenhum conteúdo.
-	Erro (400): retorna um objeto EnvioErrorResponse informando o motivo do erro.

**Consultar Envio por ID (GET /consulta/envio/{id}):**
Busca um envio específico pelo seu identificador.

Parâmetros:
-	{id}: identificador do envio

Retorno:
-	Sucesso (200): retorna um objeto Envio com as informações do envio.
-	Não encontrado (404): caso o envio não seja encontrado.

**Consultar Todos os Envios (GET /envios):**
Busca uma lista com todos os envios cadastrados.

Retorno: 
-	Sucesso (200): retorna uma lista de objetos Envio.
-	Erro (500): caso ocorra algum erro ao recuperar os envios.

**Alterar Status de Envio (PATCH /altera/status/envio/{id}):**
Altera o status do envio para "enviado ao cliente".

Parâmetros:
-	{id}: identificador do envio

Corpo da requisição:
-	statusEnviadoProCliente: boolean indicando o novo status

Retorno:
-	Sucesso (200): não retorna nenhum conteúdo.
-	Não encontrado (404): caso o envio não seja encontrado.
-	Erro interno (500): caso ocorra algum erro ao salvar a alteração.

**Estrutura do Projeto:**
O projeto está organizado da seguinte forma:
-	/src/main/java/: código fonte da aplicação.
-	/src/main/resources/: arquivos de configuração, como application.yml.
-	/docker-compose.yml: arquivo de configuração do Docker Compose.
-	/pom.xml: arquivo de configuração do Maven.

**Testes:**
O projeto possui testes unitários para validar o funcionamento das principais funcionalidades.

**Conclusão:**
Este microsserviço fornece as funcionalidades básicas para gerenciar envios de produtos em um e-commerce. A API está documentada e pronta para ser utilizada por outros microsserviços do sistema.

**Diagramas Utilizados:**

![image](https://github.com/vivianpanizzi/ADA_MicrosservicoEnvio/assets/103074990/fc7010c8-10ab-427c-af78-82da6c7b29db)
___________________________________________________________________________________________________________________________________________________________________________________________________
![image](https://github.com/vivianpanizzi/ADA_MicrosservicoEnvio/assets/103074990/f73387a1-bfed-417d-90ce-c58208ce2c0a)
___________________________________________________________________________________________________________________________________________________________________________________________________
![image](https://github.com/vivianpanizzi/ADA_MicrosservicoEnvio/assets/103074990/ef557193-b43c-43dc-bbb4-6c428fc5c854)


