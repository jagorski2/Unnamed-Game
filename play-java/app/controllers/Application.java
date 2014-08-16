package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import play.db.DB;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import dao.DAOImpl;
import dao.models.User;

public class Application extends Controller 
{
    public static Result index() {
    	Connection connection = DB.getConnection();
    	String retVal = "THIS IS YOUR RETVAL: ";
    	try 
    	{
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery("SELECT USERNAME, PASSWORD FROM USER_ACCOUNTS WHERE ID = 1");
			if (set.next())
			{
				retVal += set.getString("USERNAME"); //this is a test
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
		User user;
		
		try {
			user = mapper.readValue(request().body().asJson().toString(), User.class);
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		DAOImpl Dao = new DAOImpl();
		String ret = Dao.getUser(1);
		result.put("password", ret);
	    return ok(result);
    }

}
