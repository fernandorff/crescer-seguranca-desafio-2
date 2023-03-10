# SEGURANÇA - DESAFIO 2

## Métodos

### Autenticação

- Login:
    - `POST /login, auth: { username, password }`
- Logout:
  - `POST /logout`
- Incluir usuário:
    - `POST /usuarios, { nome: string, email: string, senha: string }`


### Usuário

- Cadastrar usuario
  - `POST /usuarios, {
    "nomeCompleto": String,
    "email": String,
    "telefone": String,
    "senha": String,
    } `
- Editar usuario:
  - `PUT /usuarios/{usuarioId}, {
    "nomeCompleto": String,
    "telefone": String,
    "fotoUrl": String
    }`
- Listar usuarios:
  - `GET /usuarios`
- Detalhes do usuario logado:
  - `GET /usuarios/me`
- Detalhar usuario:
  - `GET /usuarios/{usuarioId}`

<hr>

## Dependencias

- Java 11
- PostgreSQL 14
- Aplicação React: `./app -> npm start`
- Informações do banco de dados: `./api/src/main/resources/application.yml`
- Esquema e inserts do Banco de dados: `./api/data/schema.sql`

- Import de métodos do Insomnia: `./api/data/insomnia-tcc-java-fernando-filho.json`
