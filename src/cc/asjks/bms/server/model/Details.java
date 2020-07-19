package cc.asjks.bms.server.model;
import lombok.*;

@Data
public class Details {
	private Integer id;
	private Integer uid;
    private String date;
    private Integer toUid;
    private Double amount;
    private String currency;
    private String type;
}
