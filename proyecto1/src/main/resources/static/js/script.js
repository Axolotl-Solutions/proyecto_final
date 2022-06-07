'use strict';



/*
* Redireccionamiento en la barra nav dependiendo de rol
*/

var tipoUsuario= document.getElementById("rol-nav");
if(tipoUsuario.innerHTML === "ADMINISTRADOR"){
    document.getElementById("jueces-nav").href="/admins/buscaJuez";
    document.getElementById("busca-nav").href="/admins/busca";
    document.getElementById("agregar-nav").href="/admins";
    document.getElementById("agregar-menu").href="/admins";
    document.getElementById("disciplinas-nav").href="/admins/buscarDisciplina";
    document.getElementById("inicio-nav").href="/inicioAdmin";
    document.getElementById("logo-1").href="/inicioAdmin";
    document.getElementById("logo-2").href="/inicioAdmin";
}
if(tipoUsuario.innerHTML === "ENTRENADOR"){
    document.getElementById("tabla-nav").href="/entrenador/tabla";
    document.getElementById("tabla-menu").href="/entrenador/tabla";
    document.getElementById("calificaciones-nav").href="/entrenador/calificaciones";
    document.getElementById("calificaciones-menu").href="/entrenador/calificaciones";
    document.getElementById("busca-nav").href="/entrenador/buscar";
    document.getElementById("agregar-nav").href="/entrenador/registrar";
    document.getElementById("agregar-menu").href="/entrenador/registrar";
    document.getElementById("inicio-nav").href="/entrenador/";
    document.getElementById("logo-1").href="/entrenador/";
    document.getElementById("logo-2").href="/entrenador/";
}
if(tipoUsuario.innerHTML === "COMPETIDOR"){
    document.getElementById("tabla-nav").href="/competidor/tabla";
    document.getElementById("tabla-menu").href="/competidor/tabla";
    document.getElementById("calificaciones-nav").href="/competidor/calificaciones";
    document.getElementById("calificaciones-menu").href="/competidor/calificaciones";
    document.getElementById("inicio-nav").href="/competidor/";
    document.getElementById("logo-1").href="/competidor/";
    document.getElementById("logo-2").href="/competidor/";
}

/**
* Cambiamos al estado activo cuando hacemos clicl en
* la imagen de perfil de nav.
**/

var dd_main = document.querySelector(".dd_main");
dd_main.addEventListener("click", function(){
  this.classList.toggle("active");
})

/**
 * Cambiamos al estado activo header cuando hacemos click en nav-toggle-btn
 */

const header = document.querySelector("[data-header]");
const navToggleBtn = document.querySelector("[data-menu-toggle-btn]");

navToggleBtn.addEventListener("click", function () {
  header.classList.toggle("active");
});



/**
 * Cambiamos de estado cuando hacemos clicl a card-menu-btn
 */

const menuBtn = document.querySelectorAll("[data-menu-btn]");

for (let i = 0; i < menuBtn.length; i++) {
  menuBtn[i].addEventListener("click", function () {
    this.nextElementSibling.classList.toggle("active");
  });
}
/*
* Cambiamos los colores de <p> de la tabla dependiendo el Rol.
* Útil para tabla de admin (CRUDE).
*/
let p = document.getElementsByClassName("rol-usuario");
Array.prototype.forEach.call(p, function(e) {
  console.log(e.innerHTML);
  if (e.innerHTML === "administrador") {
     e.classList.add("rol");
     e.classList.add("rol-admin");
   }else if (e.innerHTML === "competidor") {
     e.classList.add("rol");
     e.classList.add("rol-competidor");
   }else if (e.innerHTML === "entrenador") {
     e.classList.add("rol");
     e.classList.add("rol-entrenador");
   }else if (e.innerHTML === "juez") {
         e.classList.add("rol");
         e.classList.add("rol-juez");
   }
});



/*
* Obtiene la Calificación de cada elemento para agregar una animación
* Personalizada
**/

let barra = document.getElementsByClassName("progress");
let pro = document.getElementsByClassName("progress-data");
for (let i = 0; i < barra.length; i++) {
  var cssA = document.createElement("style");
  var color = "";
  if(pro[i].innerHTML*10 <= 20){
    color="ff0000";
  }else if(pro[i].innerHTML*10 <= 40){
    color="ff5900";
  }else if(pro[i].innerHTML*10 <= 60){
    color="ff5900";
  }
  else if(pro[i].innerHTML*10 <= 80){
    color="ffd900";
  }else if(pro[i].innerHTML*10 <= 90){
    color="d4ff00";
  }else {
    color="2fff00";
  }
  console.log(color);
  let reglas = document.createTextNode('@-webkit-keyframes slider'+i+'{'+
  'from { width:0%;}'+
  'to { width:'+pro[i].innerHTML*10+'%; }'+
  '}');
  cssA.appendChild(reglas);
  document.getElementsByTagName("head")[0].appendChild(cssA);
  barra[i].style.cssText += 'transition: background 3s; animation-name:slider'+i+';animation-duration: 3.8s;'+'width:'+pro[i].innerHTML*10+'%; background:#'+color+";";
}































/**/
