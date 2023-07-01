const KEY_BD = '@usuarioestudo'


var listaRegistros = {
    ultimoIdgerado: 0,
    usuario: []
}

function gravarBD() {
    localStorage.setItem(KEY_BD, JSON.stringify(listaRegistros))
}

function lerBD() {
    const data = localStorage.getItem(KEY_BD)
    if (data) {
        listaRegistros = JSON.parse(data)
    }
    desenhar()
}

function desenhar() {
    const tbody = document.getElementById('listaRegistrosBody')
    if (tbody) {
        tbody.innerHTML = listaRegistros.usuario
            .sort((a, b) => {
                return a.name < b.name ? -1 : 1
            })
            .map(usuario => {

                return `<tr>
              <td>${usuario.id}</td>
              <td>${usuario.name}</td>
              <td>${usuario.tel}</td>
              <td>${usuario.city}</td>
              <td>${usuario.mail}</td>
              <td>
                <button onclick='visualizar("cadastro", false,${usuario.id})'>Editar</button>
                <button class='vermelho' onclick='perguntarSeDeleta(${usuario.id})'>Excluir</button>
              </td>
          </tr>`

            }).join('')
    }
}

function insertUser(name, tel, city, mail) {
    const id = listaRegistros.ultimoIdgerado + 1;
    listaRegistros.ultimoIdgerado = id;
    listaRegistros.usuario.push({
        id, name, tel, city, mail
    })
    gravarBD()
    desenhar()
    visualizar('lista')

}

function editUser(id, name, tel, city, mail) {
    var usuario = listaRegistros.usuario.find(usuario => usuario.id == id)
    usuario.name = name;
    usuario.tel = tel;
    usuario.city = city;
    usuario.mail = mail;

    gravarBD()
    desenhar()
    visualizar('lista')
}

function deleteUser(id) {
    listaRegistros.usuario = listaRegistros.usuario.filter(usuario => {
        return usuario.id != id
    })
    gravarBD()
    desenhar()
}

function perguntarSeDeleta(id) {
    if (confirm('Deseja mesmo excluir?' + id)) {
        deleteUser(id)
        desenhar()
    }
}

function limparEdicao() {
    document.getElementById('name').value = ''
    document.getElementById('tel').value = ''
    document.getElementById('city').value = ''
    document.getElementById('mail').value = ''
}

function visualizar(pagina, novo = false, id = null) {
    document.body.setAttribute('page', pagina)
    if (pagina === 'cadastro') {
        if (novo) limparEdicao()
        if (id) {
            const usuario = listaRegistros.usuario.find(usuario => usuario.id === id)
            if (usuario) {
                document.getElementById('id').value = usuario.id
                document.getElementById('name').value = usuario.name
                document.getElementById('tel').value = usuario.tel
                document.getElementById('city').value = usuario.city
                document.getElementById('mail').value = usuario.mail
            }
        }
        document.getElementById('name').focus()
    }
}


function submeter(e) {
    e.preventDefault()
    const data = {
        id: document.getElementById('id').value,
        name: document.getElementById('name').value,
        tel: document.getElementById('tel').value,
        city: document.getElementById('city').value,
        mail: document.getElementById('mail').value,
    }
    if (data.id) {
        editUser(data.id, data.name, data.tel, data.city, data.mail)
    } else {
        insertUser(data.name, data.tel, data.city, data.mail)
    }

}


window.addEventListener('load', () => {
    lerBD()

    document.getElementById('cadastroRegistro').addEventListener('submit', submeter)
})