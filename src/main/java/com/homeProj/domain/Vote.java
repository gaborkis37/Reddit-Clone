package com.homeProj.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Vote extends Auditable {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private short direction;

	@NonNull
	@ManyToOne
	private Link link;

	@Override
	public String toString() {
		return "Vote [id=" + id + ", direction=" + direction + ", link=" + link + "]";
	}

}
