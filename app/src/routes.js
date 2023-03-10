import { createBrowserRouter } from "react-router-dom";
import { Navigate } from "react-router-dom";
import useGlobalUser from "./context/user.context";
import { LoginView } from "./views/login.view";
import { HomeView } from "./views/home.view";

function PrivateRoute({ Screen }) {
  const [user] = useGlobalUser();

  if (user) {
    return <Screen />;
  }

  return <Navigate to={"/"} />;
}

export const router = createBrowserRouter([
  {
    path: "/",
    element: <LoginView />,
  },
  {
    path: "/home",
    element: <PrivateRoute Screen={HomeView} />,
  },
]);
