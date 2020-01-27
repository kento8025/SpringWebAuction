package jp.co.webAuction.db.entity.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.webAuction.db.dto.Favorite;
import jp.co.webAuction.db.dto.User;
import jp.co.webAuction.db.entity.FavoriteDao;

@Repository
public class PgFavoriteDao implements FavoriteDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void favoriteRegisterOrUpdate(int userId, int registrNumber, String favoriteName, String favoriteUrl) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		List<Favorite> favoriteList = favoriteListGet(userId);

		for (Favorite favorite : favoriteList) {

			if (favorite.getRegistrNumber() == registrNumber) {

				String sql = "UPDATE favorite SET favorite_name = :favoriteName , favorite_url = :favoriteUrl " +
						"   WHERE user_id = :userId  AND RegistrNumber = :registrNumber ";

				param.addValue("userId", userId);
				param.addValue("registrNumber", registrNumber);
				param.addValue("favoriteName", favoriteName);
				param.addValue("favoriteUrl", favoriteUrl);

				jdbcTemplate.update(sql, param);

				return;
			}

		}

		String sql = "INSERT INTO favorite (user_id , RegistrNumber , favorite_name , favorite_url ) " +
				"VALUES(:userId , :registrNumber , :favoriteName , :favoriteUrl)";

		param.addValue("userId", userId);
		param.addValue("registrNumber", registrNumber);
		param.addValue("favoriteName", favoriteName);
		param.addValue("favoriteUrl", favoriteUrl);

		jdbcTemplate.update(sql, param);

	}

	@Override
	public void favoriteSearch(User user, HttpServletRequest request) {

		//ÉzÅ[ÉÄÇÃÇ®ãCÇ…ì¸ÇËìoò^êîÇ™8Ç»ÇÃÇ≈ÇªÇÍÇå≥Ç…çÏê¨

		final int MAX_FAVORITE_BUTTON = 8;

		Favorite[] favoriteSet = new Favorite[MAX_FAVORITE_BUTTON];
		Favorite defaultFavoriteSet = new Favorite();

		if (!(user == null)) {

			List<Favorite> favoriteList = favoriteListGet(user.getId());

			for (Favorite favorite : favoriteList) {

				favoriteSet[favorite.getRegistrNumber() - 1] = favorite;

			}

			for (int i = 0; i < MAX_FAVORITE_BUTTON; i++) {

				if (favoriteSet[i] == null) {

					request.setAttribute("favoriteList" + (i + 1), defaultFavoriteSet);

				} else {

					request.setAttribute("favoriteList" + favoriteSet[i].getRegistrNumber(), favoriteSet[i]);

				}

			}

		} else {

			for (int i = 0; i < MAX_FAVORITE_BUTTON; i++) {

				request.setAttribute("favoriteList" + (i + 1), defaultFavoriteSet);

			}
		}

	}

	private List<Favorite> favoriteListGet(int userId) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		String sql = "SELECT * FROM favorite WHERE user_id = :userId ";

		param.addValue("userId", userId);

		return jdbcTemplate.query(
				sql,
				param,
				new BeanPropertyRowMapper<Favorite>(Favorite.class));

	}

}
