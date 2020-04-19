package org.online.edu.client;

import org.online.edu.client.fallback.LecturerFallbackClient;
import org.online.edu.entity.dto.CourseInfoDto;
import org.online.edu.entity.dto.TeacherDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "lecturer", fallback = LecturerFallbackClient.class)
public interface LecturerClient {

    @GetMapping("lecturer/inner/course/{id}")
    CourseInfoDto findByCourseId(@PathVariable String id);

    @GetMapping("lecturer/inner/teacher/{id}")
    TeacherDto findByTeacherId(@PathVariable String id);
}
