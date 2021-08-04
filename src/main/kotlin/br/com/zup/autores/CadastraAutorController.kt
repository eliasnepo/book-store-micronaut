package br.com.zup.autores

import io.micronaut.data.annotation.Repository
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraAutorController(@Inject val autorRepository: AutorRepository) {

    @Post
    fun cadastra(@Body @Valid request: NovoAutorRequest) {
        println(request)
        var autor: Autor = request.paraAutor()
        autor = autorRepository.save(autor)
        println(autor.id)
    }
}