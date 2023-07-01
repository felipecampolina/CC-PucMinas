window.onload = () => {
  preencheDados();
};

var dados = localStorage.db_usuarios;
var dados_usuarioCorrente = sessionStorage.usuarioCorrente;
dados = JSON.parse(dados);
dados_usuarioCorrente = JSON.parse(dados_usuarioCorrente);

function preencheDados() {
  for (i = 0; i < dados.usuarios.length; i++) {
    var id = dados.usuarios[i].id;
    let nome = dados.usuarios[i].nome;
    let login = dados.usuarios[i].login;
    let senha = dados.usuarios[i].senha;
    let pontos = dados.usuarios[i].pontos;
    let email = dados.usuarios[i].email;
    var id_correto = dados_usuarioCorrente.id;
    let MedalhasCompradas = dados.usuarios[i].MedalhasCompradas;
    let CigarrosEvitados = dados.usuarios[i].CigarrosEvitados;
    let DinheiroEconomizado = dados.usuarios[i].DinheiroEconomizado;

    if (id == id_correto) {
      document.getElementById('1').value = id;
      document.getElementById('2').value = login;
      document.getElementById('3').value = senha;
      document.getElementById('4').value = nome;
      document.getElementById('5').value = email;
    }
  }
}
function mudaDados() {
  for (i = 0; i < dados.usuarios.length; i++) {
    id = document.getElementById('1').value;
    login = document.getElementById('2').value;
    senha = document.getElementById('3').value;
    nome = document.getElementById('4').value;
    email = document.getElementById('5').value;
    pontos = dados.usuarios[i].pontos;
    id_correto = dados_usuarioCorrente.id;
    id = dados.usuarios[i].id;
    CigarrosEvitados = dados.usuarios[i].CigarrosEvitados;
    DinheiroEconomizado = dados.usuarios[i].DinheiroEconomizado;
    MedalhasCompradas = dados.usuarios[i].MedalhasCompradas;

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
        DinheiroEconomizado: DinheiroEconomizado
      };
      localStorage.setItem('db_usuarios', JSON.stringify(dados));
      alert('Dados alterados com sucesso!');
    }
  }
}

document.getElementById('salvar').addEventListener('click', mudaDados);
