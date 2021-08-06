package br.com.zup.autores

import br.com.zup.enderecos.Endereco
import java.time.LocalDateTime
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Autor(
    val nome: String,
    val email: String,
    var descricao: String,
    @field:Embedded val endereco: Endereco
) {

    @Id
    @GeneratedValue
    var id: Long? = null

    val criadoEm: LocalDateTime = LocalDateTime.now()

    override fun toString(): String {
        return "Autor(nome='$nome', email='$email', descricao='$descricao', endereco=$endereco, id=$id, criadoEm=$criadoEm)"
    }
}