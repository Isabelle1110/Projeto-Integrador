<script>
    function togglePassword(idCampo) {
        const campo = document.getElementById(idCampo);
        const icon = campo.nextElementSibling;
        if (campo.type === 'password') {
            campo.type = 'text';
            icon.classList.remove('fa-eye');
            icon.classList.add('fa-eye-slash');
        } else {
            campo.type = 'password';
            icon.classList.remove('fa-eye-slash');
            icon.classList.add('fa-eye');
        }
    }

    function validarLogin(event) {
        event.preventDefault();

        const email = document.getElementById('email').value;
        const senha = document.getElementById('senha').value;

        let usuarios = JSON.parse(localStorage.getItem('usuarios')) || [];

        const usuarioEncontrado = usuarios.find(user => user.email === email);

        if (!usuarioEncontrado) {
            alert("Erro: E-mail não cadastrado.");
            return false;
        }

        if (usuarioEncontrado.senha === senha) {
            alert(`Login bem-sucedido! Bem-vindo(a), ${usuarioEncontrado.nome || 'Cliente'}!`);
            return true;
        } else {
            alert("Erro: Senha incorreta.");
            return false;
        }
    }
</script>
