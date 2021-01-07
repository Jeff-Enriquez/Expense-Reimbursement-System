package com.ers.doas;

import com.ers.exceptions.PasswordException;
import com.ers.exceptions.UsernameException;
import com.ers.models.Manager;

public interface ManagerDoa {
	public Manager selectManager(String username, String password) throws PasswordException, UsernameException;
}
