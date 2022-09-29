package uos.capstone.backend.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Place {
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
