'use strict';


/*
* Redireccionamiento en la barra nav dependiendo de rol
*/
var tipoUsuario= document.getElementById("rol-nav");
if(tipoUsuario.innerHTML === "ADMINISTRADOR"){
    document.getElementById("agregar-nav").href="/admins";
    document.getElementById("agregar-menu").href="/admins";
    document.getElementById("inicio-nav").href="/inicioAdmin";
    if(document.URL === 'http://127.0.0.1:8080/admins'){
        document.getElementsByClassName("navbar-link")[1].classList.add("active");
    }
    if(document.URL === 'http://127.0.0.1:8080/inicioAdmin'){
            document.getElementsByClassName("navbar-link")[0].classList.add("active");
        }
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
  let reglas = document.createTextNode('@-webkit-keyframes slider'+i+'{'+
  'from { width:0%; }'+
  'to { width:'+pro[i].innerHTML*10+'%; }'+
  '}');
  cssA.appendChild(reglas);
  document.getElementsByTagName("head")[0].appendChild(cssA);
  barra[i].style.cssText += 'animation-name:slider'+i+'; animation-duration: 1.8s;'+'width:'+pro[i].innerHTML*10+'%;';
}
/*
Array.prototype.forEach.call(barra, function(e) {
  var cssA = document.createElement("style");
  let reglas = document.createTextNode('@-webkit-keyframes slider {'+
  'from { width:0%; }'+
  'to { width:100%; }'+
  '}');
  cssA.appendChild(reglas);
  document.getElementsByTagName("head")[0].appendChild(cssA);
  e.style.cssText += 'animation-name:slider; animation-duration: 1.8s;';
});
*/






























/**/
