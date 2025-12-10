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

    document.getElementById('formCadastro').addEventListener('submit', function(event) {
        event.preventDefault();

        const nome = document.getElementById('nome').value;
        const email = document.getElementById('email').value;
        const senha = document.getElementById('senha').value;
        const confirmar = document.getElementById('confirmar').value;

        if (senha !== confirmar) {
            alert("Erro: As senhas não coincidem. Por favor, verifique.");
            document.getElementById('senha').value = '';
            document.getElementById('confirmar').value = '';
            document.getElementById('senha').focus();
            return;
        }

        let usuarios = JSON.parse(localStorage.getItem('usuarios')) || [];

        const usuarioExistente = usuarios.find(user => user.email === email);
        if (usuarioExistente) {
            alert("Erro: Este e-mail já está cadastrado. Tente fazer login ou recuperar a senha.");
            return;
        }

        const novoUsuario = { nome, email, senha };
        usuarios.push(novoUsuario);
        localStorage.setItem('usuarios', JSON.stringify(usuarios));

        alert("Cadastro realizado com sucesso! Você já pode fazer login.");
        
        window.location.href = "login.html";
    });
</script>
