package uos.capstone.backend.note.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.capstone.backend.common.domain.BaseEntity;
import uos.capstone.backend.user.domain.User;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Note extends BaseEntity {
	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private Date dayStart;

	@Column(nullable = false)
	private Date dayEnd;

	@Column(nullable = false)
	private Integer adult;

	@Column(nullable = false)
	private Integer child;

	@Column(nullable = false)
	private Integer animal;

	@OneToMany(mappedBy = "note", orphanRemoval = true, cascade = {CascadeType.ALL})
	private List<NoteRegion> region;

	@OneToMany(mappedBy = "note",orphanRemoval = true, cascade = {CascadeType.ALL})
	private List<NoteTheme> theme;

	@OneToMany(mappedBy = "note", orphanRemoval = true, cascade = {CascadeType.ALL})
	private List<NotePlace> place;

	private String thumbnail;

	@Column(nullable = false)
	private Boolean public_share;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;
}
