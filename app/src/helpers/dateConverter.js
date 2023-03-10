export function dateConverter(dataRecebida) {
  const date = new Date(dataRecebida);

  const year = date.getFullYear().toString();
  const month = (date.getMonth() + 1).toString();
  const day = date.getDate().toString();
  const hours = date.getHours().toString();
  const minutes = date.getMinutes().toString();

  return ` ${day}/${month}/${year} - ${hours}:${minutes} `;
}
