package org.online.edu.client.fallback;

import org.online.edu.client.LecturerClient;
import org.online.edu.entity.dto.CourseInfoDto;
import org.online.edu.entity.dto.TeacherDto;
import org.online.edu.exception.handler.EduException;
import org.springframework.stereotype.Component;

@Component
public class LecturerFallbackClient implements LecturerClient {
    @Override
    public CourseInfoDto findByCourseId(String id) {
        throw new EduException(20001, "调用户 lecturer报错");
    }

    @Override
    public TeacherDto findByTeacherId(String id) {
        throw new EduException(20001, "调用户 lecturer报错");
    }
}
