package com.example.api.wiki.controller;

import com.example.api.wiki.entity.ConceptEntity;
import com.example.api.wiki.repository.ConceptRepository;
import com.example.api.wiki.service.ConceptService;
import com.example.model.Concept;
import com.example.model.Definition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/concept")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ConceptController {

	private final ConceptService conceptService;
	private final ConceptRepository conceptRepository;

	@GetMapping("/definition/{id}")
	public Mono<Definition> getDefinitionById(@PathVariable int id) {
		return conceptService.getDefinitionById(id);
	}

	@GetMapping("{id}")
	public Mono<Concept> getConceptById(@PathVariable int id) {
		return conceptService.getConceptById(id);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ConceptEntity>> getAllConcepts() {
		try {
			List<ConceptEntity> concepts = new ArrayList<>();
			conceptRepository.findAll().forEach(concepts::add);
			if (concepts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(concepts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("{sectionId}")
	public Mono<Concept> saveConcept(@RequestBody Concept concept, @PathVariable int sectionId) {
		return conceptService.saveConcept(concept, sectionId);
	}
}
