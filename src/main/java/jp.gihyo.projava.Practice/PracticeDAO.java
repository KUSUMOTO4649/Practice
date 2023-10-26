package jp.gihyo.projava.Practice;

import jp.gihyo.projava.Practice.HomeController.TaskItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PracticeDAO {
    private final static String TABLE_NAME = "practice";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    PracticeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;}

        /**
        @param item HomeRestController.TaskItem Item;
        @return  追加件数
         */

    public int add(HomeController.TaskItem item){
        SqlParameterSource param = new BeanPropertySqlParameterSource(item);
        SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate)
                .withTableName(TABLE_NAME);
        return insert.execute(param);
    }
    public <LIst>List<HomeController.TaskItem> findAll(){
        String query = " SELECT * FROM " + TABLE_NAME;
        List<Map<String,Object>> result = this.jdbcTemplate.queryForList(query);
        List<HomeController.TaskItem>list = result.stream().map(
                (Map<String,Object>row)-> new HomeController.TaskItem(
                        row.get("id").toString(),
                        row.get("task").toString(),
                        row.get("deadline").toString(),
                        (Boolean)row.get("done")))
                .toList();
        return list;
    }
    public int delete(String id){
        int number = jdbcTemplate.update("DELETE FROM Practice WHERE id = ? ",id);
                return number;
    }
    public int update(TaskItem taskItem){
        int number = jdbcTemplate.update(
                "UPDATEPractice SET task = ?,deadline = ?,done = ?,WHERE　id =?",
        taskItem.task(),
        taskItem.deadline(),
        taskItem.done(),
        taskItem.id());
                return number;
    }
}
