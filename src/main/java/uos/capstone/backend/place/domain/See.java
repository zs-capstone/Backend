package uos.capstone.backend.place.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uos.capstone.backend.common.domain.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="see")
@Getter
@Setter
@ToString
public class See extends BaseEntity {
	private Integer placeid;
	private String title;
	private String address;
	private String introduction;
	private Double latitude;
	private Double longitude;
	private String dial_num;
	private String type;
	private String region1;
	private String region2;
	private String image_url;
	private Double popularity;
	private Integer nature;
	private Integer outdoor;
	private Integer fatigue;
	private Integer sea;
	private Integer walking;
	private Integer exciting;
	private Integer day;
	private Integer culture;
	private Integer group_num;
	private String tag;
}
