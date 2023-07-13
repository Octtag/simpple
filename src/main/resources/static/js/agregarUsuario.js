window.addEventListener('load', function () {

  const formulario = document.querySelector('#agregar-simpleUser');

  const crearUsuario = async () => {

    const formData = {
      username: formulario.querySelector('#nombre').value,
      email: formulario.querySelector('#email').value,
      dni: formulario.querySelector('#dni').value,
      celular: formulario.querySelector('#celular').value,
      password: formulario.querySelector('#contraseña').value,
    };

    const url = '/api/sign-up';
    const settings = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData)
    }

    const resp = await fetch(url, settings);
    showAlert(resp.status);
    const data = await resp.json();

    return data;

  };

  const showAlert = (status) => {

    status === 200 ? (
      Swal.fire({
        icon: 'success',
        title: 'El usuario fue generado exitosamente',
        showConfirmButton: false,
        timer: 5000
      })

    ) : (
      Swal.fire({
        icon: 'error',
        title: 'Hubo un error al crear el usuario',
        showConfirmButton: false,
        timer: 3000
      })
    );
  }

  formulario.addEventListener('submit', async (event) => {

    event.preventDefault();

    const contraseña = formulario.querySelector('#contraseña').value;
    const contraseñaConfirmada = formulario.querySelector('#contraseñaConfirmada').value;
    const resultadoBox = document.querySelector('#resultado');

    if (contraseña === contraseñaConfirmada) {

      const data = await crearUsuario();
      clearErrorMessage();
      formulario.reset();
      if (data) {
              setTimeout(() => {
                window.location.replace('loginSimple.html');
              }, 5000);
            }
         
    } else {
      // Las contraseñas no coinciden, mostrar mensaje de error
      document.getElementById("contraseña").style.borderColor = "red";
      document.getElementById("contraseñaConfirmada").style.borderColor = "red";
      const mensajeError = document.createElement("p");
      mensajeError.id = "errorMessage"; // Agregar un ID al elemento de mensaje de error
      mensajeError.style.color = "red";
      mensajeError.innerHTML = "Las contraseñas no coinciden.";
      resultadoBox.appendChild(mensajeError); // Agregar el mensaje de error al formulario
    }

    function clearErrorMessage() {
      const mensajeError = document.getElementById("errorMessage");
      mensajeError.remove(); // Eliminar el elemento de mensaje de error si existe
    }
  });
});
