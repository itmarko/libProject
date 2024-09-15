package com.Lib.Back.Student.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Lib.Back.Student.Model.StudentModel;
import com.Lib.Back.Student.Service.IStudentService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin("http://localhost:5173/")
@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {
	private final IStudentService studentService;

	public StudentController(IStudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public ResponseEntity<List<StudentModel>> getstudentsmodel() {
		return new ResponseEntity<>(studentService.getStudentModels(), HttpStatus.FOUND);
	}

	@PostMapping
	public StudentModel addStudentModel(@RequestBody StudentModel studentModel) {
		return studentService.addStudentModel(studentModel);
	}

	@PutMapping("/update/{id}")
	public StudentModel updateStudentModel(@RequestBody StudentModel studentModel, @PathVariable long id) {
		return studentService.updateStudentModel(studentModel, id);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteStudentModel(@PathVariable Long id) {
		studentService.deleteStudentModel(id);
	}

	@GetMapping("/student/{id}")
	public StudentModel getStudentById(@PathVariable Long id) {
		return studentService.getStudentModelById(id);
	}
}
