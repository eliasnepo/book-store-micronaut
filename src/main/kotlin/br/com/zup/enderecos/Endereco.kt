package br.com.zup.enderecos

import javax.persistence.Embeddable

@Embeddable
class Endereco(enderecoResponse: EnderecoResponse, val numero: String) {
    val rua = enderecoResponse.logradouro
    val cidade = enderecoResponse.localidade
}
