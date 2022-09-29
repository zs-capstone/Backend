package uos.capstone.backend.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="hotel")
@Getter
@Setter
@ToString
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
