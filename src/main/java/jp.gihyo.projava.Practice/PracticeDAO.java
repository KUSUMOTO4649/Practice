package jp.gihyo.projava.Practice;

import jp.gihyo.projava.Practice.HomeController.TaskItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PracticeDAO {
    private final static string TEBLE_NAME = "Practice";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    PracticeDAO(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }
    public void add(HomeController.TaskItem item) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(Practice);
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME);
        return insert.execute(param);
    }
    public List<TaskItem> findAll(){
        String query = "SELECT*FROM Practice";
        List<Map<String,Object>>result = this.jdbcTemplate.queryForList(query);
        List<TaskItem>taskItems =result.stream()
                .map((Map<String,Object>row)-> new TaskItem(
                        row.get("id").toString(),
                        row.get("task").toString(),
                        row.get("deadline").toString(),
                        (Boolean)row.get("done")))
                .toList();
        return taskItems;
    }
}
