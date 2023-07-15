package com.javarush.todolist.rantsev.dao;

import com.javarush.todolist.rantsev.domain.Task;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDAO extends PagingAndSortingRepository<Task, Integer> {

}
