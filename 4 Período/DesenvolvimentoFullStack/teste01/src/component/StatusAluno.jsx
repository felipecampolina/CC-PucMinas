import React from "react";

export default function StatusAluno(props){
    const status = props.nota >= 6 ? "Aprovado":"Reprovado";
    return(
        <div>
            <p>Meu nome é {props.nome}</p>
            <p>Eu fui {status}</p>
        </div>
    )
}