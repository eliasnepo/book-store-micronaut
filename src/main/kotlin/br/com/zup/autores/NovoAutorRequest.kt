package br.com.zup.autores

import br.com.zup.enderecos.Endereco
import br.com.zup.enderecos.EnderecoResponse
import br.com.zup.validations.UniqueEmail
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoAutorRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email @field:UniqueEmail val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank val cep: String,
    @field:NotBlank val numero: String) {

    fun paraAutor(enderecoResponse: EnderecoResponse): Autor {
        return Autor(nome, email, descricao, Endereco(enderecoResponse, numero))
    }
}
