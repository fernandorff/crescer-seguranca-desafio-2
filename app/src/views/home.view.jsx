import useGlobalUser from "../context/user.context";
import useGlobalUpdate from "../context/update.context";
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import "bootstrap/dist/css/bootstrap.min.css";
import { useEffect, useState } from "react";
import { UsuariosAPI } from "../api/Usuarios.api";
import { LoginAPI } from "../api/Login.api";

const formVazio = {
  nomeCompletoEditar: "",
  telefoneEditar: "",
  fotoUrlEditar: "",
};

export function HomeView() {
  const [user, setUser] = useGlobalUser();
  const { postLogout } = LoginAPI();
  const { getDadosDoUsuarioLogado, putEditarUsuario } = UsuariosAPI();
  const [update, toggleUpdate] = useGlobalUpdate();
  const [formInput, setFormInput] = useState(formVazio);
  const [usuarioAtual, setUsuarioAtual] = useState();

  function handleChange(event) {
    const { name, value } = event.target;
    setFormInput((oldFormInput) => ({
      ...oldFormInput,
      [name]: value,
    }));
  }

  async function logout() {
    await postLogout();
    setUser(null);
  }

  async function editarUsuario(event) {
    event.preventDefault();
    await putEditarUsuario({
      nomeCompleto: formInput.nomeCompletoEditar,
      telefone: formInput.telefoneEditar,
      fotoUrl: formInput.fotoUrlEditar,
      usuarioId: usuarioAtual.id,
    });
    toggleUpdate();
  }

  useEffect(() => {
    async function fetchData() {
      const usuarioLogado = await getDadosDoUsuarioLogado();
      setUsuarioAtual(usuarioLogado);
    }
    fetchData();
  }, [update]);

  return (
    <>
      <Container className="vh-100 d-flex justify-content-center align-items-center">
        <Card
          className="m-5 p-5"
          style={{ maxWidth: "600px", minWidth: "600px" }}
        >
          <Card.Body className="bg-opacity-50">
            <Card.Title className="text-center">Perfil de Usuário</Card.Title>

            {usuarioAtual &&
              Object.keys(usuarioAtual).map((key, index) => (
                <div key={index}>
                  <p>
                    <strong>{key}:</strong> {usuarioAtual[key]}
                  </p>
                </div>
              ))}

            <Card.Title className="text-center">
              Editar dados do Usuário
            </Card.Title>

            <Form className="d-flex flex-column p-1" onSubmit={editarUsuario}>
              <Form.Group className="mb-3">
                <Form.Label>Nome Completo</Form.Label>
                <Form.Control
                  placeholder="Nome completo"
                  type="text"
                  name="nomeCompletoEditar"
                  value={formInput.nomeCompletoEditar}
                  onChange={handleChange}
                />
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>Telefone</Form.Label>
                <Form.Control
                  placeholder="Telefone"
                  type="text"
                  name="telefoneEditar"
                  value={formInput.telefoneEditar}
                  onChange={handleChange}
                />
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>Foto de perfil</Form.Label>
                <Form.Control
                  placeholder="Url da foto"
                  type="text"
                  name="fotoUrlEditar"
                  value={formInput.fotoUrlEditar}
                  onChange={handleChange}
                />
              </Form.Group>

              <div className="d-flex justify-content-between my-3">
                <Button type="submit" variant="success">
                  Editar usuario
                </Button>
                <Button variant="danger" onClick={logout}>
                  Logout
                </Button>
              </div>
            </Form>
          </Card.Body>
        </Card>
      </Container>
    </>
  );
}
