let pessoa = {
    nome: 'Felipe',
    idade: 18,
    casado: false,
};

function criarPessoa(n,i,c,f) {
    let p ={};
    p.nome = n;
    p.idade = i;
    p,casado = c;
    p.numeroFilhos = f;
    return p;
};

let teste1 = criarPessoa('Felipe',18,false,5);
let teste2 = criarPessoa('Gabriel',35,true,5);
let teste3 = criarPessoa('Isabela',30,false,5);


console.log(teste1,teste2,teste3)
