package be.ordina.springbatch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import be.ordina.springbatch.domain.Person;

public class JdbcPersonMapper implements RowMapper<Person> {

	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person person = new Person();
		person.setFirstName(rs.getString("firstName"));
		person.setName(rs.getString("lastName"));
		person.setCity(rs.getString("city"));
		person.setId(rs.getLong("id"));
		return person;
	}
}
