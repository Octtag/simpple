const apiUrl = 'http://localhost:8080/api/login';

window.addEventListener('load', function(){
    console.log("Entramos en el EventListener");

    const formulario = document.querySelector('#agregar-simpleUser');

    const inputEmail = this.document.querySelector('#email');

    const inputPassword =  this.document.querySelector('#contraseña');

    formulario.addEventListener('submit', function(event){
        event.preventDefault();

        const validacion = validacionNoVacio(inputEmail.value) && validacionNoVacio(inputPassword.value);

        if(validacion){
            console.log("Entramos en validación");
            const datosUsuario = normalizacionLogin(inputEmail.value, inputPassword.value);
            console.log(datosUsuario);

            fetchApiLogin(apiUrl, datosUsuario);
        }else{

            console.log("algun dato no es correcto");
        }
        formulario.reset();
    });
});

function validacionNoVacio(texto) {
    let resultado = true;

    if(texto === ""){
        resultado = false;
    }

    return resultado
}

function normalizacionLogin(username, password) {

    const usuario = {
        username: username.trim(),
        password: password.trim()
    }
    return usuario;
}

function fetchApiLogin(url,payload) {

    const configuraciones = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        },
        body: JSON.stringify(payload)
    }

    fetch(url, configuraciones)
    .then( respuesta => {
        console.log("las configuraciones son: ");
        console.log(configuraciones);
        console.log(respuesta);
        return respuesta.json()
    })
    .then( data => {
        console.log(data);
        console.log(data.jwt);
        //si llega correctamente un token
        console.log("TOKEN desde login.js: ", data.jwt);
        //console.log(data.jwt);
        if(data.jwt){
            localStorage.setItem('jwt', data.jwt);

            location.href = '/index.html'
        }
    }).catch( error => console.log(error))
}