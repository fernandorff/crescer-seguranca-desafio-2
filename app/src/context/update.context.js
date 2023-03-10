import createGlobalState from "react-create-global-state";

const [_useGlobalUpdate, Provider] = createGlobalState(false);

function useGlobalUpdate() {
  const [update, setUpdate] = _useGlobalUpdate();

  function toggleUpdate() {
    setUpdate(!update);
  }

  return [update, toggleUpdate];
}

export const GlobalUpdateProvider = Provider;

export default useGlobalUpdate;
