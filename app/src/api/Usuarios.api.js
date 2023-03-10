import { axiosInstance } from "./_base";
import { ToastContext } from "./../context/toast-provider.context";
import { Validacao } from "./helper/validacao";
import { useContext } from "react";

export function UsuariosAPI() {
  const { toggleToast } = useContext(ToastContext);
  const validarErro = Validacao();

  async function getListaDeUsuarios() {
    try {
      const usuario = await axiosInstance.get(`/usuarios`);
      return usuario.data;
    } catch (error) {
      validarErro(error);
    }
  }

  async function getDetalhesUsuario({ usuarioId }) {
    try {
      const usuario = await axiosInstance.get(`/usuarios/${usuarioId}`);
      return usuario.data;
    } catch (error) {
      validarErro(error);
    }
  }

  async function postIncluirUsuario({
    nomeCompleto,
    telefone,
    email,
    senha,
    fotoUrl,
  }) {
    try {
      const response = await axiosInstance.post("/usuarios", {
        nomeCompleto: nomeCompleto,
        telefone: telefone,
        email: email,
        senha: senha,
        fotoUrl: fotoUrl,
        permissoes: ["USER"],
      });
      return response.data;
    } catch (error) {
      validarErro(error);
    }
  }

  async function postSolicitarNovaSenha({ emailNovaSenha }) {
    try {
      const response = await axiosInstance.post(`/usuarios/novasenha/publico`, {
        emailNovaSenha: emailNovaSenha,
      });
      toggleToast("Código enviado");
      return response.data;
    } catch (error) {
      console.log(error);
      validarErro(error);
    }
  }

  async function putTrocarSenha({
    emailNovaSenha,
    codigoNovaSenha,
    novaSenha,
  }) {
    try {
      const response = await axiosInstance.put(
        `/usuarios/trocarsenha/publico`,
        {
          emailNovaSenha: emailNovaSenha,
          codigo: codigoNovaSenha,
          novaSenha: novaSenha,
        }
      );
      return response.data;
    } catch (error) {
      console.log("aaaaaaaaaaaaaaaaaa");
      console.log(error);
      validarErro(error);
    }
  }

  async function putEditarUsuario({
    nomeCompleto,
    telefone,
    fotoUrl,
    usuarioId,
  }) {
    try {
      const response = await axiosInstance.put(`/usuarios/${usuarioId}`, {
        nomeCompleto: nomeCompleto,
        telefone: telefone,
        fotoUrl: fotoUrl,
      });
    } catch (error) {
      validarErro(error);
    }
  }

  async function getDadosDoUsuarioLogado() {
    try {
      const usuario = await axiosInstance.get(`/usuarios/me`);
      return usuario.data;
    } catch (error) {
      validarErro(error);
    }
  }

  return {
    getDetalhesUsuario,
    postIncluirUsuario,
    getListaDeUsuarios,
    putEditarUsuario,
    getDadosDoUsuarioLogado,
    postSolicitarNovaSenha,
    putTrocarSenha,
  };
}
