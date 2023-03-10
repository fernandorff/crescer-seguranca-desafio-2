import { useContext } from "react";
import { ToastContext } from "./../../context/toast-provider.context";

export function Validacao() {
  const { toggleToast } = useContext(ToastContext);

  function validarErro(error) {
    if (error.response) {
      toggleToast(error.response.data.message);
      throw new Error(error.response.data.message);
    }
  }

  return validarErro;
}
