import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import BoasVindas from './component/boasVindas' // devemos importar todos os componentes para usar
import Identificacao from './component/identificacao'
import IdentificacaoComParametro from './component/IdentificacaoComParametro'
import StatusAluno from './component/StatusAluno'
function App() {


  return (
   <main>
    <BoasVindas></BoasVindas>
    <IdentificacaoComParametro

    nome = "Felipe"
    sobrenome = "Campolina"
    idade = {20}//passagem de inteiros
    
    ></IdentificacaoComParametro>

    <StatusAluno
    nome = "Helena"
    nota = {6.5}
    
    />

   </main>
  )
}

export default App // Precisamos exportar para outros componentes terem acesso a ele
