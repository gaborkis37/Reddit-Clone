package com.homeProj.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Link {

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String url;

	public Link() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", title=" + title + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Link)) {
			return false;
		}
		Link other = (Link) obj;
		return Objects.equals(id, other.id) && Objects.equals(title, other.title) && Objects.equals(url, other.url);
	}

}
