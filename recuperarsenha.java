<form id="recuperarsenhaForm" class="formulario" onsubmit="return iniciarRecuperacao(event)">
    </form>

<form id="redefinirSenhaForm" class="formulario" style="display:none;" onsubmit="return redefinirSenha(event)">
    <h3 class="titulo-recuperar">Nova Senha</h3>
    <p class="descricao-recuperar">Digite sua nova senha. Você já confirmou seu e-mail.</p>
    
    <div class="campo">
        <input type="password" id="novaSenha" placeholder="Nova Senha" required />
    </div>
    
    <div class="campo">
        <input type="password" id="confirmarNovaSenha" placeholder="Confirmar Nova Senha" required />
    </div>
    
    <input type="hidden" id="emailRedefinicao" /> 
    <button type="submit" class="botao">Redefinir</button>
</form>


<script>
    let emailParaRedefinicao = null; 

    function iniciarRecuperacao(event) {
        event.preventDefault(); 
        
        const email = document.getElementById('email').value;
        let usuarios = JSON.parse(localStorage.getItem('usuarios')) || [];
        const usuarioEncontrado = usuarios.find(user => user.email === email);

        if (!usuarioEncontrado) {
            alert("Erro: E-mail não cadastrado.");
            return false;
        }

        alert(`E-mail encontrado! Foi enviado um "token" de recuperação para ${email}. Clique OK para prosseguir.`);
        
        emailParaRedefinicao = email;
        
        document.getElementById('recuperarsenhaForm').style.display = 'none';
        document.getElementById('redefinirSenhaForm').style.display = 'flex';
        document.getElementById('emailRedefinicao').value = email;
        
        return false;
    }

    function redefinirSenha(event) {
        event.preventDefault();

        const novaSenha = document.getElementById('novaSenha').value;
        const confirmarNovaSenha = document.getElementById('confirmarNovaSenha').value;
        const email = document.getElementById('emailRedefinicao').value;

        if (novaSenha !== confirmarNovaSenha) {
            alert("Erro: As novas senhas não coincidem.");
            return false;
        }

        let usuarios = JSON.parse(localStorage.getItem('usuarios')) || [];
        
        const index = usuarios.findIndex(user => user.email === email);

        if (index !== -1) {
            usuarios[index].senha = novaSenha;
            localStorage.setItem('usuarios', JSON.stringify(usuarios));
            
            alert("Senha redefinida com sucesso! Você será redirecionado para o login.");
            window.location.href = "login.html"; 
        } else {
            alert("Erro interno: Usuário não encontrado para redefinição.");
        }
        return false;
    }
</script>
