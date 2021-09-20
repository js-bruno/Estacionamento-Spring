<h1> Estacionamento API</h1>

## Respostas

| Código | Descrição |
|---|---|
| `200` | Requisição executada com sucesso (ok).|
| `201` | Requisição executada com sucesso e novo um livro foi criado (created).|
| `204` | Requisição executada com sucesso sem retorno (no content).|
| `404` | Registro pesquisado não encontrado (not found).|


## EndPoints

```
  GET /parking/
  [
      {
        id: 80,
        plate: "abr-1234",
        time: "60 minutos",
        paid: false,
        out: false,
        dateTimeEntry: "19/09/2021 16:15:12",
        dateTimeOut: null
      },
      {
        id: 81,
        plate: "bru-5123",
        time: "20 minutos",
        paid: false,
        out: false,
        dateTimeEntry: "19/09/2021 16:30:35",
        dateTimeOut: null
      },
  ]
```

```
  GET /parking/{plate}
  [
      {
        id: 80,
        plate: "abr-1234",
        time: "60 minutos",
        paid: false,
        out: false,
        dateTimeEntry: "19/09/2021 16:15:12",
        dateTimeOut: null
      }
  ]
```

```
POST /parking/abc-1234

{ plate: 'FAA-1234' }
````


```
  PATCH /parking/{plate}/pay
      {
        id: 80,
        plate: "abr-1234",
        time: "60 minutos",
        paid: true,
        out: false,
        dateTimeEntry: "19/09/2021 16:15:12",
        dateTimeOut: null
      }
```

```
  PATCH /parking/{plate}/out
      {
        id: 80,
        plate: "abr-1234",
        time: "60 minutos",
        paid: true,
        out: false,
        dateTimeEntry: "19/09/2021 16:15:12",
        dateTimeOut: "19/09/2021 19:28:32"
      }
```
## Pré-Requisitos

```
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-validation</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-devtools</artifactId>
		<scope>runtime</scope>
		<optional>true</optional>
	</dependency>
	<dependency>
		<groupId>org.postgresql</groupId>
		<artifactId>postgresql</artifactId>
		<scope>runtime</scope>
	</dependency>
	<dependency>
		<groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
		<optional>true</optional>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>
	<!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
	<dependency>
		<groupId>org.junit.jupiter</groupId>
		<artifactId>junit-jupiter-api</artifactId>
		<version>5.8.0</version>
		<scope>test</scope>
	</dependency>
	<dependency>
		<groupId>javax.servlet.jsp</groupId>
		<artifactId>jsp-api</artifactId>
		<version>2.2</version>
		<scope>provided</scope>
	</dependency>
	<dependency>
		<groupId>io.springfox</groupId>
		<artifactId>springfox-swagger2</artifactId>
		<version>3.0.0</version>
	</dependency>
	<dependency>
		<groupId>io.springfox</groupId>
		<artifactId>springfox-boot-starter</artifactId>
		<version>3.0.0</version>
	</dependency>
	<dependency>
		<groupId>io.springfox</groupId>
		<artifactId>springfox-swagger-ui</artifactId>
		<version>3.0.0</version>
	</dependency>
</dependencies>

```
