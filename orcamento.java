const NUMERO_LUCIANO = '556194126644'; 
localStorage.removeItem('popupVistoOrcamento');

function fecharPopup() {
    document.getElementById('cadastro-popup').style.display = 'none';
    localStorage.setItem('popupVistoOrcamento', 'true');
}

function irParaCadastro() {
    localStorage.setItem('popupVistoOrcamento', 'true');
    window.location.href = "cadastro.html"; 
}

function formatarCelular(input) {
    let value = input.value.replace(/\D/g, ""); 
    value = value.substring(0, 11);
    let formattedValue = '';
    if (value.length > 0) {
        formattedValue += '(' + value.substring(0, 2);
    }
    if (value.length > 2) {
        formattedValue += ') ' + value.substring(2, 7);
    }
    if (value.length > 7) {
        formattedValue += '-' + value.substring(7, 11);
    }
    input.value = formattedValue;
}

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('celular').addEventListener('input', function() {
        formatarCelular(this);
    });

    document.getElementById('formOrcamento').addEventListener('submit', function(event) {
        event.preventDefault();

        const celularInput = document.getElementById('celular');
        const celularLimpo = celularInput.value.replace(/\D/g, "");
        if (celularLimpo.length < 10 || celularLimpo.length > 11) { 
            alert("Por favor, preencha o campo Celular com um DDD e número válidos (10 ou 11 dígitos).");
            celularInput.focus();
            return;
        }

        const nome = document.getElementById('nome').value;
        const email = document.getElementById('email').value;
        const dataEvento = document.getElementById('dataEvento').value;
        const comentario = document.getElementById('comentario').value;
        const tipoEventoElement = document.querySelector('input[name="tipoEvento"]:checked');
        const tipoEvento = tipoEventoElement ? tipoEventoElement.value : 'Não informado';

        let mensagem = `*🚨 NOVA SOLICITAÇÃO DE ORÇAMENTO (via Website) 🚨*%0A%0A`;
        mensagem += `Prezado Luciano,%0A`;
        mensagem += `Recebemos uma nova solicitação de orçamento com os seguintes detalhes:%0A%0A`;
        mensagem += `*DADOS DO CLIENTE*%0A`;
        mensagem += `---------------------------------%0A`;
        mensagem += `*Nome:* ${nome}%0A`;
        mensagem += `*Email:* ${email}%0A`;
        mensagem += `*Celular:* ${celularInput.value}%0A%0A`;
        mensagem += `*DETALHES DO EVENTO*%0A`;
        mensagem += `---------------------------------%0A`;
        mensagem += `*Tipo de Evento:* ${tipoEvento}%0A`;
        mensagem += `*Data Sugerida:* ${dataEvento}%0A`;
        mensagem += `*Comentário/Necessidades Adicionais:* ${comentario || 'N/A'}%0A%0A`;
        mensagem += `Aguardamos o seu contato para darmos continuidade ao processo.%0A`;
        mensagem += `Att.,%0AEquipe do Site`;

        const linkWhatsApp = `https://wa.me/${NUMERO_LUCIANO}?text=${mensagem}`;
        window.open(linkWhatsApp, '_blank');
        alert("A solicitação de orçamento será aberta no WhatsApp do Luciano. Por favor, confirme o envio.");
        this.reset();
    });

    function fecharModal() {
        console.log("Fechando modal...");
    }

    window.onload = function() {
        const popupElement = document.getElementById('cadastro-popup');
        if (popupElement) {
            const popupVisto = localStorage.getItem('popupVistoOrcamento');
            if (!popupVisto) {
                popupElement.style.display = 'flex';
            }
        }
    };
});
