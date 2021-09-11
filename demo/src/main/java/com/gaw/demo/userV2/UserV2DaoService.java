package com.gaw.demo.userV2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserV2DaoService {
    private static List<UserV2> users = new ArrayList<UserV2>();	
	static {
		users.add(new UserV2(1, new Name("Galih", "Wicaksono"), new Date()));
		users.add(new UserV2(4, new Name("Evelina", "Larisa"), new Date()));
		users.add(new UserV2(6, new Name("Sapi", "Udin"), new Date()));
	}
	
	public List<UserV2> findAll(){
		return users;
	}
}
