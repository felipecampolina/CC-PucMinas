import React from "react";

export default function NumAleatorio(props) // Export criado para conseguirmos usar em outros lugares do código.
{
    const {min,max} = props;
    const aleatorio = parseInt(Math.random()*[max-min]+min); // gerar número aleatorio 

    return(
        <div>
            <h4>Número aleatório</h4>
            <p>min: {min}</p>
            <p>max: {max}</p>
            <p>gerado: {aleatorio}</p>
        </div>
    )

}