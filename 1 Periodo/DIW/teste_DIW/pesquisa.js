const API_KEY = 'cd6f8a8b32484c38820d37b780795584';
const API_KEY2 = 'AIzaSyBnBv8bE7VAzKK6d3kfMSNjAXf4jGkQoYU';

function exibeNoticias() {
  let divTela = document.getElementById('box-noticia');
  let texto = '';

  // Montar texto HTML das noticias
  let dados = JSON.parse(this.responseText);
  for (i = 0; i < dados.articles.length; i++) {
    let noticia = dados.articles[i];
    let data = new Date(noticia.publishedAt);

    texto =
      texto +
      `
            <div class="col-sm-12 col-md-12 col-lg-12" id="box-noticia">
                <img src="${noticia.urlToImage}" alt="">
                <span class="titulo">${noticia.title}</span><br>
                <span class="creditos">${data.toLocaleDateString()} - 
                    ${noticia.source.name} - 
                    ${noticia.author}</span><br>
                <span class="text">
                ${noticia.content} <a href="${noticia.url}">Leia mais ...</a>
                </span>
            </div>            
        `;
  }

  // Preencher a DIV com o texto HTML
  divTela.innerHTML = texto;
}
function exibeFilme() {
  let divTelaVideo = document.getElementById('box-video');
  let texto1 = '';
  let dados = JSON.parse(this.responseText);
  for (i = 0; i < dados.results.length; i++) {
    var teste = dados.results[i].overview;

    texto1 =
      texto1 +
      `
      <div class="col-sm-12 col-md-12 col-lg-12" id="box-video">
      <p>${teste}</p>
    </div>
  `;
  }
  divTelaVideo.innerHTML = texto1;
}

function executaPesquisa() {
  let query = document.getElementById('textoPesquisa').value;

  let xhr = new XMLHttpRequest();
  xhr.onload = exibeNoticias;
  xhr.open(
    'GET',
    `https://newsapi.org/v2/everything?q=${query}&apiKey=${API_KEY}&language=pt`
  );
  xhr.send();
}


function executaPesquisaFilme() {
  let query = document.getElementById('textoPesquisa').value;

  let xhr = new XMLHttpRequest();
  xhr.onload = exibeFilme;
  xhr.open(
    'GET',
    `https://api.themoviedb.org/3/search/movie?api_key=1ce8f9dfa09e56ede06a8323155a3152&language=en-US&page=1&include_adult=false&query=${query}`
  );
  xhr.send();
}





document
  .getElementById('Expesquisa')
  .addEventListener('click', executaPesquisa);
document
  .getElementById('Expesquisa')
  .addEventListener('click', executaPesquisaFilme);
