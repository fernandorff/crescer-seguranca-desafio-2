import { createContext, useState } from "react";
import { Toast, ToastContainer } from "react-bootstrap";

export const ToastContext = createContext({
  show: false,
  message: "",
  toggleToast: () => {},
});

const ToastProvider = (props) => {
  const [show, setShow] = useState(false);
  const [message, setMessage] = useState("");

  const toggleToast = (message) => {
    setMessage(message);
    setShow(!show);
  };

  return (
    <ToastContext.Provider value={{ show, message, toggleToast }}>
      {props.children}
      <ToastContainer
        className="p-3"
        position="top-start"
        containerPosition="fixed"
      >
        <Toast onClose={() => setShow(false)} show={show} delay={3000} autohide>
          <Toast.Body style={{ fontWeight: "bold", textAlign: "center" }}>
            {message}
          </Toast.Body>
        </Toast>
      </ToastContainer>
    </ToastContext.Provider>
  );
};

export default ToastProvider;
