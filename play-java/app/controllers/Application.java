package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import assets.User;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mysql.jdbc.PreparedStatement;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.Request;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import views.html.index;
import play.db.*;

public class Application extends Controller 
{
    public static Result index() {
    	Connection connection = DB.getConnection();
    	String retVal = "THIS IS YOUR RETVAL: ";
    	try 
    	{
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery("SELECT USERID, PASSWORD FROM ACCOUNTS WHERE ID = 1");
			if (set.next())
			{
				retVal += set.getString("USERID"); //this is a test
				retVal += " " + set.getString("PASSWORD");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ok(index.render(retVal));
    	
    }
    
    public static Result sayHello() {
   		ObjectMapper mapper = new ObjectMapper();
		ObjectNode result = Json.newObject();
		User user = new User();
		try {
			user = mapper.readValue(request().body().asJson().toString(), User.class);
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Connection connection = DB.getConnection();
		try 
    	{
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery("SELECT ID, USERID, PASSWORD FROM USER_ACCOUNTS WHERE ID =" + user.getId());
			if (set.next())
			{
				result.put("id", set.getInt("ID"));
				result.put("userid", set.getString("USERID"));
			    result.put("password", set.getString("PASSWORD"));
			}
			if (connection != null)
			{
				if (!connection.isClosed())
				{
					connection.close();
				}
			}
			
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return ok(result);
    }

}
