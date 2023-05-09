# Voll.med

<p align="center">Esse projeto foi desenvolvido a partir do curso oferecido pelo <a href="https://www.alura.com.br/">Alura</a> e ministrado pelo instrutor <a href="https://cursos.alura.com.br/user/rodrigo-ferreira">Rodrigo Ferreira</a> e feito algumas modificações finais necessárias para atender o deploy no railway e o modo que é realizado o login.</p>

<b>Tabela de conteúdos</b>

- [Sobre](#Sobre)
- [Funcionalidades](#Funcionalidades)
- [Anotações](#Anotações)
- [Tecnologias](#Tecnologias)

# Sobre

Voll.med é uma clínica médica fictícia que precisa de um aplicativo para gestão de consultas. O aplicativo deve possuir funcionalidades que permitam o cadastro de médicos e de pacientes, e também o agendamento e cancelamento de consultas.

Enquanto um time de desenvolvimento será responsável pelo aplicativo mobile, o nosso será responsável pelo desenvolvimento da API Rest desse projeto.

# Funcionalidades

- [x] CRUD de médicos;
- [x] CRUD de pacientes;
- [x] Agendamento de consultas;
- [x] Cancelamento de consultas;
- [x] Login e signup do sistema;
- [x] Deploy no Railway.

# Anotações

Todas as informações aprendidas e que são importantes para a construção do projeto.


Swagger é uma ferramenta de software livre que ajuda a projetar, construir, documentar e consumir APIs RESTful. Ele fornece uma interface gráfica de usuário para visualizar e interagir com as APIs e também gera automaticamente a documentação da API em diferentes formatos.
E o swagger da aplicação está sendo exibido através da seguinte url: <a href="https://medicalapp.up.railway.app/swagger-ui/index.html#/">https://medicalapp.up.railway.app/swagger-ui/index.html#/</a>

OpenAPI 3.0 é uma especificação para descrição de APIs RESTful, que permite documentar e padronizar a interface das suas APIs, tornando mais fácil para os desenvolvedores entenderem e utilizarem os seus serviços.
O OpenAPI do projeto é apresentado no seguinte link: <a href="https://medicalapp.up.railway.app/v3/api-docs">https://medicalapp.up.railway.app/v3/api-docs</a>

.........................EM CONSTRUÇÃO.....................

# Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Auth0](https://auth0.com/docs/) - Plataforma de autenticação e autorização em nuvem que simplifica o processo de adicionar autenticação segura aos aplicativos.
- [H2 Database](https://www.h2database.com/html/main.html) - É um banco de dados em memória com suporte a SQL e JDBC, adequado para desenvolvimento e testes.
- [Hibernate](https://hibernate.org/orm/documentation/6.2/) - É um framework ORM (Mapeamento Objeto-Relacional) para Java que permite aos desenvolvedores mapear objetos Java para tabelas em um banco de dados relacional. Ele fornece uma camada de abstração entre o código Java e o banco de dados, permitindo que os desenvolvedores interajam com o banco de dados usando objetos Java. Além disso, o Hibernate também oferece recursos como caching, lazy loading e validação de dados.
- [Flyway](https://flywaydb.org/documentation/) - Uma ferramenta de migração de banco de dados que permite gerenciar e versionar as alterações no esquema do banco de dados.
- [Java 17](https://www.oracle.com/java/) - É uma linguagem de programação orientada a objetos, multiplataforma e amplamente utilizada em desenvolvimento de software e aplicações web.
- [Log4j](https://logging.apache.org/log4j/2.x/javadoc.html) - É uma biblioteca de registro de eventos de código aberto para a linguagem de programação Java.
- [Lombok](https://projectlombok.org/api/) - Uma biblioteca Java que ajuda a reduzir a verbosidade do código ao gerar automaticamente os métodos getters, setters, equals, hashCode e toString, além de outros recursos úteis.
- [Maven](https://maven.apache.org/guides/) - É uma ferramenta de automação de compilação de projetos Java que ajuda a gerenciar as dependências e a construir, testar e implantar o projeto de forma mais eficiente. Ele usa um arquivo de configuração XML chamado "pom.xml" para descrever as informações do projeto e as dependências necessárias, facilitando a construção e a manutenção de projetos complexos em Java.
- [MySQL](https://dev.mysql.com/doc/) - Sistema de gerenciamento de banco de dados relacional de código aberto amplamente utilizado em aplicações web.
- [Railway](https://docs.railway.app/) - É uma plataforma de hospedagem na nuvem que permite aos desenvolvedores implantar facilmente aplicativos web e bancos de dados em uma infraestrutura gerenciada.
- [Spring Boot 3](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) - É um framework Java baseado no Spring que oferece uma maneira rápida e fácil de criar aplicativos prontos para produção com configuração mínima. Ele vem com muitas convenções inteligentes que ajudam a aumentar a produtividade do desenvolvedor e permite que você se concentre na lógica de negócios do seu aplicativo

Made with 💜 by Diego Henrique 👋 [See my Linkedin](https://www.linkedin.com/in/diegohts/)
