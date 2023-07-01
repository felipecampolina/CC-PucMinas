// ONLOAD
window.onload = () => {
  atualizaPontos();
  compraConquista1('conquista1');
  compraConquista1('conquista2');
  compraConquista1('conquista3');
  compraConquista1('conquista4');
  compraConquista1('conquista5');
  compraConquista1('conquista6');
  compraConquista1('conquista7');
  compraConquista1('conquista8');
  compraConquista1('conquista9');

  desafio1.onclick = () => conferiDesafioFeito(desafio1);
  desafio2.onclick = () => conferiDesafioFeito(desafio2);
  desafio3.onclick = () => conferiDesafioFeito(desafio3);
  desafio4.onclick = () => conferiDesafioFeito(desafio4);
  desafio5.onclick = () => conferiDesafioFeito(desafio5);
  desafio6.onclick = () => conferiDesafioFeito(desafio6);
  desafio7.onclick = () => conferiDesafioFeito(desafio7);
  desafio8.onclick = () => conferiDesafioFeito(desafio8);
  desafio9.onclick = () => conferiDesafioFeito(desafio9);
  desafio10.onclick = () => conferiDesafioFeito(desafio10);
  conquista1.onclick = () => compraConquista('conquista1');
  conquista2.onclick = () => compraConquista('conquista2');
  conquista3.onclick = () => compraConquista('conquista3');
  conquista4.onclick = () => compraConquista('conquista4');
  conquista5.onclick = () => compraConquista('conquista5');
  conquista6.onclick = () => compraConquista('conquista6');
  conquista7.onclick = () => compraConquista('conquista7');
  conquista8.onclick = () => compraConquista('conquista8');
  conquista9.onclick = () => compraConquista('conquista9');
};
var dados = localStorage.db_usuarios;
var dados_usuarioCorrente = sessionStorage.usuarioCorrente;
dados = JSON.parse(dados);
dados_usuarioCorrente = JSON.parse(dados_usuarioCorrente);

for (i = 0; i < dados.usuarios.length; i++) {
  var id = dados.usuarios[i].id;
  var id_correto = dados_usuarioCorrente.id;
  if (id == id_correto) {
    var pontos = parseInt(dados.usuarios[i].pontos);
    var comprados = dados.usuarios[i].MedalhasCompradas;
  }
}
//Função que confere se o desafio foi feito e aumenta a quantidade de pontos
function conferiDesafioFeito(x) {
  var aux = x.id;
  if (x.checked == true) {
    ganhaPontos(justNumbers(aux));
    console.log(aux);
    atualizaPontos();
    teste = document.getElementById(`${aux}`);
    teste.style.opacity = 0;
    teste.innerHTML = `<input class="form-check-input" type="checkbox" value="" id="${aux}" disabled checked>`;
  }
}
function ganhaPontos(numero) {
  switch (numero) {
    case 1:
      pontos += 3;
      break;
    case 2:
      pontos += 7;
      break;
    case 3:
      pontos += 10;
      break;
    case 4:
      pontos += 30;
      break;
    case 5:
      pontos += 40;
      break;
    case 6:
      pontos += 50;
      break;
    case 7:
      pontos += 50;
      break;
    case 8:
      pontos += 60;
      break;
    case 9:
      pontos += 80;
      break;
    case 10:
      pontos += 120;
      break;
  }
}

//Funcão para atualizar a quantidade de pontos na tela
function atualizaPontos() {
  escritoPontos = document.getElementById('pontos');
  escritoPontos.innerText = `Pontos do Usuário : ${pontos}`;
}
function compraConquista(y) {
  idConquista = y; //Salvar id da conquista para ser utilizada na frente
  numeroConquista = justNumbers(y); //Função que tranformar o id em um número
  if (checarCompra(numeroConquista)) {
    alert('Essa medalha já foi comprada!');
    comprar = document.getElementById(idConquista);
    comprar.style.opacity = 100; //Mudar opacidade da medalha
  } else comprarMedalha(numeroConquista);
}
function compraConquista1(y) {
  idConquista = y; //Salvar id da conquista para ser utilizada na frente
  numeroConquista = justNumbers(y); //Função que tranformar o id em um número
  if (checarCompra(numeroConquista)) {
    comprar = document.getElementById(idConquista);
    comprar.style.opacity = 100; //Mudar opacidade da medalha
  }
}

