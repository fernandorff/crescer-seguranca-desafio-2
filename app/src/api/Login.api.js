import { useContext } from "react";
import { axiosInstance } from "./_base";
import { ToastContext } from "./../context/toast-provider.context";
import { Validacao } from "./helper/validacao";

export function LoginAPI() {
  const { toggleToast } = useContext(ToastContext);
  const validarErro = Validacao();

  async function postLogin({ email, senha }) {
    try {
      const response = await axiosInstance.post(
        "/login",
        {},
        {
          auth: {
            username: email,
            password: senha,
          },
        }
      );
      toggleToast("Login bem sucedido");
      return response.data;
    } catch (error) {
      validarErro(error);
    }
  }

  async function postLogout() {
    try {
      const response = await axiosInstance.post("/logout", {}, {});

      return response.data;
    } catch (error) {
      validarErro(error);
    }
  }

  return {
    postLogin,
    postLogout,
  };
}
