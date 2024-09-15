package com.Lib.Back.Student.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Lib.Back.Student.Model.StudentModel;

public interface StudentRepositry extends JpaRepository<StudentModel, Long> {
	Optional<StudentModel> findByAddharNumber(String addharNumber);
}
