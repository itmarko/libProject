package com.Lib.Back.Student.Service;

import java.util.List;

import com.Lib.Back.Student.Model.StudentModel;

public interface IStudentService {
	StudentModel addStudentModel(StudentModel student);

	List<StudentModel> getStudentModels();

	StudentModel updateStudentModel(StudentModel studentModel, Long id);

	StudentModel getStudentModelById(Long id);

	void deleteStudentModel(Long id);

}
