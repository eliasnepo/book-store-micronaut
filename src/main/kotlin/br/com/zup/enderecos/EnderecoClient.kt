package br.com.zup.enderecos

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client(value = "https://viacep.com.br")
interface EnderecoClient {

    @Get("/ws/{cep}/json/")
    fun consulta(@PathVariable cep: String): HttpResponse<EnderecoResponse>

    @Get(value = "/ws/{cep}/xml/", consumes = [MediaType.APPLICATION_XML])
    fun consultaComXml(@PathVariable cep: String): HttpResponse<EnderecoResponse>
}
