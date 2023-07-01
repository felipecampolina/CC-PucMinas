onload = () => {
    document.querySelector('#bt-0').onclick = () => digito(0);
    document.querySelector('#bt-1').onclick = () => digito(1);
    document.querySelector('#bt-2').onclick = () => digito(2);
    document.querySelector('#bt-3').onclick = () => digito(3);
    document.querySelector('#bt-4').onclick = () => digito(4);
    document.querySelector('#bt-5').onclick = () => digito(5);
    document.querySelector('#bt-6').onclick = () => digito(6);
    document.querySelector('#bt-7').onclick = () => digito(7);
    document.querySelector('#bt-8').onclick = () => digito(8);
    document.querySelector('#bt-9').onclick = () => digito(9);
    document.querySelector('#bt-virgula').onclick = virgula;
    document.querySelector('#bt-cancelar').onclick = cancelar;
    document.querySelector('#bt-adicao').onclick = () => operador('+');
    document.querySelector('#bt-subtracao').onclick = () => operador('-');
    document.querySelector('#bt-multiplicacao').onclick = () => operador('*');
    document.querySelector('#bt-divisao').onclick = () => operador('/');
    document.querySelector('#bt-igual').onclick = calcula;
}

//Variaveis
let sValor = '0';
let ehNovoNumero = true;
let valorAnterior = 0;
let operacaoPendente = null;

//Atualização do visor
const atualizaVisor = () => {
    let [parteInteira, parteDecimal] = sValor.split(',');
    let v = '';
    c = 0;
  for (let i = parteInteira.length - 1; i >= 0; i--){
      if (++c > 3){
          v = '.' + v;
          c = 1;
      }
      v = parteInteira[i]+v;
  }
v = v + (parteDecimal ? ',' + parteDecimal : '');
    document.querySelector('#display_resultado').innerText = v ;
    
}




//Clique no botão digito
const digito = (n) =>{
    if(ehNovoNumero){
        sValor ='' + n;
        ehNovoNumero = false;
    }
    else
    {
    sValor += n;
    }

    atualizaVisor();

    
}

//Clique botão virgula
const virgula = () =>{
    if(ehNovoNumero){
        sValor = '0,';
        ehNovoNumero = false;
    }else if(sValor.indexOf(',')==-1) sValor += ','; // Procurar se existe virgula no valor.
    atualizaVisor();  
}

// Clique no botão AC
const cancelar = () =>{
    sValor = '0';
    operacaoPendente = null;
    valorAnterior = 0;
    ehNovoNumero = true;
    atualizaVisor();
}

const valorAtual = () => parseFloat(sValor.replace(',', '.'));



// Operadores
const operador = (op) => {
    console.log(op);
    calcula();
    valorAnterior = valorAtual();
    operacaoPendente = op;
    ehNovoNumero = true;
  };
  
  const calcula = () => {
    if (operacaoPendente != null) {
      let resultado = 0;
      switch (operacaoPendente) {
        case '+':
          resultado = valorAnterior + valorAtual();
          break;
        case '-':
          resultado = valorAnterior - valorAtual();
          break;
        case '*':
          resultado = valorAnterior * valorAtual();
          break;
        case '/':
          resultado = valorAnterior / valorAtual();
          break;
      }
      sValor = resultado.toString().replace('.', ',');
    }
    ehNovoNumero = true;
    operacaoPendente = null;
    valorAnterior = 0;
    atualizaVisor();
  };

