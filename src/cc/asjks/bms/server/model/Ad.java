package cc.asjks.bms.server.model;
import com.mysql.cj.jdbc.Blob;

import lombok.*;

@Data
public class Ad {
	private Integer id;
    private String contact;
    private String content;
    private String img;
    private long begin;
    private long end;
}
