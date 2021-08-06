package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/autores")
class BuscaDetalheDeAutoresController(val autorRepository: AutorRepository) {

    @Get
    fun lista(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        if (email.isBlank()) {
            val autores = autorRepository.findAll()
            val resposta = autores.map { autor -> DetalhesDoAutorResponse(autor.nome, autor.email, autor.descricao) }
            return HttpResponse.ok(resposta)
        }

        val possivelAutor = autorRepository.buscaPorEmail(email)
        if (possivelAutor.isEmpty) {
            return HttpResponse.badRequest()
        }

        return HttpResponse.ok(DetalhesDoAutorResponse(possivelAutor.get().nome, possivelAutor.get().email, possivelAutor.get().descricao))
    }
}