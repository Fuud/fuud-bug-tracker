package com.blogspot.fuud.java.experemental.hibernate.dsl;

import com.blogspot.fuud.java.bugtracker.dao.BaseTest;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class DetachedCriteriaBuilderTest extends BaseTest {

    @Entity
    public static class TestClass {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    @Override
    protected Configuration getConfiguration() {
        return new Configuration().addAnnotatedClass(TestClass.class).configure();
    }

    @Test
    public void test() throws Exception {
        final DetachedCriteria criteria =
                new DetachedCriteriaBuilder<TestClass>(TestClass.class) {{
                    where(eq(object.getTitle(), "Lord Foo"));
                }}.getCriteria();


        save("Lord Foo");//1
        save("Lord Foo");//2
        save("Lord Car");
        save("Lord Foo");//3
        save("Lord Bar");

        final List lordsFoo = (List<TestClass>) criteria.getExecutableCriteria(getSession()).list();

        assertEquals(3, lordsFoo.size());
        for (TestClass testClass : (List<TestClass>) lordsFoo) {
            assertEquals("Lord Foo", testClass.getTitle());
        }
    }

    private void save(String title) {
        final Session session = getSessionFactory().openSession();
        session.beginTransaction();
        final TestClass testClass = new TestClass();
        testClass.setTitle(title);
        session.save(testClass);
        session.getTransaction().commit();
        session.close();
    }

}
