import React from "react";

function IdentificacaoComParametro(props){
// Não precisamos do render()
        return(
            <div>
                <strong> Meu nome é {props.nome}</strong>
                <strong> Meu sobrenome é {props.sobrenome}</strong>
                <strong> Tenho {props.idade} anos</strong>
            </div>
        )
    
}
export default IdentificacaoComParametro;