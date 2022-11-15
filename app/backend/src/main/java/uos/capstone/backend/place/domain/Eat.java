package uos.capstone.backend.place.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uos.capstone.backend.common.domain.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="eat")
@Getter
@Setter
@ToString
public class Eat extends BaseEntity {
    private String contentsid;
    private String title;
    private String roadaddress;
    private String tag;
    private String introduction;
    private Double latitude;
    private Double longitude;
    private String label;
    private String phoneno;
    private String imgpath;
    private String thumbnailpath;
    private Double popularity;
    private Integer tag_len;
    private String popular_tag;
    private Integer len;
}
