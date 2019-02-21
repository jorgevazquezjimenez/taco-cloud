package tacos.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.domain.Ingredient;
import tacos.repository.IngredientRepository;

@Repository
@Slf4j
public class JdbcIngredientRepository implements IngredientRepository {
	
	private JdbcTemplate jdbc;
	
	@Autowired	
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		super();
		this.jdbc = jdbc;
	}

	@Override
	public Iterable<Ingredient> findAll() {
		log.info("Querying all ingredients...");
		return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
	}

	@Override
	public Ingredient findOne(String id) {
		log.info("Querying ingredient with id " + id);
		return jdbc.queryForObject("select id,name,type from Ingredient where id=?", this::mapRowToIngredient,id);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		log.info("Saving " + ingredient.toString());
		jdbc.update(
				"insert into Ingredient (id, name, type) values (?, ?, ?)",
				ingredient.getId(),
				ingredient.getName(),
				ingredient.getType().toString()
				);
		return ingredient;
	}
	
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient(
				rs.getString("id"),
				rs.getString("name"),
				Ingredient.Type.valueOf(rs.getString("type")));
	}

}
