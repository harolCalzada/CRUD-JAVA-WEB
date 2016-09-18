package com.harol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.harol.model.User;
import com.harol.util.DbUtil;

public class UserDao {

	private Connection connection;
	
	public UserDao(){
		connection = DbUtil.getConnection();
	}
	
	public void addUser(User user){
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into users(fistname, lastname, dob, email) values (?, ?, ?, ?)");
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deleteUser(int userId){
		try {
			PreparedStatement preaparedStatement = connection
					.prepareStatement("delete from users where userid=?");
			preaparedStatement.setInt(1, userId);
			preaparedStatement.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user){
		try{
			PreparedStatement preparedStatement = connection
					.prepareStatement("update users set firstname=?, lastname=?, dob=?, email=?"+
							"where userid=?");
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setInt(5, user.getUserid());
			preparedStatement.executeUpdate();
			
		}catch (SQLException e){
			e.printStackTrace();
		} 
		
	}
	
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<User>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from users");
			while (rs.next()){
				User user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lasname"));
				user.setEmail(rs.getString("email"));
				users.add(user);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return users;
	}
	
	
	public User getUserById(int userId){
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from users where userid=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
		}catch (SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	
}
