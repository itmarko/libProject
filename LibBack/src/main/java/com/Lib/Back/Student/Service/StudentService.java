package com.Lib.Back.Student.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Lib.Back.Student.Exception.StudentNotFoundException;
import com.Lib.Back.Student.Exception.studentAlreadyExistException;
import com.Lib.Back.Student.Model.StudentModel;
import com.Lib.Back.Student.Repository.StudentRepositry;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
	private final StudentRepositry studentRepositry;

	public StudentService(StudentRepositry studentRepositry) {
		this.studentRepositry = studentRepositry;
	}

	@Override
	public List<StudentModel> getStudentModels() {

		return studentRepositry.findAll();
	}

	@Override
	public StudentModel addStudentModel(StudentModel studentModel) {
		if (studentModelAlreadyExist(studentModel.getAddharNumber())) {
			throw new studentAlreadyExistException(studentModel.getAddharNumber() + "Already Exists !");
		}
		return studentRepositry.save(studentModel);
	}

	@Override
	public StudentModel updateStudentModel(StudentModel studentModel, Long id) {

		return studentRepositry.findById(id).map(st -> {
			st.setFirstName(studentModel.getFirstName());
			st.setLastName(studentModel.getLastName());
			st.setFatherName(studentModel.getFatherName());
			st.seteMail(studentModel.geteMail());
			st.setAddharNumber(studentModel.getAddharNumber());
			st.setMoNumber(studentModel.getMoNumber());
			st.setParentMoNumber(studentModel.getParentMoNumber());
			st.setCurrentAddress(studentModel.getCurrentAddress());
			st.setParmanentAddress(studentModel.getParmanentAddress());
			st.setGender(studentModel.getGender());
			st.setDoBirth(studentModel.getDoBirth());
			st.setDatetime(studentModel.getDatetime());
			return studentRepositry.save(st);
		}).orElseThrow(() -> new StudentNotFoundException("Sorry, this student could not be found"));
	}

	@Override
	public StudentModel getStudentModelById(Long id) {

		return studentRepositry.findById(id)
				.orElseThrow(() -> new StudentNotFoundException("Sorry, no student found with the Id :" + id));
	}

	@Override
	public void deleteStudentModel(Long id) {
		if (!studentRepositry.existsById(id)) {
			throw new StudentNotFoundException("Sorry, student not found");
		}
		studentRepositry.deleteById(id);
	}

	private boolean studentModelAlreadyExist(String addharNumber) {
		// TODO Auto-generated method stub
		return false;
	}

}
