package org.belief.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 留言
 * @author ydjx
 *
 */
@Entity
public class Message {

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String author;
	@Column(nullable=false)
	private String title;
	@Column(nullable=false)
	private String body;
	
	public final Long getId() {
		return id;
	}
	public final void setId(Long id) {
		this.id = id;
	}
	public final String getAuthor() {
		return author;
	}
	public final void setAuthor(String author) {
		this.author = author;
	}
	public final String getTitle() {
		return title;
	}
	public final void setTitle(String title) {
		this.title = title;
	}
	public final String getBody() {
		return body;
	}
	public final void setBody(String body) {
		this.body = body;
	}
	
	
}
