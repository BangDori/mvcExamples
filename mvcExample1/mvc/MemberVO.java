package com.yu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	private int id;
	private String name;
	private String password;
	private String email;
	private String regdate;
}
