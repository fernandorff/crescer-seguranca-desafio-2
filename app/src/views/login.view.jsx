import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { LoginAPI } from "../api/Login.api";
import useGlobalUser from "../context/user.context";
import { UsuariosAPI } from "./../api/Usuarios.api";
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";

const formVazio = {
  email: "",
  senha: "",
  nomeCompletoCadastro: "",
  telefoneCadastro: "",
  emailCadastro: "",
  senhaCadastro: "",
  fotoUrlCadastro: "",
  emailNovaSenha: "",
  codigoNovaSenha: "",
  novaSenha: "",
};

export function LoginView() {
  const { postLogin } = LoginAPI();
  const { postIncluirUsuario, postSolicitarNovaSenha, putTrocarSenha } =
    UsuariosAPI();
  const navigate = useNavigate();
  const [user, setUser] = useGlobalUser();

  const [formInput, setFormInput] = useState(formVazio);
  const [cadastrar, setCadastrar] = useState(false);
  const [esqueceuSenha, setEsqueceuSenha] = useState(false);
  const [solicitouNovaSenha, setSolicitouNovaSenha] = useState(false);
  const [botaoDesabilitado, setBotaoDesabilitado] = useState(false);

  useEffect(() => {
    if (user) {
      navigate("/home");
    }
  }, [user, navigate]);

  function handleChange(event) {
    const { name, value } = event.target;
    setFormInput((oldFormInput) => ({
      ...oldFormInput,
      [name]: value,
    }));
  }

  function switchCadastrar() {
    setCadastrar(!cadastrar);
    setFormInput(formVazio);
  }

  function switchEsqueceuSenha() {
    setEsqueceuSenha(!esqueceuSenha);
    setSolicitouNovaSenha(false);
    setFormInput(formVazio);
  }

  async function solicitarNovaSenha() {
    setBotaoDesabilitado(true);
    try {
      const response = await postSolicitarNovaSenha({
        emailNovaSenha: formInput.emailNovaSenha,
      });
      setSolicitouNovaSenha(true);
    } catch (error) {
    } finally {
      setBotaoDesabilitado(false);
    }
  }

  async function trocarSenha() {
    const response = await putTrocarSenha({
      emailNovaSenha: formInput.emailNovaSenha,
      codigoNovaSenha: formInput.codigoNovaSenha,
      novaSenha: formInput.novaSenha,
    });
    setSolicitouNovaSenha(false);
    setEsqueceuSenha(false);
  }

  async function handleLogin(event) {
    event.preventDefault();
    const user = await postLogin({
      email: formInput.email,
      senha: formInput.senha,
    });
    if (user) {
      setUser(user);
    }
  }

  async function handleIncluirUsuario(event) {
    event.preventDefault();
    await postIncluirUsuario({
      nomeCompleto: formInput.nomeCompletoCadastro,
      email: formInput.emailCadastro,
      senha: formInput.senhaCadastro,
      dataNascimento: formInput.dataNascimentoCadastro,
      telefone: formInput.telefoneCadastro,
    });
    setFormInput(formVazio);
    setCadastrar(!cadastrar);
  }

  return (
    <Container className="vh-100 d-flex justify-content-center align-items-center">
      <Card
        className="m-5 p-5"
        style={{ maxWidth: "600px", minWidth: "600px" }}
      >
        <Card.Body className="bg-opacity-50">
          {!cadastrar && !esqueceuSenha ? (
            <>
              <Card.Title className="text-center">Login</Card.Title>
              <Form onSubmit={handleLogin} className="d-flex flex-column p-1">
                <Form.Group className="mb-3">
                  <Form.Label>E-mail</Form.Label>
                  <Form.Control
                    placeholder="E-mail"
                    type="text"
                    name="email"
                    value={formInput.email}
                    onChange={handleChange}
                  />
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label>Senha</Form.Label>
                  <Form.Control
                    placeholder="Usuário"
                    type="text"
                    name="senha"
                    value={formInput.senha}
                    onChange={handleChange}
                  />
                </Form.Group>
                <Button
                  className="mx-auto"
                  variant="link"
                  onClick={switchEsqueceuSenha}
                >
                  Esqueceu a senha?
                </Button>
                <div className="d-flex justify-content-between my-3">
                  <Button type="submit">Entrar</Button>
                  <Button variant="outline-secondary" onClick={switchCadastrar}>
                    Cadastre-se
                  </Button>
                </div>
              </Form>
            </>
          ) : !cadastrar && esqueceuSenha && !solicitouNovaSenha ? (
            <>
              <Card.Title className="text-center">
                Recuperação de senha
              </Card.Title>
              <Form onSubmit={handleLogin} className="d-flex flex-column p-1">
                <Form.Group className="mb-3">
                  <Card.Text className="text-center text-secondary">
                    O código de recuperação de senha será enviado para o e-mail
                    informado. O código expira em 10 minutos.
                  </Card.Text>
                  <Form.Label>Informe o e-mail cadastrado.</Form.Label>
                  <Form.Control
                    placeholder="E-mail"
                    type="text"
                    name="emailNovaSenha"
                    value={formInput.emailNovaSenha}
                    onChange={handleChange}
                  />
                </Form.Group>
                <Button
                  onClick={solicitarNovaSenha}
                  className="mb-3 mx-auto"
                  disabled={botaoDesabilitado}
                >
                  Solicitar nova senha
                </Button>
                <Button
                  className="mx-auto"
                  variant="link"
                  onClick={switchEsqueceuSenha}
                >
                  Voltar
                </Button>
              </Form>
            </>
          ) : !cadastrar && esqueceuSenha && solicitouNovaSenha ? (
            <>
              <Card.Title className="text-center">
                Insira o código e a nova senha abaixo
              </Card.Title>
              <Card.Text className="text-center text-secondary">
                O código de recuperação de senha foi enviado para o e-mail
                informado. O código expira em 10 minutos.
              </Card.Text>
              <Form onSubmit={handleLogin} className="d-flex flex-column p-1">
                <Form.Label>E-mail: {formInput.emailNovaSenha}</Form.Label>
                <Form.Group className="mb-3">
                  <Form.Label>Código de recuperação</Form.Label>
                  <Form.Control
                    placeholder="Insira o código de recuperação aqui."
                    type="text"
                    name="codigoNovaSenha"
                    value={formInput.codigoNovaSenha}
                    onChange={handleChange}
                  />
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label>Nova senha</Form.Label>
                  <Form.Control
                    placeholder="Insira a nova senha aqui"
                    type="text"
                    name="novaSenha"
                    value={formInput.novaSenha}
                    onChange={handleChange}
                  />
                </Form.Group>
                <Button onClick={trocarSenha} className="mb-3 mx-auto">
                  Trocar senha
                </Button>
                <Button
                  className="mx-auto"
                  variant="link"
                  onClick={switchEsqueceuSenha}
                >
                  Voltar
                </Button>
              </Form>
            </>
          ) : (
            <>
              <Card.Title className="text-center">Novo Usuário</Card.Title>
              <Form
                onSubmit={handleIncluirUsuario}
                className="d-flex flex-column p-1"
              >
                <Form.Group className="mb-3">
                  <Form.Label>Nome Completo</Form.Label>
                  <Form.Control
                    placeholder="Nome completo"
                    type="text"
                    name="nomeCompletoCadastro"
                    value={formInput.nomeCompletoCadastro}
                    onChange={handleChange}
                  />
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label>Telefone</Form.Label>
                  <Form.Control
                    placeholder="Telefone"
                    type="text"
                    name="telefoneCadastro"
                    value={formInput.telefoneCadastro}
                    onChange={handleChange}
                  />
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label>E-mail</Form.Label>
                  <Form.Control
                    placeholder="E-mail"
                    type="text"
                    name="emailCadastro"
                    value={formInput.emailCadastro}
                    onChange={handleChange}
                  />
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label>Senha</Form.Label>
                  <Form.Control
                    placeholder="Senha"
                    type="text"
                    name="senhaCadastro"
                    value={formInput.senhaCadastro}
                    onChange={handleChange}
                  />
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label>Foto de perfil</Form.Label>
                  <Form.Control
                    placeholder="Url da foto"
                    type="text"
                    name="fotoUrlCadastro"
                    value={formInput.fotoUrlCadastro}
                    onChange={handleChange}
                  />
                </Form.Group>
                <div className="d-flex justify-content-between my-3">
                  <Button type="submit" variant="success">
                    Cadastrar
                  </Button>
                  <Button variant="outline-secondary" onClick={switchCadastrar}>
                    Login
                  </Button>
                </div>
              </Form>
            </>
          )}
        </Card.Body>
      </Card>
    </Container>
  );
}
