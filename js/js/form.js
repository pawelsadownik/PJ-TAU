// js module
export function formDocument(id, value) {
  var x = document.createElement("INPUT");
  x.setAttribute("type", "numer");
  x.setAttribute("value", value);
  x.setAttribute("id", id);
  document.body.appendChild(x);

  var inpObj = document.getElementById(id);
  if (inpObj.value < 15) {
    document.getElementById(id).className = "invalid";
  } else {
    document.getElementById(id).className = "valid";
  }
}
