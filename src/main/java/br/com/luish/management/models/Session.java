package br.com.luish.management.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.luish.management.models.enums.SessionResult;
import br.com.luish.management.models.enums.SessionStatus;

@Entity
public class Session implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer result;
	private long time;
	private int totalVotes;
	private Integer status;
	
	@JsonIgnore
	@OneToMany(mappedBy = "session")
	private List<Vote> votes = new ArrayList<>();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "topic_id")
	private Convocation topic;
	
	public Session() {
	}

	public Session(long time, Convocation topic) {
		this.time = time;
		this.topic = topic;
		this.result = SessionResult.ANDAMENTO.getCode();
		this.totalVotes  = 0;
		this.status = SessionStatus.ABERTA.getCode();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(int totalVotes) {
		this.totalVotes = totalVotes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public Convocation getTopic() {
		return topic;
	}

	public void setTopic(Convocation topic) {
		this.topic = topic;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Session other = (Session) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
