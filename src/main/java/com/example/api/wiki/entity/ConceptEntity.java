package com.example.api.wiki.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "CONCEPTS")
public class ConceptEntity implements Serializable {

	@Id
	@GeneratedValue // automatycznie będzie podbijało wartość
	@Column(name = "CONCEPT_ID", nullable = false, unique = true, precision = 8)
	int id;

	@Column(name = "SECTION_ID", nullable = false, precision = 6)
	int sectionId;

	@Column(name = "KEY_PHRASE", nullable = false, length = 150)
	String keyPhrase;

	@Column(name = "SUMMARY", length = 400)
	String summary;

	@Builder.Default // gdy korzystasz z buildera przyjmij że pole nie jest nullem tylko new array list (jak nie chcesz mieć null pointerów)
	@JoinColumn(name = "CONCEPT_ID") // gdy zaciągam tabelę, to joinuję z inną tabelą po zadanym kluczu obcym z takim kluczem jakim jest target entity
	@OneToMany(targetEntity = ParagraphEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	List<ParagraphEntity> paragraphs = new ArrayList<>(); // zmapowana tabela
}
