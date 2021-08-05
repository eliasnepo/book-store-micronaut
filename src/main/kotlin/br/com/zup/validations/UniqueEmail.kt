package br.com.zup.validations

import br.com.zup.autores.AutorRepository
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Inject
import javax.inject.Singleton
import javax.validation.Constraint
import kotlin.annotation.AnnotationTarget.*

@MustBeDocumented
@Target(FIELD, CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueEmailValidator::class])
annotation class UniqueEmail(val message: String = "E-mail já existente.")

@Singleton
class UniqueEmailValidator(val autorRepository: AutorRepository) : ConstraintValidator<UniqueEmail, String> {

    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<UniqueEmail>,
        context: ConstraintValidatorContext
    ): Boolean {
        val possivelAutor = autorRepository.findByEmail(value!!)
        if (possivelAutor.isEmpty) {
            return true
        }
        return false
    }

}
