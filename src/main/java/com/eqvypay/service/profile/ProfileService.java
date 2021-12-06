package com.eqvypay.service.profile;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.eqvypay.service.authentication.AuthenticationService;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.service.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eqvypay.persistence.User;

@Service
public class ProfileService implements ProfileRepository {
	
	@Autowired
	DatabaseConnectionManagementService dcms;

    @Autowired
    private UserRepository userRepo;
	
	@Override
	public void updateUsername(User user, String username) throws Exception {
		Connection connection = dcms.getConnection(dcms.parseEnvironment());
	
		PreparedStatement updateStatement = connection.prepareStatement("update Users set name=? where email=?");
		updateStatement.setString(1, username);
		updateStatement.setString(2, user.getEmail());
		
		boolean update = updateStatement.execute();				
	}

	@Override
	public void updateContact(User user, String contact) throws Exception {
		Connection connection = dcms.getConnection(dcms.parseEnvironment());
		
		PreparedStatement updateStatement = connection.prepareStatement("update Users set contact=? where email=?");
		updateStatement.setString(1, contact);
		updateStatement.setString(2, user.getEmail());
		
		boolean update = updateStatement.execute();
	}

	@Override
	public void updatePassword(User user, String password) throws Exception {
		Connection connection = dcms.getConnection(dcms.parseEnvironment());
		String hashedPassword = AuthenticationService.getHashedPassword(password);
		PreparedStatement updateStatement = connection.prepareStatement("update Users set password=? where email=?");
		updateStatement.setString(1, hashedPassword);
		updateStatement.setString(2, user.getEmail());
		
		boolean update = updateStatement.execute();
	}

}