function justNumbers(teste) {
  var numbers = teste.replace(/[^0-9]/g, ''); //Remover todos os caracteres que não são numeros
  return parseInt(numbers); //Transformar string em Int
}
function checarCompra(numero) {
  var i;
  if (comprados.includes(numero))
    return true; //Verificar se o número x pertence a array
  else return false;
}
function comprarMedalha(numero) {
  aux = numero;
  switch (numero) {
    case 1:
      acaoComprarMoeda(20, aux);
      break;
    case 2:
      acaoComprarMoeda(40, aux);
      break;
    case 3:
      acaoComprarMoeda(60, aux);
      break;
    case 4:
      acaoComprarMoeda(80, aux);
      break;
    case 5:
      acaoComprarMoeda(100, aux);
      break;
    case 6:
      acaoComprarMoeda(120, aux);
      break;
    case 7:
      acaoComprarMoeda(140, aux);
      break;
    case 8:
      acaoComprarMoeda(160, aux);
      break;
    case 9:
      acaoComprarMoeda(180, aux);
      break;
  }
}
function acaoComprarMoeda(valor, numero) {
  var preco = valor;
  if (pontos >= valor) {
    if (
      confirm(
        `Você tem certeza que deseja comprar a medalha de número ${numero} que tem o preço de ${preco} pontos?`
      )
    ) {
      pontos = pontos - valor;
      atualizaPontos();
      comprar = document.getElementById(idConquista);
      comprar.style.opacity = 100; //Mudar opacidade da medalha
      comprados.push(numero); //Adicionar numero na array Comprados
    } else {
      alert('Operação cancelada com sucesso!');
    }
  } else
    alert(`Pontos insuficientes. 
    O preço dessa medalha = ${preco} pontos!`);
}
function salvaPontosBancoDados() {
  for (i = 0; i < dados.usuarios.length; i++) {
    var id = dados.usuarios[i].id;
    let nome = dados.usuarios[i].nome;
    let login = dados.usuarios[i].login;
    let senha = dados.usuarios[i].senha;
    let email = dados.usuarios[i].email;
    let MedalhasCompradas = dados.usuarios[i].MedalhasCompradas;
    var id_correto = dados_usuarioCorrente.id;
    var CigarrosEvitados = dados.usuarios[i].CigarrosEvitados;
    var DinheiroEconomizado = dados.usuarios[i].DinheiroEconomizado;

    if (id == id_correto) {
      dados.usuarios[i] = {
        id: id,
        login: login,
        senha: senha,
        nome: nome,
        email: email,
        pontos: pontos,
        MedalhasCompradas: MedalhasCompradas,
        CigarrosEvitados: CigarrosEvitados,
        DinheiroEconomizado: DinheiroEconomizado,
      };
      localStorage.setItem('db_usuarios', JSON.stringify(dados));
      alert('Dados alterados com sucesso!');
    }
  }
}
function salvaMedalhasBancoDados() {
  for (i = 0; i < dados.usuarios.length; i++) {
    id = dados.usuarios[i].id;
    nome = dados.usuarios[i].nome;
    login = dados.usuarios[i].login;
    senha = dados.usuarios[i].senha;
    email = dados.usuarios[i].email;
    id_correto = dados_usuarioCorrente.id;
    MedalhasCompradas = comprados;
    CigarrosEvitados = dados.usuarios[i].CigarrosEvitados;
    DinheiroEconomizado = dados.usuarios[i].DinheiroEconomizado;

    if (id == id_correto) {
      dados.usuarios[i] = {
        id: id,
        login: login,
        senha: senha,
        nome: nome,
        email: email,
        pontos: pontos,
        MedalhasCompradas: MedalhasCompradas,
        CigarrosEvitados: CigarrosEvitados,
        DinheiroEconomizado: DinheiroEconomizado,
      };
      localStorage.setItem('db_usuarios', JSON.stringify(dados));
      alert('Dados alterados com sucesso!');
    }
  }
}

document
  .getElementById('salvar2')
  .addEventListener('click', salvaMedalhasBancoDados, salvaPontosBancoDados);
