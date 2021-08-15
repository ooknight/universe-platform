package test.io.github.ooknight.universe.support.ebean;

import io.github.ooknight.universe.support.ebean.autoconfigure.EbeanAutoConfiguration;

import io.ebean.Database;
import io.ebean.SqlRow;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = EbeanTestWithSpring.class)
@ImportAutoConfiguration({EbeanAutoConfiguration.class, DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, SqlInitializationAutoConfiguration.class})
@SqlGroup({
    @Sql("classpath:db-schema.ddl"),
    @Sql("classpath:db-data.ddl")
})
@Transactional
//@Rollback(false)
public class EbeanTestWithSpring {

    @Resource
    private Database db;

    @Test
    void test() {
        SqlRow result = db.sqlQuery("select now()").findOne();
        assertNotNull(result);
    }


    @Test
    void test3() {
        db.sqlUpdate("insert into e_role(id,name_,state_) values (101,'军师',1)").execute();
        List<SqlRow> result = db.sqlQuery("select * from e_role").findList();
        assertEquals(4, result.size());
    }

    @Test
    void test2() {
        db.sqlUpdate("insert into e_role(id,name_,state_) values (101,'军师',1)").execute();
        db.sqlUpdate("insert into e_role(id,name_,state_) values (102,'将军',1)").execute();
        List<SqlRow> result = db.sqlQuery("select * from e_role").findList();
        assertEquals(5, result.size());
    }

}
