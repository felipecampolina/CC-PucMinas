
window.onload = () =>{
    startStop();
    checarPrimeiraVez();
    salvaAltracoes.onclick = () => fechaFormulario();
    cigarro.onchange = () => atualizaFormulario();
    vape.onchange = () => atualizaFormulario();
    salvaAltracoes.onclick = () => respondeFormulario();
    var intervalo = setInterval(atualizaCardTempo,1000);
    setInterval(atualizaCardCigarrosEvitados,1000);
    setInterval(atualizaCardDinheiroEconomizado,1000);
    
    
}


//timer
var x;
var startstop = 0;
var primeiraVezAcesso = true;
var formularioAberto = 0;


function startStop() { /* Toggle StartStop */

  startstop = startstop + 1;

  if (startstop === 1) {
    start();
  } 
}


function start() {
  x = setInterval(timer, 10);
  checarPrimeiraVez();
} /* Start */

function stop() {
  clearInterval(x);
} /* Stop */


var milisec = 0;
var sec = 0; /* holds incrementing value */
var min = 0;
var hour = 0;
var day = 0;
var month = 0 ;
var year = 0;
var minutosPassados = 0;

/* Contains and outputs returned value of  function checkTime */

var miliSecOut = 0;
var secOut = 0;
var minOut = 0;
var hourOut = 0;
var dayOut = 0;
var monthOut = 0;
var yearOut = 0;

/* Output variable End */


function timer() {
  /* Main Timer */


  miliSecOut = checkTime(milisec);
  secOut = checkTime(sec);
  minOut = checkTime(min);
  hourOut = checkTime(hour);
  dayOut = checkTime(day);
  monthOut = checkTime(month);
  yearOut = checkTime(year);

  milisec = ++milisec;

  if (milisec === 100) {
    milisec = 0;
    sec = ++sec;
  }

  if (sec == 60) {
    min = ++min;
    minutosPassados = ++minutosPassados
    sec = 0;
  }

  if (min == 60) {
    min = 0;
    hour = ++hour;
  }
  if (hour == 24) {
    hour = 0;
    day = ++day;
  }
  if (day == 30) {
    day = 0;
    month = ++month;
  }
  if (month == 12) {
    month = 0;
    year = ++year;
  }


  document.getElementById("milisec").innerHTML = miliSecOut;
  document.getElementById("sec").innerHTML = secOut;
  document.getElementById("min").innerHTML = minOut;
  document.getElementById("hour").innerHTML = hourOut;
  document.getElementById("day").innerHTML = dayOut;
  document.getElementById("month").innerHTML = monthOut;
  document.getElementById("year").innerHTML = yearOut;

}


/* Adds 0 when value is <10 */


function checkTime(i) {
  if (i < 10) {
    i = "0" + i;
  }
  return i;
}

function reset() {
  milisec = 0;
  sec = 0;
  min = 0
  hour = 0;
  day = 0;
  month = 0;
  year = 0;
  minutosPassados = 0;
  document.getElementById("milisec").innerHTML = "00";
  document.getElementById("sec").innerHTML = "00";
  document.getElementById("min").innerHTML = "00";
  document.getElementById("hour").innerHTML = "00";
  document.getElementById("day").innerHTML = "00";
  document.getElementById("month").innerHTML = "00";
  document.getElementById("year").innerHTML = "00";
}

//Formulario 1* Vez acesso

function checarPrimeiraVez() // Função que checa a necessidade do formulario
{
    if(primeiraVezAcesso)
    {
    abreFormulario();
    primeiraVezAcesso = false;
    }
}
function respondeFormulario()
{
  cigarro = document.getElementById('cigarro');
  vape = document.getElementById('vape');
  resposta2 = document.getElementById("resposta2");
  resposta3 = document.getElementById("resposta3");
  resposta4 = document.getElementById("resposta4");
  resposta5 = document.getElementById("resposta5");
  resposta2 = parseInt(resposta2.value);
  resposta3 = parseInt(resposta3.value);
  resposta4 = parseInt(resposta4.value);
  resposta5 = parseInt(resposta5.value);
  if(vape.checked)console.log(resposta2,resposta3,resposta4,resposta5);
  else console.log(resposta2,resposta3,resposta4,);
  atualizaCardCigarrosEvitados(resposta2);
  

}
function atualizaFormulario()//Atualiza formulario com perguntas diferentes
{
    cigarro = document.getElementById('cigarro');
    vape = document.getElementById('vape');
    if(cigarro.checked){
      pergunta2 = document.getElementById('pergunta2');
      pergunta3 = document.getElementById('pergunta3');
      pergunta4 = document.getElementById('pergunta4');
      pergunta5 = document.getElementById('pergunta5');
      resposta5 = document.getElementById('resposta5');
      pergunta2.innerText = "2 - Quantos cigarros você fuma por dia? ";
      pergunta3.innerText = "3 - Quantos cigarros têm no maço? ";
      pergunta4.innerText= "4 - Qual o preço do maço(R$)?";
      pergunta5.style.display  = "none";
      resposta5.style.display  = "none";

    }
    else{
      console.log('teste');
      pergunta2.innerText = "2 - Quantos pods por dia? ";
      pergunta3.innerText = "3 - Quantos ML de essência cabem no seu vape? ";
      pergunta4.innerText= "4 - Quantos ML tem sua essência/juice?" ;
      pergunta5.style.display  = "grid";
      resposta5.style.display  = "grid";
    };
}
function fechaFormulario(){
  
    formularioAberto = 0;
}
function abreFormulario(){
  
  
  botao = document.getElementById('configuracoes');
  botao.click();
  formularioAberto = 1;
}
function atualizaCardTempo(){
  //card tempo sem fumar
  minutosSemFumar = document.getElementById("minutosSemfumar");
  horasSemFumar = document.getElementById("horasSemfumar");
  diasSemFumar = document.getElementById("diasSemfumar");
  mesesSemFumar = document.getElementById("mesesSemfumar");
  anoSemFumar = document.getElementById("anosSemfumar");
  minutosSemFumar.innerText = `Minutos: ${min }`;
  horasSemFumar.innerText = `Horas: ${hour }`;
  diasSemFumar.innerText = `Dias: ${day }`;
  mesesSemFumar.innerText = `Mêses: ${month }`;
  anoSemFumar.innerText = `Anos: ${year }`;

}
function atualizaCardCigarrosEvitados(){
  card2 = document.getElementById('cardCigarrosEvitados');
  cigarros_evitados = (resposta2/1440)*minutosPassados;
  cigarros_evitados = (cigarros_evitados.toFixed(2));
  card2.innerText = `${cigarros_evitados}`;
}
function atualizaCardDinheiroEconomizado(){
  if(cigarro.checked){
    cigarrosPorMinuto = (resposta2 / 1440);
    precoPorCigarro = resposta4/resposta2 ;
    dinheiroEconomizadoPorminuto = ((precoPorCigarro * cigarrosPorMinuto)*minutosPassados).toFixed(2);
    card3 = document.getElementById('dinheiroEconomizadoPorMinuto');
    card3.innerText = `R$${dinheiroEconomizadoPorminuto}`;
  }
  if(vape.checked){
    usoPorDia = (resposta2 * resposta3);
    precoPorDia = (resposta5 * usoPorDia) / resposta4;
    precoPorMinuto = ((precoPorDia / 1440)*minutosPassados).toFixed(3);
    card4 = document.getElementById('dinheiroEconomizadoPorMinuto');
    card4.innerText = `R$${precoPorMinuto}`;
  }
  }
