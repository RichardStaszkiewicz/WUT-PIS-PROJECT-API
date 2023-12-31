package com.example.api.wiki.repository;

import com.example.api.wiki.entity.ParagraphEntity;
import com.example.api.wiki.entity.id.ParagraphId;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParagraphRepository extends CrudRepository<ParagraphEntity, ParagraphId> {
	// w ten sposób się robi nietypowe zapytania
	List<ParagraphEntity> findParagraphEntityByConceptId(int conceptId);
	List<ParagraphEntity> findAllByConceptId(int conceptId);
}
