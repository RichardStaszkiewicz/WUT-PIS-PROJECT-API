package com.example.api.wiki.controller

import com.example.api.wiki.service.ConceptService
import com.example.model.Concept
import com.example.model.Definition
import com.example.model.Paragraph
import reactor.core.publisher.Mono
import spock.lang.Specification
import spock.lang.Subject

class ConceptControllerTest extends Specification {

	def service = Mock(ConceptService)

	@Subject
	def controller = new ConceptController(service)

	def 'When getting concept by id should propagate response from service'() {
		given:
		service.getConceptById(_ as Integer) >> Mono.justOrEmpty(response) // załadając że zwróci taką odpowiedź

		expect: // co oczekujesz po wyjściu
		controller.getConceptById(id).block() == response // mono nie zwróci wartości póki nie wwoła się na nim block

		where: // tabelka parametrów (każdy kolejny jest innym wierszem)
		id || response
		0  || null
		1  || new Concept(1, "test", "test", [])
		2  || new Concept(2, "test", "test", [Paragraph.builder().number(1).sequentialNumber(1).build()])
	}

	def 'When getting definition by id should propagate response from service'() {
		given:
		service.getDefinitionById(_ as Integer) >> Mono.justOrEmpty(response)

		expect:
		controller.getDefinitionById(id).block() == response

		where:
		id || response
		0  || null
		1  || new Definition(1, "test", "test")
	}

	def 'When saving concept should propagate method call to service'() {
		given:
		service.saveConcept(_ as Concept, id) >> Mono.justOrEmpty(concept)
		service.saveConcept(null, id) >> Mono.empty()

		expect:
		controller.saveConcept(concept, id).block() == concept

		where:
		id | concept
		0  | null
		1  | new Concept(1, "test", "test", [])
	}
}
