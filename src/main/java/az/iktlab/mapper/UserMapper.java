package az.iktlab.mapper;


import az.iktlab.dao.entity.UserEntity;
import az.iktlab.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static int mapFromRsToCount(ResultSet rs) throws SQLException {
        int count = 0;
        while (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }

    public static UserEntity mapToEntity(User user) {
        UserEntity entity = new UserEntity();

        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setAge(user.getAge());
        entity.setGender(user.getGender());

        return entity;
    }
}
