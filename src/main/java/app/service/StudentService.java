package app.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentService implements BaseService{
    @Override
    public List findAll() throws Exception {
        return null;
    }

    @Override
    public Object findBydni(Long dni) throws Exception {
        return null;
    }

    @Override
    public void insert(Object entity) throws Exception {

    }

    @Override
    public void update(Long dni, Object entity) throws Exception {

    }

    @Override
    public void delete(Long id) throws Exception {

    }
}
