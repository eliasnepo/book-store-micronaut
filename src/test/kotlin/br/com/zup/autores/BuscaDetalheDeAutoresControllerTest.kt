package br.com.zup.autores

import br.com.zup.enderecos.Endereco
import br.com.zup.enderecos.EnderecoResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class BuscaDetalheDeAutoresControllerTest {

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setup() {
        val enderecoResponse = EnderecoResponse("Rua A", "Goiânia")
        val endereco = Endereco(enderecoResponse, "1234")
        autor = Autor("Elias Nepomuceno", "elias2@zup.com.br", "é ele", endereco)
        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown() {
        autorRepository.deleteAll()
    }

    @Test
    internal fun `deve retornar os detalhes de um autor`() {


        val response: HttpResponse<DetalhesDoAutorResponse> = client.toBlocking().exchange("/autores?email=${autor.email}", DetalhesDoAutorResponse::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.email, response.body()!!.email)
        assertEquals(autor.descricao, response.body()!!.descricao)
    }
}