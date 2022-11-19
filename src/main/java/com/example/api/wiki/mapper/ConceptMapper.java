package com.example.api.wiki.mapper;

import com.example.api.config.MapstructConfig;
import com.example.api.wiki.entity.ConceptEntity;
import com.example.model.Concept;
import com.example.model.Definition;
import java.util.ArrayList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructConfig.class, imports = {ArrayList.class})
public interface ConceptMapper {

	@Mapping(target = "id")
	@Mapping(target = "concept", source = "keyPhrase")
	@Mapping(target = "content", source = "summary")
	Definition entityToDefinition(ConceptEntity entity);

	@Mapping(target = "id")
	@Mapping(target = "keyPhrase")
	@Mapping(target = "summary")
	@Mapping(target = "paragraphs")
	Concept entityToConcept(ConceptEntity entity);

	@Mapping(target = "id")
	@Mapping(target = "keyPhrase")
	@Mapping(target = "summary")
	@Mapping(target = "paragraphs", ignore = true)
	ConceptEntity dtoToEntity(Concept entity);
}
