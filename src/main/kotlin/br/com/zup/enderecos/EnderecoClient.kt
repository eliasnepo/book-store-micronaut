package br.com.zup.enderecos

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client(value = "https://viacep.com.br")
interface EnderecoClient {

    @Get("/ws/{cep}/json/")
    fun consulta(@PathVariable cep: String): HttpResponse<EnderecoResponse>
}
