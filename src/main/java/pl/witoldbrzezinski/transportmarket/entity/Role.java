package pl.witoldbrzezinski.transportmarket.entity;

import javax.persistence.Column;
import javax.persistence.Table;


@Table(name="authorities")
public class Role {
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name="authority", nullable = false)
	private String authority;
}
