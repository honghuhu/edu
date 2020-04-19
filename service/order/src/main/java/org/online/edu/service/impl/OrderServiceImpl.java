package org.online.edu.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.online.edu.client.LecturerClient;
import org.online.edu.client.UCenterClient;
import org.online.edu.entity.Order;
import org.online.edu.entity.dto.CourseInfoDto;
import org.online.edu.entity.dto.MemberDto;
import org.online.edu.entity.dto.TeacherDto;
import org.online.edu.mapper.OrderMapper;
import org.online.edu.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author 007
 * @since 2020-04-16
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private Snowflake snowflake;
    private UCenterClient uCenterClient;
    private LecturerClient lecturerClient;

    @Override
    public String save(String courseId, String memberId) {
        MemberDto member = uCenterClient.info(memberId);
        CourseInfoDto course = lecturerClient.findByCourseId(courseId);
        TeacherDto teacher = lecturerClient.findByTeacherId(course.getTeacherId());
        Order order = new Order().setOrderNo(snowflake.nextIdStr())
                .setCourseId(courseId)
                .setCourseTitle(course.getTitle())
                .setCourseCover(course.getCover())
                .setTeacherName(teacher.getName())
                .setTotalFee(course.getPrice())
                .setMemberId(memberId)
                .setMobile(member.getMobile())
                .setNickname(member.getNickname())
                .setStatus(0)
                .setPayType(1);
        save(order);
        return order.getOrderNo();
    }
}
