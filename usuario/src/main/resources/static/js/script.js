document.addEventListener("DOMContentLoaded", () => {

    // ==================================
    // QUANTIDADE (pagamento.html)
    // ==================================

    const btnMais = document.getElementById("btnMais");
    const btnMenos = document.getElementById("btnMenos");
    const spanQtd = document.getElementById("quantidade");
    const quantidadeInput = document.getElementById("quantidadeInput");
    const valorIngresso = document.getElementById("valorIngresso");
    const valorTotal = document.getElementById("valorTotal");

    if (btnMais && btnMenos) {
        let qtd = 1;

        function atualizarTotal() {
            spanQtd.textContent = qtd;
            quantidadeInput.value = qtd;

            if (typeof precoPorIngresso !== "undefined") {
                const subtotal = qtd * precoPorIngresso;
                valorIngresso.innerHTML = `R$ ${subtotal.toFixed(2)}`;
                valorTotal.innerHTML = `R$ ${subtotal.toFixed(2)}`;
            }
        }

        btnMais.addEventListener("click", () => { qtd++; atualizarTotal(); });
        btnMenos.addEventListener("click", () => { if (qtd > 1) { qtd--; atualizarTotal(); } });
    }

    // ==================================
    // FORMA DE PAGAMENTO (pagamento.html)
    // ==================================

    const formaPagamentoInput = document.getElementById("formaPagamentoInput");
    const btnFinalizar = document.getElementById("finalizarCompra");

    document.querySelectorAll(".payment-option").forEach(op => {
        op.addEventListener("click", () => {
            document.querySelectorAll(".payment-option").forEach(x => x.classList.remove("active"));
            op.classList.add("active");
            if (formaPagamentoInput) formaPagamentoInput.value = op.dataset.method;
            if (btnFinalizar) btnFinalizar.disabled = false;
        });
    });

});
