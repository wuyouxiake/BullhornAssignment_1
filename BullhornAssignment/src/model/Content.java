package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CONTENT database table.
 * 
 */
@Entity
@NamedQuery(name="Content.findAll", query="SELECT c FROM Content c")
public class Content implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String content;

	private String photolink;

	@Column(name="USER_NAME")
	private String userName;

	public Content() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhotolink() {
		return this.photolink;
	}

	public void setPhotolink(String photolink) {
		this.photolink = photolink;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}